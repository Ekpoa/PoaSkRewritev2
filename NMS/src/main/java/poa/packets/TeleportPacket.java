package poa.packets;

import poa.util.BukkitVersion;

public class TeleportPacket {

    public static Object packet(int id, double x, double y, double z, float yaw, float pitch, boolean onGround){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> TeleportPacket1202.teleportPacket(id, x, y, z, yaw, pitch, onGround);
            case "1204" -> TeleportPacket1204.teleportPacket(id, x, y, z, yaw, pitch, onGround);
            case "1206" -> TeleportPacket1206.teleportPacket(id, x, y, z, yaw, pitch, onGround);
            default -> null;
        };
    }

}
