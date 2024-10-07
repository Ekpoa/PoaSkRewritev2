package poa.packets;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.SneakyThrows;
import net.minecraft.core.BlockPos;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
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
import org.checkerframework.framework.qual.PostconditionAnnotation;
import poa.util.FetchSkin1206;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class FakePlayer1206 {





    @SneakyThrows
    public static Player spawnFakePlayer(List<Player> sendTo, String name, String skinTexture, String skinSignature, Location loc, boolean listed, int latency, int id, UUID uuid, int skinModel) {
        World world = Bukkit.getWorlds().get(0);
        MinecraftServer server = MinecraftServer.getServer();
        ServerLevel level = ((CraftWorld) world).getHandle();


        ClientInformation clientInformation = new ClientInformation("en_us", 2, ChatVisiblity.FULL, false, skinModel, HumanoidArm.RIGHT, true, listed);
        ServerPlayer fakePlayer = new ServerPlayer(server, level, new GameProfile(uuid, name), clientInformation);
        fakePlayer.setPos(loc.getX(), loc.getY(), loc.getZ());
        fakePlayer.setRot(loc.getYaw(), loc.getPitch());
        fakePlayer.setYHeadRot(loc.getYaw());

        GameProfile gameProfile = fakePlayer.getGameProfile();

        if (skinTexture != null || skinSignature == null) {
            gameProfile.getProperties().removeAll("textures");
            gameProfile.getProperties().put("textures", new Property("textures", skinTexture, skinSignature));

        }

        //fakePlayer.connection = new ServerGamePacketListenerImpl(server, new Connection(PacketFlow.CLIENTBOUND), fakePlayer, new CommonListenerCookie(gameProfile, 0, clientInformation, false));

        for (Player player : sendTo) {
            ServerGamePacketListenerImpl connection = ((CraftPlayer) player).getHandle().connection;
            ClientboundPlayerInfoUpdatePacket.Entry entry = new ClientboundPlayerInfoUpdatePacket.Entry(fakePlayer.getUUID(), gameProfile, listed, latency, GameType.DEFAULT_MODE, Component.empty(), null);
            ClientboundPlayerInfoUpdatePacket.Action addPlayer = ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER;
            connection.send(new ClientboundPlayerInfoUpdatePacket(EnumSet.of(addPlayer), entry));

            if (listed)
                connection.send(new ClientboundPlayerInfoUpdatePacket(EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED), entry));
            if (latency > 0)
                connection.send(new ClientboundPlayerInfoUpdatePacket(EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LATENCY), entry));

            fakePlayer.setId(id);

            ClientboundAddEntityPacket packet = new ClientboundAddEntityPacket(fakePlayer, 0, new BlockPos(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));

            connection.send(packet);

            if (skinTexture != null || skinSignature == null) {
                connection.send(new ClientboundSetEntityDataPacket(id, fakePlayer.getEntityData().packAll()));
            }
        }
        Player tr;
        try {
            tr = fakePlayer.getBukkitEntity().getPlayer();
        }
        catch (Exception e){
            System.out.println("Failed to create bukkit entity for fake player");
            tr = null;
        }
        return tr;
    }

    public static void spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency, int id, UUID uuid, int skinModel) {
        UUID string = Bukkit.getOfflinePlayer(skinName).getUniqueId();
        String texture = FetchSkin1206.fetchSkinURL(string);
        String signature = FetchSkin1206.fetchSkinSignature(string);

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
        for (Player p : sendTo) {
            SendPacket1206.sendPacket(p, new ClientboundPlayerInfoRemovePacket(uuids));
            SendPacket1206.sendPacket(p, FakeEntity1206.removeFakeEntityPacket(ids));

        }
    }

}
