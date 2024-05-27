package poa.packets.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import poa.packets.packetListener.PacketInjector1202;
import poa.packets.packetListener.PacketInjector1204;
import poa.packets.packetListener.PacketInjector1206;
import poa.util.BukkitVersion;

import java.util.HashMap;
import java.util.Map;

public class PacketInjector {
    private static final Map<Player, PacketInjector1206> playerPacketStuffMap1206 = new HashMap<>();
    private static final Map<Player, PacketInjector1204> playerPacketStuffMap1204 = new HashMap<>();
    private static final Map<Player, PacketInjector1202> playerPacketStuffMap1202 = new HashMap<>();


    public static void inject(PlayerJoinEvent e){
        switch (BukkitVersion.getBukkitVersion()) {
            case "1206" -> {
                Player player = e.getPlayer();
                PacketInjector1206 packetInjector = new PacketInjector1206(player);
                packetInjector.inject(player);
                playerPacketStuffMap1206.put(player, packetInjector);
            }
        }
    }

    public static void inject(PlayerLoginEvent e){
        switch (BukkitVersion.getBukkitVersion()){
            case "1204" -> {
                Player player = e.getPlayer();
                PacketInjector1204 packetInjector = new PacketInjector1204(player, e.getAddress());
                packetInjector.injectPlayer();
                playerPacketStuffMap1204.put(player, packetInjector);
            }
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
