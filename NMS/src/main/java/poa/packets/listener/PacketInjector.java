package poa.packets.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import poa.packets.packetListener.*;
import poa.util.BukkitVersion;

import java.util.HashMap;
import java.util.Map;

public class PacketInjector {
    private static final Map<Player, PacketInjector1213> playerPacketStuffMap1213 = new HashMap<>();
    private static final Map<Player, PacketInjector1211> playerPacketStuffMap1211 = new HashMap<>();
    private static final Map<Player, PacketInjector121> playerPacketStuffMap121 = new HashMap<>();
    private static final Map<Player, PacketInjector1206> playerPacketStuffMap1206 = new HashMap<>();
    private static final Map<Player, PacketInjector1204> playerPacketStuffMap1204 = new HashMap<>();
    private static final Map<Player, PacketInjector1202> playerPacketStuffMap1202 = new HashMap<>();


    public static void inject(PlayerJoinEvent e){
        switch (BukkitVersion.getBukkitVersion()) {
            case "1204" -> {
                Player player = e.getPlayer();
                PacketInjector1204 packetInjector = new PacketInjector1204(player);
                packetInjector.inject(player);
                playerPacketStuffMap1204.put(player, packetInjector);
            }
            case "1206" -> {
                Player player = e.getPlayer();
                PacketInjector1206 packetInjector = new PacketInjector1206(player);
                packetInjector.inject(player);
                playerPacketStuffMap1206.put(player, packetInjector);
            }
            case "121" -> {
                Player player = e.getPlayer();
                PacketInjector121 packetInjector = new PacketInjector121(player);
                packetInjector.inject(player);
                playerPacketStuffMap121.put(player, packetInjector);
            }
            case "1211" -> {

                Player player = e.getPlayer();
                PacketInjector1211 packetInjector = new PacketInjector1211(player);
                packetInjector.inject(player);
                playerPacketStuffMap1211.put(player, packetInjector);
            }
            case "1213" -> {

                Player player = e.getPlayer();
                PacketInjector1213 packetInjector = new PacketInjector1213(player);
                packetInjector.inject(player);
                playerPacketStuffMap1213.put(player, packetInjector);
            }
        }
    }

    public static void inject(PlayerLoginEvent e){
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> {
                Player player = e.getPlayer();
                PacketInjector1202 packetInjector = new PacketInjector1202(player, e.getAddress());
                packetInjector.injectPlayer();
                playerPacketStuffMap1202.put(player, packetInjector);
            }
        }
    }

    public static void unInject(Player player){
        switch (BukkitVersion.getBukkitVersion()){
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
