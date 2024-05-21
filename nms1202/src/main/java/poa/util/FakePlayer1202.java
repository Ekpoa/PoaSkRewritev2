package poa.util;

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
import org.bukkit.craftbukkit.v1_20_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import poa.FakeEntity1202;
import poa.SendPacket1202;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class FakePlayer1202 {

    public static Map<String, UUID> nameToUuid = new HashMap<>();
    public static Map<UUID, Integer> uuidToId = new HashMap<>();

    @SneakyThrows
    public static void spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency) {
        World world = Bukkit.getWorlds().get(0);
        MinecraftServer server = MinecraftServer.getServer();
        ServerLevel level = ((CraftWorld) world).getHandle();


        UUID uuid = UUID.randomUUID();
        if(nameToUuid.containsKey(name))
            uuid = nameToUuid.get(name);
        else
            nameToUuid.put(name, uuid);

        int id;
        if(uuidToId.containsKey(uuid))
            id = uuidToId.get(uuid);
        else{
            id = ThreadLocalRandom.current().nextInt(99999, Integer.MAX_VALUE -1);
            uuidToId.put(uuid, id);
        }


        ServerPlayer fakePlayer = new ServerPlayer(server, level, new GameProfile(uuid, name), ClientInformation.createDefault());
        fakePlayer.setPos(loc.getX(), loc.getY(), loc.getZ());


        GameProfile gameProfile = fakePlayer.getGameProfile();

        if(skinName != null && !skinName.isEmpty()) {
            UUID string = Bukkit.getOfflinePlayer(skinName).getUniqueId();

            gameProfile.getProperties().removeAll("textures");
            gameProfile.getProperties().put("textures", new Property("textures", FetchSkin1202.fetchSkinURL(string), FetchSkin1202.fetchSkinSignature(string)));
        }


        for (Player player : sendTo) {


            ServerGamePacketListenerImpl connection = ((CraftPlayer) player).getHandle().connection;
            ClientboundPlayerInfoUpdatePacket.Entry entry = new ClientboundPlayerInfoUpdatePacket.Entry(fakePlayer.getUUID(), gameProfile, listed, latency, GameType.DEFAULT_MODE, Component.empty(), null);
            ClientboundPlayerInfoUpdatePacket.Action addPlayer = ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER;
            connection.send(new ClientboundPlayerInfoUpdatePacket(EnumSet.of(addPlayer), entry));

            if(listed)
                connection.send(new ClientboundPlayerInfoUpdatePacket(EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED), entry));
            if(latency > 0)
                connection.send(new ClientboundPlayerInfoUpdatePacket(EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LATENCY), entry));

            fakePlayer.setId(id);

            ClientboundAddEntityPacket packet = new ClientboundAddEntityPacket(fakePlayer);


            connection.send(packet);
        }

    }

    @SneakyThrows
    public static void removeFakePlayerPacket(List<Player> sendTo, List<UUID> uuids) {
        for (Player p : sendTo) {
            List<Integer> list = new ArrayList<>();
            for (UUID uuid : uuids) {
                list.add(uuidToId.get(uuid));
            }

            SendPacket1202.sendPacket(p, new ClientboundPlayerInfoRemovePacket(uuids));
            SendPacket1202.sendPacket(p, FakeEntity1202.removeFakeEntityPacket(list));


        }
    }

}
