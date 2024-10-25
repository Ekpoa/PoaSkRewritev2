package poa.packets;

import org.bukkit.attribute.Attribute;
import poa.util.BukkitVersion;

public class AttributePacket {

    public static Object packet(int id, Attribute attribute, double value){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> AttributePacket1202.packet(id, attribute, value);
            case "1204" -> AttributePacket1204.packet(id, attribute, value);
            case "1206" -> AttributePacket1206.packet(id, attribute, value);
            case "121" -> AttributePacket121.packet(id, attribute, value);
            case "1211" -> AttributePacket1211.packet(id, attribute, value);
            default -> null;
        };
    }

    public static Object packet(int id, String attribute, double value){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> AttributePacket1202.packet(id, attribute, value);
            case "1204" -> AttributePacket1204.packet(id, attribute, value);
            case "1206" -> AttributePacket1206.packet(id, attribute, value);
            case "121" -> AttributePacket121.packet(id, attribute, value);
            case "1211" -> AttributePacket121.packet(id, attribute, value);
            default -> null;
        };
    }


}
