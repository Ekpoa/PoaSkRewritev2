package poa.packets;


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.SneakyThrows;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ParticleStatus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
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

import poa.util.FetchSkin1216;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

public class FakePlayer1216 {


    @SneakyThrows
    public static Player spawnFakePlayer(List<Player> sendTo, String name, String skinTexture, String skinSignature, Location loc, boolean listed, int latency, int id, UUID uuid, int skinModel) {
        final ServerPlayer fakePlayer = createServerPlayer(loc, name, uuid, skinModel, listed, skinTexture, skinSignature);
        fakePlayer.setId(id);

        //fakePlayer.connection = new ServerGamePacketListenerImpl(server, new Connection(PacketFlow.CLIENTBOUND), fakePlayer, new CommonListenerCookie(gameProfile, 0, clientInformation, false));

        final com.mojang.authlib.GameProfile gameProfile = fakePlayer.getGameProfile();

        ClientboundPlayerInfoUpdatePacket.Entry entry = new ClientboundPlayerInfoUpdatePacket.Entry(fakePlayer.getUUID(), gameProfile, listed, latency, GameType.DEFAULT_MODE, Component.empty(), true, 0, null);
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

            if (skinTexture != null)
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


        fakePlayer.connection = new ServerGamePacketListenerImpl(MinecraftServer.getServer(), new Connection(PacketFlow.CLIENTBOUND), fakePlayer, new CommonListenerCookie(fakePlayer.getGameProfile(), 1, fakePlayer.clientInformation(), true));


        GameProfile gameProfile = fakePlayer.getGameProfile();

        if (skinSignature == null) {
            Map<String, String> signed = fromMineskin(skinTexture); // accepts base64 texture
            skinTexture = signed.get("texture");
            skinSignature = signed.get("signature");
        }

        if (skinTexture != null && skinSignature != null) {
            gameProfile.getProperties().removeAll("textures");
            gameProfile.getProperties().put("textures", new Property("textures", skinTexture, skinSignature));
        }

        return fakePlayer;
    }

    public static void spawnTablistOnly(List<Player> sendTo, String name, net.kyori.adventure.text.Component tablistName, UUID uuid, String skinTexture, String skinSignature, int latency, int tabPosition) {
        final Location loc = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);



        final ServerPlayer fakePlayer = createServerPlayer(loc, name, uuid, 127, true, skinTexture, skinSignature);

        final GameProfile gameProfile = fakePlayer.getGameProfile();

        fakePlayer.listOrder = tabPosition;

        ClientboundPlayerInfoUpdatePacket.Entry entry = new ClientboundPlayerInfoUpdatePacket.Entry(fakePlayer.getUUID(), gameProfile, true, latency, GameType.DEFAULT_MODE, Component.empty(), true, tabPosition, null);

        final EnumSet<ClientboundPlayerInfoUpdatePacket.Action> enumSet = EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED,
                ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LATENCY,
                ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER,
                ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LIST_ORDER);




        final ClientboundPlayerInfoUpdatePacket actionsPacket = new ClientboundPlayerInfoUpdatePacket(enumSet, entry);


        for (Player player : sendTo) {
            ServerGamePacketListenerImpl connection = ((CraftPlayer) player).getHandle().connection;
            connection.send(actionsPacket);

        }
        fakePlayer.getBukkitEntity().getPlayer().playerListName(tablistName);
    }

    public static void spawnTablistOnly(List<Player> sendTo, String name, net.kyori.adventure.text.Component tablistName, UUID uuid, String skinTexture, String skinSignature, int latency){
        spawnTablistOnly(sendTo, name, tablistName, uuid, skinTexture, skinSignature, latency, -1);
    }



    public static void spawnTablistOnly(List<Player> sendTo, String name, net.kyori.adventure.text.Component tablistName, String skinName, UUID uuid, int latency, int tablistPosition){
        UUID string = Bukkit.getOfflinePlayer(skinName).getUniqueId();
        String texture = FetchSkin1216.fetchSkinURL(string);
        String signature = FetchSkin1216.fetchSkinSignature(string);
        spawnTablistOnly(sendTo, name, tablistName, uuid, texture, signature, latency, tablistPosition);
    }

    public static void spawnTablistOnly(List<Player> sendTo, String name, net.kyori.adventure.text.Component tablistName, String skinName, UUID uuid, int latency){
        spawnTablistOnly(sendTo, name, tablistName, skinName, uuid, latency, 0);
    }


    public static void removeTablistPacket(List<Player> sendTo, List<UUID> uuids) {
        final ClientboundPlayerInfoRemovePacket packet = new ClientboundPlayerInfoRemovePacket(uuids);
        for (Player p : sendTo) {
            SendPacket1216.sendPacket(p, packet);
        }
    }


    public static Player spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency, int id, UUID uuid, int skinModel) {
        UUID string = Bukkit.getOfflinePlayer(skinName).getUniqueId();
        String texture = FetchSkin1216.fetchSkinURL(string);
        String signature = FetchSkin1216.fetchSkinSignature(string);

        return spawnFakePlayer(sendTo, name, texture, signature, loc, listed, latency, id, uuid, skinModel);
    }

    public static Player spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency, int id, UUID uuid) {
        return spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid, 127);
    }

    public static Player spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency, int id) {
        return spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, UUID.randomUUID());
    }

    public static Player spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency) {
        return spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, ThreadLocalRandom.current().nextInt(99999, Integer.MAX_VALUE - 1));
    }

    @SneakyThrows
    public static void removeFakePlayerPacket(List<Player> sendTo, List<UUID> uuids, List<Integer> ids) {
        final ClientboundPlayerInfoRemovePacket packet = new ClientboundPlayerInfoRemovePacket(uuids);
        for (Player p : sendTo) {
            SendPacket1216.sendPacket(p, packet);
            SendPacket1216.sendPacket(p, FakeEntity1216.removeFakeEntityPacket(ids));
        }
    }

    private static final Map<String, String> texturesToSignatures = new HashMap<>();

    @SneakyThrows
    public static Map<String, String> fromMineskin(String base64Texture) {
        Map<String, String> result = new HashMap<>();

        if(texturesToSignatures.containsKey(base64Texture)){
            result.put(base64Texture, texturesToSignatures.get(base64Texture));
            return result;
        }

        // Decode the base64 texture to extract the internal URL
        String decoded = new String(Base64.getDecoder().decode(base64Texture), java.nio.charset.StandardCharsets.UTF_8);
        org.json.JSONObject json = new org.json.JSONObject(decoded);
        String textureUrl = json.getJSONObject("textures").getJSONObject("SKIN").getString("url");

        // Send to Mineskin API
        String payload = "{\"variant\":\"classic\",\"visibility\":0,\"url\":\"" + textureUrl + "\"}";
        java.net.URL apiUrl = new java.net.URL("https://api.mineskin.org/generate/url");
        java.net.HttpURLConnection connection = (java.net.HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(20000);

        try (java.io.OutputStream os = connection.getOutputStream()) {
            os.write(payload.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        }

        int code = connection.getResponseCode();
        String response = new String(
                (code == 200 ? connection.getInputStream() : connection.getErrorStream())
                        .readAllBytes(),
                java.nio.charset.StandardCharsets.UTF_8
        );

        if (code != 200)
            throw new IOException("Mineskin API returned HTTP " + code + ": " + response);

        org.json.JSONObject textureJson = new org.json.JSONObject(response)
                .getJSONObject("data").getJSONObject("texture");


        result.put("texture", textureJson.getString("value"));
        result.put("signature", textureJson.getString("signature"));
        return result;
    }



}
