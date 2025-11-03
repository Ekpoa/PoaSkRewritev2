package poa.packets;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
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
import poa.util.Components12110;
import poa.util.FetchSkin12110;
import poa.util.PoaPlugin12110;

import java.util.*;
import java.util.logging.Level;

public class FakeTablist12110 {

    private static final Map<UUID, GameProfile> activeTabProfiles = new HashMap<>();


    private static void addTabPlayer(List<Player> sendTo, String name, net.kyori.adventure.text.Component username, String texture, String signature, UUID uuid, int latency, int skinModel) {
        Bukkit.getScheduler().runTaskAsynchronously(PoaPlugin12110.getPlugin(), () -> {
            Bukkit.getScheduler().runTask(PoaPlugin12110.getPlugin(), () ->
                    spawnTabEntry(sendTo, name, username, uuid, texture, signature, latency, skinModel)
            );

        });
    }

    public static void addTabPlayer(List<Player> sendTo, String name, net.kyori.adventure.text.Component username, String skinName, UUID uuid, int latency, int skinModel) {
        Bukkit.getScheduler().runTaskAsynchronously(PoaPlugin12110.getPlugin(), () -> {
            if (skinName.length() > 16) {
                if (!isValidBase64(name)) {
                    Bukkit.getLogger().log(Level.WARNING, name + " is not greater than 16 chars and does not match a base64 encode. Use texture or shorter name. Name is designed for sorting. Username is what shows");
                    return;
                }

                FakePlayer12110.fromMineskin(skinName).thenAccept(map -> {
                    String newTexture = map.get("texture");
                    String newSignature = map.get("signature");

                    Bukkit.getScheduler().runTask(PoaPlugin12110.getPlugin(), () ->
                            spawnTabEntry(sendTo, name, username, uuid, newTexture, newSignature, latency, skinModel)
                    );
                });
                return;
            }


            UUID skinUUID = Bukkit.getOfflinePlayer(skinName).getUniqueId();
            String texture = FetchSkin12110.fetchSkinURL(skinUUID);
            String signature = FetchSkin12110.fetchSkinSignature(skinUUID);
            if (texture == null || signature == null) {
                Bukkit.getLogger().log(Level.WARNING, "Failed to fetch skin for " + skinName);
                return;
            }
            addTabPlayer(sendTo, name, username, texture, signature, uuid, latency, skinModel);
        });
    }


    private static boolean isValidBase64(String input) {
        if (input == null || input.isEmpty()) return false;

        // Quick sanity check for allowed characters (Base64 + padding)
        if (!input.matches("^[A-Za-z0-9+/=\\r\\n]+$")) return false;

        try {
            Base64.getDecoder().decode(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


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
                Components12110.nmsComponentActual(username),
                true,
                0,
                null
        );

        EnumSet<ClientboundPlayerInfoUpdatePacket.Action> actions = EnumSet.of(
                ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER,
                ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED,
                ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LATENCY,
                ClientboundPlayerInfoUpdatePacket.Action.UPDATE_DISPLAY_NAME
        );

        ClientboundPlayerInfoUpdatePacket packet = new ClientboundPlayerInfoUpdatePacket(actions, entry);

        for (Player player : sendTo) {
            ((CraftPlayer) player).getHandle().connection.send(packet);
        }
        fakePlayer.getBukkitEntity().getPlayer().playerListName(username);

    }


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
        Bukkit.getScheduler().runTaskLater(PoaPlugin12110.getPlugin(), () -> {
            ClientboundPlayerInfoRemovePacket removePacket = new ClientboundPlayerInfoRemovePacket(List.of(uuid));
            for (Player player : sendTo) {
                SendPacket12110.sendPacket(player, removePacket);
            }
        }, 3L);
    }


    public static void clearAll(List<Player> sendTo) {
        List<UUID> all = new ArrayList<>(activeTabProfiles.keySet());
        for (UUID uuid : all) {
            removeTabPlayer(sendTo, uuid);
        }
    }
}
