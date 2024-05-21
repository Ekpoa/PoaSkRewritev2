package poa;

import poa.util.BukkitVersion;

public class PosRotPacket {

    public static Object posPacket(int id, short deltaX, short deltaY, short deltaZ){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> PosRotPacket1202.posPacket(id, deltaX, deltaY, deltaZ);
            case "1204" -> PosRotPacket1204.posPacket(id, deltaX, deltaY, deltaZ);
            case "1206" -> PosRotPacket1206.posPacket(id, deltaX, deltaY, deltaZ);
            default -> null;
        };
    }


    public static Object rotPacket(int id, int yaw, int pitch){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> PosRotPacket1202.rotPacket(id, yaw, pitch);
            case "1204" -> PosRotPacket1204.rotPacket(id, yaw, pitch);
            case "1206" -> PosRotPacket1206.rotPacket(id, yaw, pitch);
            default -> null;
        };
    }

}
