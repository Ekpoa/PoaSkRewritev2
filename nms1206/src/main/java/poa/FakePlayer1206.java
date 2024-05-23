package poa;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.SneakyThrows;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.level.GameType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import poa.util.FetchSkin1206;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class FakePlayer1206 {

    public static Map<String, UUID> nameToUuid = new HashMap<>();
    public static Map<UUID, Integer> uuidToId = new HashMap<>();

    @SneakyThrows
    public static void spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency, int id, UUID uuid) {
        World world = Bukkit.getWorlds().get(0);
        MinecraftServer server = MinecraftServer.getServer();
        ServerLevel level = ((CraftWorld) world).getHandle();



        if (nameToUuid.containsKey(name))
            uuid = nameToUuid.get(name);
        else
            nameToUuid.put(name, uuid);


        if (uuidToId.containsKey(uuid))
            id = uuidToId.get(uuid);
        else {

            uuidToId.put(uuid, id);
        }


        ServerPlayer fakePlayer = new ServerPlayer(server, level, new GameProfile(uuid, name), ClientInformation.createDefault());
        fakePlayer.setPos(loc.getX(), loc.getY(), loc.getZ());


        GameProfile gameProfile = fakePlayer.getGameProfile();

        if (skinName != null && !skinName.isEmpty()) {
            UUID string = Bukkit.getOfflinePlayer(skinName).getUniqueId();

            gameProfile.getProperties().removeAll("textures");
            gameProfile.getProperties().put("textures", new Property("textures", FetchSkin1206.fetchSkinURL(string), FetchSkin1206.fetchSkinSignature(string)));
        }


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

            ClientboundAddEntityPacket packet = new ClientboundAddEntityPacket(fakePlayer);


            connection.send(packet);
        }

    }


    public static void spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency, int id) {
        spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, UUID.randomUUID());
    }

    public static void spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency) {
        spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, ThreadLocalRandom.current().nextInt(99999, Integer.MAX_VALUE - 1));
    }

    @SneakyThrows
    public static void removeFakePlayerPacket(List<Player> sendTo, List<UUID> uuids) {
        for (Player p : sendTo) {
            List<Integer> list = new ArrayList<>();
            for (UUID uuid : uuids) {
                list.add(uuidToId.get(uuid));
            }

            SendPacket1206.sendPacket(p, new ClientboundPlayerInfoRemovePacket(uuids));
            SendPacket1206.sendPacket(p, FakeEntity1206.removeFakeEntityPacket(list));


        }
    }

}
