package poa.packets;

import org.bukkit.attribute.Attribute;
import poa.util.BukkitVersion;

public class AttributePacket {

    public static Object packet(int id, Attribute attribute, double value) {
        return switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> AttributePacket1202.packet(id, attribute, value);
            case "1204" -> AttributePacket1204.packet(id, attribute, value);
            case "1206" -> AttributePacket1206.packet(id, attribute, value);
            case "121" -> AttributePacket121.packet(id, attribute, value);
            case "1211" -> AttributePacket1211.packet(id, attribute, value);
            case "1213" -> AttributePacket1213.packet(id, attribute, value);
            case "1214" -> AttributePacket1214.packet(id, attribute, value);
            case "1215" -> AttributePacket1215.packet(id, attribute, value);
            case "1216" -> AttributePacket1216.packet(id, attribute, value);
            case "1217" -> AttributePacket1217.packet(id, attribute, value);
            default -> null;
        };
    }

    public static Object packet(int id, String attribute, double value) {
        return switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> AttributePacket1202.packet(id, attribute, value);
            case "1204" -> AttributePacket1204.packet(id, attribute, value);
            case "1206" -> AttributePacket1206.packet(id, attribute, value);
            case "121" -> AttributePacket121.packet(id, attribute, value);
            case "1211" -> AttributePacket1211.packet(id, attribute, value);
            case "1213" -> AttributePacket1213.packet(id, attribute, value);
            case "1214" -> AttributePacket1214.packet(id, attribute, value);
            case "1215" -> AttributePacket1215.packet(id, attribute, value);
            case "1216" -> AttributePacket1216.packet(id, attribute, value);
            case "1217" -> AttributePacket1217.packet(id, attribute, value);
            default -> null;
        };
    }



}
