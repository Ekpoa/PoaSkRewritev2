package poa.packets;

import poa.util.BukkitVersion;

public class AnimatePacket {

    public static Object packet(int id, int animation){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> AnimationPacket1202.packet(id, animation);
            case "1204" -> AnimationPacket1204.packet(id, animation);
            case "1206" -> AnimationPacket1206.packet(id, animation);
            case "121" -> AnimationPacket121.packet(id, animation);
            case "1211" -> AnimationPacket1211.packet(id, animation);
            case "1213" -> AnimationPacket1213.packet(id, animation);
            case "1214" -> AnimationPacket1214.packet(id, animation);
            case "1215" -> AnimationPacket1215.packet(id, animation);
            case "1216" -> AnimationPacket1216.packet(id, animation);
            case "1217" -> AnimationPacket1217.packet(id, animation);
            case "1218" -> AnimationPacket1218.packet(id, animation);
            case "1219" -> AnimationPacket1219.packet(id, animation);
            case "12110" -> AnimationPacket12110.packet(id, animation);
            case "12111" -> AnimationPacket12111.packet(id, animation);
            default -> null;
        };
    }
}
