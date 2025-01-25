package poa.packets;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.SneakyThrows;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ParticleStatus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.ChatVisiblity;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import poa.util.FetchSkin1214;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

public class FakePlayer1214 {


    @SneakyThrows
    public static Player spawnFakePlayer(List<Player> sendTo, String name, String skinTexture, String skinSignature, Location loc, boolean listed, int latency, int id, UUID uuid, int skinModel) {
        final ServerPlayer fakePlayer = createServerPlayer(loc, name, uuid, skinModel, listed, skinTexture, skinSignature);
        fakePlayer.setId(id);

        //fakePlayer.connection = new ServerGamePacketListenerImpl(server, new Connection(PacketFlow.CLIENTBOUND), fakePlayer, new CommonListenerCookie(gameProfile, 0, clientInformation, false));

        final GameProfile gameProfile = fakePlayer.getGameProfile();

        ClientboundPlayerInfoUpdatePacket.Entry entry = new ClientboundPlayerInfoUpdatePacket.Entry(fakePlayer.getUUID(), gameProfile, listed, latency, GameType.DEFAULT_MODE, Component.empty(), true, 1, null);
        ClientboundPlayerInfoUpdatePacket.Action addPlayer = ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER;
        final ClientboundPlayerInfoUpdatePacket updatePacket = new ClientboundPlayerInfoUpdatePacket(EnumSet.of(addPlayer), entry);

        ClientboundAddEntityPacket spawnPacket = new ClientboundAddEntityPacket(fakePlayer.getId(), fakePlayer.getUUID(), loc.getX(), loc.getY(), loc.getZ(), loc.getPitch(), loc.getYaw(), fakePlayer.getType(), 0, fakePlayer.getDeltaMovement(), fakePlayer.getYHeadRot());

        final ClientboundSetEntityDataPacket entityDataPacket = new ClientboundSetEntityDataPacket(id, fakePlayer.getEntityData().packAll());

        final ClientboundPlayerInfoUpdatePacket listPacket = new ClientboundPlayerInfoUpdatePacket(EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED), entry);

        final ClientboundPlayerInfoUpdatePacket latencyPacket = new ClientboundPlayerInfoUpdatePacket(EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LATENCY), entry);

        for (Player player : sendTo) {
            ServerGamePacketListenerImpl connection = ((CraftPlayer) player).getHandle().connection;
            connection.send(updatePacket);

            if (listed)
                connection.send(listPacket);
            if (latency > 0)
                connection.send(latencyPacket);

            connection.send(spawnPacket);

            if (skinTexture != null || skinSignature == null)
                connection.send(entityDataPacket);

        }

        Player tr;
        try {
            tr = fakePlayer.getBukkitEntity().getPlayer();
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.WARNING, "Failed to create bukkit entity for fake player");
            tr = null;
        }
        return tr;
    }


    public static ServerPlayer createServerPlayer(Location loc, String name, UUID uuid, int skinModel, boolean listed, String skinTexture, String skinSignature) {
        World world = Bukkit.getWorlds().get(0);
        MinecraftServer server = MinecraftServer.getServer();
        ServerLevel level = ((CraftWorld) world).getHandle();
        ClientInformation clientInformation = new ClientInformation("en_us", 2, ChatVisiblity.FULL, false, skinModel, HumanoidArm.RIGHT, true, listed, ParticleStatus.ALL);
        ServerPlayer fakePlayer = new ServerPlayer(server, level, new GameProfile(uuid, name), clientInformation);
        fakePlayer.setPos(new Vec3(loc.getX(), loc.getY(), loc.getZ()));
        fakePlayer.setRot(loc.getYaw(), loc.getPitch());
        fakePlayer.setYHeadRot(loc.getYaw());


        GameProfile gameProfile = fakePlayer.getGameProfile();

        if (skinTexture != null || skinSignature == null) {
            gameProfile.getProperties().removeAll("textures");
            gameProfile.getProperties().put("textures", new Property("textures", skinTexture, skinSignature));
        }

        return fakePlayer;
    }

    public static void spawnTablistOnly(List<Player> sendTo, String name, UUID uuid, String skinTexture, String skinSignature, int latency) {
        final Location loc = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
        final ServerPlayer fakePlayer = createServerPlayer(loc, name, uuid, 127, true, skinTexture, skinSignature);

        final GameProfile gameProfile = fakePlayer.getGameProfile();


        ClientboundPlayerInfoUpdatePacket.Entry entry = new ClientboundPlayerInfoUpdatePacket.Entry(fakePlayer.getUUID(), gameProfile, true, latency, GameType.DEFAULT_MODE, Component.empty(), true, 1, null);
        ClientboundPlayerInfoUpdatePacket.Action addPlayer = ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER;

        final ClientboundPlayerInfoUpdatePacket listPacket = new ClientboundPlayerInfoUpdatePacket(EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED), entry);

        final ClientboundPlayerInfoUpdatePacket latencyPacket = new ClientboundPlayerInfoUpdatePacket(EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LATENCY), entry);

        final ClientboundPlayerInfoUpdatePacket updatePacket = new ClientboundPlayerInfoUpdatePacket(EnumSet.of(addPlayer), entry);



        for (Player player : sendTo) {
            ServerGamePacketListenerImpl connection = ((CraftPlayer) player).getHandle().connection;
            connection.send(updatePacket);

            connection.send(listPacket);

            if (latency > 0)
                connection.send(latencyPacket);

        }
    }

    public static void spawnTablistOnly(List<Player> sendTo, String name, String skinName, UUID uuid, int latency){
        UUID string = Bukkit.getOfflinePlayer(skinName).getUniqueId();
        String texture = FetchSkin1214.fetchSkinURL(string);
        String signature = FetchSkin1214.fetchSkinSignature(string);
        spawnTablistOnly(sendTo, name, uuid, texture, signature, latency);
    }

    public static void removeTablistPacket(List<Player> sendTo, List<UUID> uuids) {
        final ClientboundPlayerInfoRemovePacket packet = new ClientboundPlayerInfoRemovePacket(uuids);
        for (Player p : sendTo) {
            SendPacket1214.sendPacket(p, packet);
        }
    }


    public static void spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency, int id, UUID uuid, int skinModel) {
        UUID string = Bukkit.getOfflinePlayer(skinName).getUniqueId();
        String texture = FetchSkin1214.fetchSkinURL(string);
        String signature = FetchSkin1214.fetchSkinSignature(string);

        spawnFakePlayer(sendTo, name, texture, signature, loc, listed, latency, id, uuid, skinModel);
    }

    public static void spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency, int id, UUID uuid) {
        spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid, 127);
    }

    public static void spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency, int id) {
        spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, UUID.randomUUID());
    }

    public static void spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency) {
        spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, ThreadLocalRandom.current().nextInt(99999, Integer.MAX_VALUE - 1));
    }

    @SneakyThrows
    public static void removeFakePlayerPacket(List<Player> sendTo, List<UUID> uuids, List<Integer> ids) {
        final ClientboundPlayerInfoRemovePacket packet = new ClientboundPlayerInfoRemovePacket(uuids);
        for (Player p : sendTo) {
            SendPacket1214.sendPacket(p, packet);
            SendPacket1214.sendPacket(p, FakeEntity1214.removeFakeEntityPacket(ids));
        }
    }

}
