package poa.packets;

import org.bukkit.entity.Player;
import poa.packets.SendPacket1202;
import poa.packets.SendPacket1204;
import poa.packets.SendPacket1206;
import poa.util.BukkitVersion;

public class SendPacket {
    public static void sendPacket(Player player, Object packet) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> SendPacket1202.sendPacket(player, packet);
            case "1204" -> SendPacket1204.sendPacket(player, packet);
            case "1206" -> SendPacket1206.sendPacket(player, packet);
            case "121" -> SendPacket121.sendPacket(player, packet);
            case "1211" -> SendPacket1211.sendPacket(player, packet);
            case "1213" -> SendPacket1213.sendPacket(player, packet);
            case "1214" -> SendPacket1214.sendPacket(player, packet);
            case "1215" -> SendPacket1215.sendPacket(player, packet);
            case "1216" -> SendPacket1216.sendPacket(player, packet);
            case "1217" -> SendPacket1217.sendPacket(player, packet);
        }

    }
}