package poa.packets;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ParticleStatus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.ChatVisiblity;
import net.minecraft.world.level.GameType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import poa.util.FetchSkin1219;
import poa.util.PoaPlugin1219;

import java.util.*;
import java.util.logging.Level;

public class FakeTablist1219 {

    private static final Map<UUID, GameProfile> activeTabProfiles = new HashMap<>();

    /**
     * Spawns a fake player in the tablist with the given info.
     *
     * @param sendTo   Viewers to send packets to
     * @param name     Internal name of fake player
     * @param username Display name in tablist
     * @param skinName Name of the player to copy skin from
     * @param uuid     Unique UUID for fake player
     * @param latency  Ping value in tablist
     * @param skinModel 0 = classic, 1 = slim (Alex)
     */
    public static void addTabPlayer(List<Player> sendTo, String name, net.kyori.adventure.text.Component username, String skinName, UUID uuid, int latency, int skinModel) {
        Bukkit.getScheduler().runTaskAsynchronously(PoaPlugin1219.getPlugin(), () -> {
            try {
                UUID skinUUID = Bukkit.getOfflinePlayer(skinName).getUniqueId();
                String texture = FetchSkin1219.fetchSkinURL(skinUUID);
                String signature = FetchSkin1219.fetchSkinSignature(skinUUID);

                if (texture == null || signature == null) {
                    Bukkit.getLogger().log(Level.WARNING, "Failed to fetch skin for " + skinName);
                    return;
                }

                Bukkit.getScheduler().runTask(PoaPlugin1219.getPlugin(), () ->
                        spawnTabEntry(sendTo, name, username, uuid, texture, signature, latency, skinModel)
                );
            } catch (Exception e) {
                Bukkit.getLogger().log(Level.SEVERE, "Error while fetching skin for " + skinName, e);
            }
        });
    }

    /**
     * Internal: builds and sends the ADD_PLAYER tablist packet.
     */
    private static void spawnTabEntry(List<Player> sendTo, String name, net.kyori.adventure.text.Component username, UUID uuid,
                                      String skinTexture, String skinSignature, int latency, int skinModel) {

        ServerPlayer fakePlayer = createServerPlayer(name, uuid, skinModel, skinTexture, skinSignature);

        GameProfile profile = fakePlayer.getGameProfile();
        activeTabProfiles.put(uuid, profile);

        ClientboundPlayerInfoUpdatePacket.Entry entry = new ClientboundPlayerInfoUpdatePacket.Entry(
                fakePlayer.getUUID(),
                profile,
                true,
                latency,
                GameType.DEFAULT_MODE,
                Component.empty(),
                true,
                0,
                null
        );

        EnumSet<ClientboundPlayerInfoUpdatePacket.Action> actions = EnumSet.of(
                ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER,
                ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED,
                ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LATENCY
        );

        ClientboundPlayerInfoUpdatePacket packet = new ClientboundPlayerInfoUpdatePacket(actions, entry);

        for (Player player : sendTo) {
            ((CraftPlayer) player).getHandle().connection.send(packet);
        }
        fakePlayer.getBukkitEntity().getPlayer().playerListName(username);
    }

    /**
     * Builds a ServerPlayer instance configured similarly to your FakePlayer1214 method,
     * but without world spawning.
     */
    private static ServerPlayer createServerPlayer(String name, UUID uuid, int skinModel, String skinTexture, String skinSignature) {
        World world = Bukkit.getWorlds().get(0);
        MinecraftServer server = MinecraftServer.getServer();
        ServerLevel level = ((CraftWorld) world).getHandle();

        ClientInformation clientInfo = new ClientInformation(
                "en_us",
                2,
                ChatVisiblity.FULL,
                false,
                skinModel,
                HumanoidArm.RIGHT,
                true,
                true,
                ParticleStatus.ALL
        );

        // --- create fake player (NMS) ---
        ServerPlayer fakePlayer = new ServerPlayer(server, level, new GameProfile(uuid, name), clientInfo);

        // set a default position to avoid NPEs or zero-height crashes
        Location spawn = world.getSpawnLocation();
        fakePlayer.setPos(spawn.getX(), spawn.getY(), spawn.getZ());
        fakePlayer.setRot(0, 0);
        fakePlayer.setYHeadRot(0);

        // --- safely apply skin using Bukkit bridge ---
        CraftPlayer bukkitFake = fakePlayer.getBukkitEntity();
        if (bukkitFake != null) {
            PlayerProfile profile = Bukkit.createProfile(uuid, name);

            if (skinTexture != null && skinSignature != null) {
                profile.setProperty(new ProfileProperty("textures", skinTexture, skinSignature));
                bukkitFake.setPlayerProfile(profile);
            }
        }

        return fakePlayer;
    }


    /**
     * Removes a fake player from the tablist for all given viewers.
     */
    public static void removeTabPlayer(List<Player> sendTo, UUID uuid) {
        GameProfile profile = activeTabProfiles.remove(uuid);
        if (profile == null) return;

        for (Player player : sendTo) {
            ServerGamePacketListenerImpl conn = ((CraftPlayer) player).getHandle().connection;

            // Step 1: unlist
            ClientboundPlayerInfoUpdatePacket.Entry hideEntry = new ClientboundPlayerInfoUpdatePacket.Entry(
                    uuid,
                    profile,
                    false,
                    0,
                    GameType.DEFAULT_MODE,
                    Component.empty(),
                    false,
                    0,
                    null
            );

            ClientboundPlayerInfoUpdatePacket hidePacket = new ClientboundPlayerInfoUpdatePacket(
                    EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED),
                    hideEntry
            );

            conn.send(hidePacket);
        }

        // Step 2: remove after delay to avoid cache race
        Bukkit.getScheduler().runTaskLater(PoaPlugin1219.getPlugin(), () -> {
            ClientboundPlayerInfoRemovePacket removePacket = new ClientboundPlayerInfoRemovePacket(List.of(uuid));
            for (Player player : sendTo) {
                SendPacket1219.sendPacket(player, removePacket);
            }
        }, 3L);
    }

    /**
     * Removes all fake tablist entries that were spawned via this class.
     */
    public static void clearAll(List<Player> sendTo) {
        List<UUID> all = new ArrayList<>(activeTabProfiles.keySet());
        for (UUID uuid : all) {
            removeTabPlayer(sendTo, uuid);
        }
    }
}
