package poa.packets.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import poa.packets.packetListener.*;
import poa.util.BukkitVersion;

import java.util.HashMap;
import java.util.Map;

public class PacketInjector {
    private static final Map<Player, PacketInjector12111> playerPacketStuffMap12111 = new HashMap<>();
    private static final Map<Player, PacketInjector12110> playerPacketStuffMap12110 = new HashMap<>();
    private static final Map<Player, PacketInjector1219> playerPacketStuffMap1219 = new HashMap<>();
    private static final Map<Player, PacketInjector1218> playerPacketStuffMap1218 = new HashMap<>();
    private static final Map<Player, PacketInjector1217> playerPacketStuffMap1217 = new HashMap<>();
    private static final Map<Player, PacketInjector1216> playerPacketStuffMap1216 = new HashMap<>();
    private static final Map<Player, PacketInjector1215> playerPacketStuffMap1215 = new HashMap<>();
    private static final Map<Player, PacketInjector1214> playerPacketStuffMap1214 = new HashMap<>();
    private static final Map<Player, PacketInjector1213> playerPacketStuffMap1213 = new HashMap<>();
    private static final Map<Player, PacketInjector1211> playerPacketStuffMap1211 = new HashMap<>();
    private static final Map<Player, PacketInjector121> playerPacketStuffMap121 = new HashMap<>();
    private static final Map<Player, PacketInjector1206> playerPacketStuffMap1206 = new HashMap<>();
    private static final Map<Player, PacketInjector1204> playerPacketStuffMap1204 = new HashMap<>();
    private static final Map<Player, PacketInjector1202> playerPacketStuffMap1202 = new HashMap<>();


    public static void inject(Player player, String id){
        switch (BukkitVersion.getBukkitVersion()) {
            case "1204" -> {
                PacketInjector1204 packetInjector = new PacketInjector1204(player, id);
                packetInjector.inject(player);
                playerPacketStuffMap1204.put(player, packetInjector);
            }
            case "1206" -> {
                PacketInjector1206 packetInjector = new PacketInjector1206(player, id);
                packetInjector.inject(player);
                playerPacketStuffMap1206.put(player, packetInjector);
            }
            case "121" -> {
                PacketInjector121 packetInjector = new PacketInjector121(player, id);
                packetInjector.inject(player);
                playerPacketStuffMap121.put(player, packetInjector);
            }
            case "1211" -> {
                PacketInjector1211 packetInjector = new PacketInjector1211(player, id);
                packetInjector.inject(player);
                playerPacketStuffMap1211.put(player, packetInjector);
            }
            case "1213" -> {
                PacketInjector1213 packetInjector = new PacketInjector1213(player, id);
                packetInjector.inject(player);
                playerPacketStuffMap1213.put(player, packetInjector);
            }
            case "1214" -> {
                PacketInjector1214 packetInjector = new PacketInjector1214(player, id);
                packetInjector.inject(player);
                playerPacketStuffMap1214.put(player, packetInjector);
            }
            case "1215" -> {
                PacketInjector1215 packetInjector = new PacketInjector1215(player, id);
                packetInjector.inject(player);
                playerPacketStuffMap1215.put(player, packetInjector);
            }
            case "1216" -> {
                PacketInjector1216 packetInjector = new PacketInjector1216(player, id);
                packetInjector.inject(player);
                playerPacketStuffMap1216.put(player, packetInjector);
            }
            case "1217" -> {
                PacketInjector1217 packetInjector = new PacketInjector1217(player, id);
                packetInjector.inject(player);
                playerPacketStuffMap1217.put(player, packetInjector);
            }
            case "1218" -> {
                PacketInjector1218 packetInjector = new PacketInjector1218(player, id);
                packetInjector.inject(player);
                playerPacketStuffMap1218.put(player, packetInjector);
            }
            case "1219" -> {
                PacketInjector1219 packetInjector = new PacketInjector1219(player, id);
                packetInjector.inject(player);
                playerPacketStuffMap1219.put(player, packetInjector);
            }
            case "12110" -> {
                PacketInjector12110 packetInjector = new PacketInjector12110(player, id);
                packetInjector.inject(player);
                playerPacketStuffMap12110.put(player, packetInjector);
            }
            case "12111" -> {
                PacketInjector12111 packetInjector = new PacketInjector12111(player, id);
                packetInjector.inject(player);
                playerPacketStuffMap12111.put(player, packetInjector);
            }
        }
    }

    public static void inject(PlayerLoginEvent e, String id){
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> {
                final Player player = e.getPlayer();
                PacketInjector1202 packetInjector = new PacketInjector1202(player, e.getAddress(), id);
                packetInjector.injectPlayer();
                playerPacketStuffMap1202.put(player, packetInjector);
            }
        }
    }

    public static void unInject(Player player){
        switch (BukkitVersion.getBukkitVersion()){
            case "12111" -> {
                playerPacketStuffMap12111.get(player).uninjectPlayer();
                playerPacketStuffMap12111.remove(player);
            }
            case "12110" -> {
                playerPacketStuffMap12110.get(player).uninjectPlayer();
                playerPacketStuffMap12110.remove(player);
            }
            case "1219" -> {
                playerPacketStuffMap1219.get(player).uninjectPlayer();
                playerPacketStuffMap1219.remove(player);
            }
            case "1218" -> {
                playerPacketStuffMap1218.get(player).uninjectPlayer();
                playerPacketStuffMap1218.remove(player);
            }
            case "1217" -> {
                playerPacketStuffMap1217.get(player).uninjectPlayer();
                playerPacketStuffMap1217.remove(player);
            }
            case "1216" -> {
                playerPacketStuffMap1216.get(player).uninjectPlayer();
                playerPacketStuffMap1216.remove(player);
            }
            case "1215" -> {
                playerPacketStuffMap1215.get(player).uninjectPlayer();
                playerPacketStuffMap1215.remove(player);
            }
            case "1214" -> {
                playerPacketStuffMap1214.get(player).uninjectPlayer();
                playerPacketStuffMap1214.remove(player);
            }
            case "1213" -> {
                playerPacketStuffMap1213.get(player).uninjectPlayer();
                playerPacketStuffMap1213.remove(player);
            }
            case "1211" -> {
                playerPacketStuffMap1211.get(player).uninjectPlayer();
                playerPacketStuffMap1211.remove(player);
            }
            case "121" -> {
                playerPacketStuffMap121.get(player).uninjectPlayer();
                playerPacketStuffMap121.remove(player);
            }
            case "1206" -> {
                playerPacketStuffMap1206.get(player).uninjectPlayer();
                playerPacketStuffMap1206.remove(player);
            }
            case "1204" -> {
                playerPacketStuffMap1204.get(player).uninjectPlayer();
                playerPacketStuffMap1204.remove(player);
            }
            case "1202" -> {
                playerPacketStuffMap1202.get(player).uninjectPlayer();
                playerPacketStuffMap1202.remove(player);
            }
        }
    }


}
