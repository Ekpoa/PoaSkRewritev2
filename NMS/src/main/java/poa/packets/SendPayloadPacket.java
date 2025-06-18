package poa.packets;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import poa.packets.SendPayloadPacket1202;
import poa.packets.SendPayloadPacket1204;
import poa.packets.SendPayloadPacket1206;
import poa.util.BukkitVersion;

public class SendPayloadPacket {

    public static void sendGameTestMarker(Player player, Location location, String text, Color color, int durationMs) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> SendPayloadPacket1202.sendGameTestMarker(player, location, text, color, durationMs);
            case "1204" -> SendPayloadPacket1204.sendGameTestMarker(player, location, text, color, durationMs);
            case "1206" -> SendPayloadPacket1206.sendGameTestMarker(player, location, text, color, durationMs);
            case "121" -> SendPayloadPacket121.sendGameTestMarker(player, location, text, color, durationMs);
            case "1211" -> SendPayloadPacket1211.sendGameTestMarker(player, location, text, color, durationMs);
            case "1213" -> SendPayloadPacket1213.sendGameTestMarker(player, location, text, color, durationMs);
            case "1214" -> SendPayloadPacket1214.sendGameTestMarker(player, location, text, color, durationMs);
            case "1215" -> SendPayloadPacket1215.sendGameTestMarker(player, location, text, color, durationMs);
        }

    }

    public static void sendBrandPayload(Player player, String brand) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> SendPayloadPacket1202.sendBrandPayload(player, brand);
            case "1204" -> SendPayloadPacket1204.sendBrandPayload(player, brand);
            case "1206" -> SendPayloadPacket1206.sendBrandPayload(player, brand);
            case "121" -> SendPayloadPacket121.sendBrandPayload(player, brand);
            case "1211" -> SendPayloadPacket1211.sendBrandPayload(player, brand);
            case "1213" -> SendPayloadPacket1213.sendBrandPayload(player, brand);
            case "1214" -> SendPayloadPacket1214.sendBrandPayload(player, brand);
            case "1215" -> SendPayloadPacket1215.sendBrandPayload(player, brand);
        }
    }

}
