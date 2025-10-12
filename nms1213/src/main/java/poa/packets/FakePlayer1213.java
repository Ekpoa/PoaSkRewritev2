package poa.packets;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.SneakyThrows;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.LocalChatSession;
import net.minecraft.network.chat.RemoteChatSession;
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
import net.minecraft.world.entity.player.ProfileKeyPair;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import org.bukkit.plugin.Plugin;
import org.json.JSONObject;
import poa.util.FetchSkin1213;
import poa.util.PoaPlugin1213;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

public class FakePlayer1213 {


    @SneakyThrows
    public static Player spawnFakePlayer(List<Player> sendTo, String name, String skinTexture, String skinSignature, Location loc, boolean listed, int latency, int id, UUID uuid, int skinModel) {
        final ServerPlayer fakePlayer = createServerPlayer(sendTo, id,loc, name, uuid, skinModel, listed, skinTexture, skinSignature);
        fakePlayer.setId(id);

        GameProfile gameProfile = fakePlayer.getGameProfile();

        if (skinTexture != null || skinSignature == null) {
            gameProfile.getProperties().removeAll("textures");
            gameProfile.getProperties().put("textures", new Property("textures", skinTexture, skinSignature));

        }

        //fakePlayer.connection = new ServerGamePacketListenerImpl(server, new Connection(PacketFlow.CLIENTBOUND), fakePlayer, new CommonListenerCookie(gameProfile, 0, clientInformation, false));

        for (Player player : sendTo) {
            ServerGamePacketListenerImpl connection = ((CraftPlayer) player).getHandle().connection;
            ClientboundPlayerInfoUpdatePacket.Entry entry = new ClientboundPlayerInfoUpdatePacket.Entry(fakePlayer.getUUID(), gameProfile, listed, latency, GameType.DEFAULT_MODE, Component.empty(), 0, null);
            ClientboundPlayerInfoUpdatePacket.Action addPlayer = ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER;
            connection.send(new ClientboundPlayerInfoUpdatePacket(EnumSet.of(addPlayer), entry));

            if (listed)
                connection.send(new ClientboundPlayerInfoUpdatePacket(EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LISTED), entry));
            if (latency > 0)
                connection.send(new ClientboundPlayerInfoUpdatePacket(EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_LATENCY), entry));

            fakePlayer.setId(id);

            ClientboundAddEntityPacket packet = new ClientboundAddEntityPacket(fakePlayer.getId(), fakePlayer.getUUID(), loc.getX(), loc.getY(), loc.getZ(), loc.getPitch(), loc.getYaw(), fakePlayer.getType(), 0, fakePlayer.getDeltaMovement(), fakePlayer.getYHeadRot());

           // new ClientboundAddEntityPacket(fakePlayer, 0, new BlockPos(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));

            connection.send(packet);

            if (skinTexture != null) {
                connection.send(new ClientboundSetEntityDataPacket(id, fakePlayer.getEntityData().packAll()));
            }
        }

        Player tr;
        try {
            tr = fakePlayer.getBukkitEntity().getPlayer();
        }
        catch (Exception e){
            Bukkit.getLogger().log(Level.WARNING, "Failed to create bukkit entity for fake player");
            tr = null;
        }
        return tr;
    }




    public static ServerPlayer createServerPlayer(List<Player> sendTo, int id, Location loc, String name, UUID uuid, int skinModel, boolean listed, String skinTexture, String skinSignature) {
        boolean isCached = texturesToSignatures.containsKey(skinTexture);

        UUID randomUUID;

        if (!isCached)
            randomUUID = UUID.randomUUID();
        else {
            randomUUID = uuid;
        }
        World world = Bukkit.getWorlds().get(0);
        MinecraftServer server = MinecraftServer.getServer();
        ServerLevel level = ((CraftWorld) world).getHandle();
        ClientInformation clientInformation = new ClientInformation("en_us", 2, ChatVisiblity.FULL, false, skinModel, HumanoidArm.RIGHT, true, listed, ParticleStatus.ALL);
        ServerPlayer fakePlayer;

        if (!isCached)
            fakePlayer = new ServerPlayer(server, level, new GameProfile(randomUUID, name), clientInformation);
        else
            fakePlayer = new ServerPlayer(server, level, new GameProfile(uuid, name), clientInformation);

        fakePlayer.setPos(new Vec3(loc.getX(), loc.getY(), loc.getZ()));
        fakePlayer.setRot(loc.getYaw(), loc.getPitch());
        fakePlayer.setYHeadRot(loc.getYaw());


        GameProfile gameProfile = fakePlayer.getGameProfile();

        final String[] sig = {skinSignature};
        if (skinSignature == null) {
            fromMineskin(skinTexture).thenAccept(signed -> {
                String newTexture = signed.get("texture");
                sig[0] = signed.get("signature");

                if (skinTexture != null && sig[0] != null) {
                    gameProfile.getProperties().removeAll("textures");
                    gameProfile.getProperties().put("textures", new Property("textures", newTexture, sig[0]));
                }

                if (!isCached) {
                    removeFakePlayerPacket(sendTo, List.of(randomUUID), List.of(id));

                    Bukkit.getScheduler().runTaskLater(PoaPlugin1213.getPlugin(), () -> {
                        spawnFakePlayer(sendTo, name, newTexture, sig[0], loc, listed, 0, id, uuid, skinModel);

                    }, 4L);
                }
            });
        }
        else {
            gameProfile.getProperties().removeAll("textures");
            gameProfile.getProperties().put("textures", new Property("textures", skinTexture, skinSignature));
        }

        return fakePlayer;
    }

    public static void spawnTablistOnly(List<Player> sendTo, String name, net.kyori.adventure.text.Component tablistName,UUID uuid, String skinTexture, String skinSignature, int latency) {
        final Location loc = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
        final ServerPlayer fakePlayer = createServerPlayer(sendTo, 9999, loc, name, uuid, 127, true, skinTexture, skinSignature);

        final GameProfile gameProfile = fakePlayer.getGameProfile();


        ClientboundPlayerInfoUpdatePacket.Entry entry = new ClientboundPlayerInfoUpdatePacket.Entry(fakePlayer.getUUID(), gameProfile, true, latency, GameType.DEFAULT_MODE, Component.empty(), 1, null);
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
        fakePlayer.getBukkitEntity().getPlayer().playerListName(tablistName);
    }

    public static void spawnTablistOnly(List<Player> sendTo, String name, net.kyori.adventure.text.Component tablistName, String skinName, UUID uuid, int latency){
        UUID string = Bukkit.getOfflinePlayer(skinName).getUniqueId();
        String texture = FetchSkin1213.fetchSkinURL(string);
        String signature = FetchSkin1213.fetchSkinSignature(string);
        spawnTablistOnly(sendTo, name, tablistName, uuid, texture, signature, latency);
    }

    public static void removeTablistPacket(List<Player> sendTo, List<UUID> uuids) {
        final ClientboundPlayerInfoRemovePacket packet = new ClientboundPlayerInfoRemovePacket(uuids);
        for (Player p : sendTo) {
            SendPacket1213.sendPacket(p, packet);
        }
    }



    public static void spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency, int id, UUID uuid, int skinModel) {
        UUID string = Bukkit.getOfflinePlayer(skinName).getUniqueId();
        String texture = FetchSkin1213.fetchSkinURL(string);
        String signature = FetchSkin1213.fetchSkinSignature(string);

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
            SendPacket1213.sendPacket(p, new ClientboundPlayerInfoRemovePacket(uuids));
            SendPacket1213.sendPacket(p, FakeEntity1213.removeFakeEntityPacket(ids));

        }
    }
    private static final Map<String, Map<String, String>> texturesToSignatures = new HashMap<>();
    private static boolean CACHE_TO_DISK = false;

    private static File cacheFile;
    private static FileConfiguration cacheYML;

    static {
        final Plugin plugin = PoaPlugin1213.getPlugin();
        final FileConfiguration config = plugin.getConfig();
        if (config.isSet("SaveSkinCacheToDisk") && config.getBoolean("SaveSkinCacheToDisk")){
            CACHE_TO_DISK = true;
            cacheFile = new File(plugin.getDataFolder(), "SkinCache.yml");
            try {
                cacheFile.createNewFile();
                cacheYML = YamlConfiguration.loadConfiguration(cacheFile);

                if(cacheYML.isConfigurationSection("SkinCache")){
                    for (String oldTexture : cacheYML.getConfigurationSection("SkinCache").getKeys(false)) {
                        Map<String, String> innerMap = new HashMap<>();

                        innerMap.put("texture", cacheYML.getString("SkinCache." + oldTexture + ".RealTexture"));
                        innerMap.put("signature", cacheYML.getString("SkinCache." + oldTexture + ".Signature"));
                        texturesToSignatures.put(oldTexture, innerMap);
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


    @SneakyThrows
    public static CompletableFuture<Map<String, String>> fromMineskin(String base64Texture) {
        final CompletableFuture<Map<String, String>> future = new CompletableFuture<>();

        Map<String, String> result = new HashMap<>();
        if (texturesToSignatures.containsKey(base64Texture)) {
            future.complete(texturesToSignatures.get(base64Texture));
        } else {
            Bukkit.getScheduler().runTaskAsynchronously(PoaPlugin1213.getPlugin(), () -> {
                String decoded = new String(Base64.getDecoder().decode(base64Texture), StandardCharsets.UTF_8);
                JSONObject json = new JSONObject(decoded);
                String textureUrl = json.getJSONObject("textures").getJSONObject("SKIN").getString("url");
                String payload = "{\"variant\":\"classic\",\"visibility\":0,\"url\":\"" + textureUrl + "\"}";
                URL apiUrl = null;
                try {
                    apiUrl = new URL("https://api.mineskin.org/generate/url");
                    HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);
                    connection.setConnectTimeout(10000);
                    connection.setReadTimeout(20000);
                    try (OutputStream os = connection.getOutputStream()) {
                        os.write(payload.getBytes(StandardCharsets.UTF_8));
                    }
                    int code = connection.getResponseCode();
                    String response = new String((code == 200 ? connection.getInputStream() : connection.getErrorStream()).readAllBytes(), StandardCharsets.UTF_8);
                    if (code != 200) throw new IOException("Mineskin API returned HTTP " + code + ": " + response);
                    JSONObject textureJson = new JSONObject(response).getJSONObject("data").getJSONObject("texture");
                    final String newTexture = textureJson.getString("value");
                    result.put("texture", newTexture);
                    final String signature = textureJson.getString("signature");
                    result.put("signature", signature);

                    texturesToSignatures.put(base64Texture, result);

                    if(CACHE_TO_DISK){
                        cacheYML.set("SkinCache." + base64Texture + ".RealTexture", newTexture);
                        cacheYML.set("SkinCache." + base64Texture + ".Signature", signature);
                        cacheYML.save(cacheFile);
                    }

                    future.complete(result);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return future;
    }



}
