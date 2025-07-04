package poa.packets;

import poa.packets.PosRotPacket1202;
import poa.packets.PosRotPacket1204;
import poa.packets.PosRotPacket1206;
import poa.util.BukkitVersion;

public class PosRotPacket {

    public static Object posPacket(int id, short deltaX, short deltaY, short deltaZ){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> PosRotPacket1202.posPacket(id, deltaX, deltaY, deltaZ);
            case "1204" -> PosRotPacket1204.posPacket(id, deltaX, deltaY, deltaZ);
            case "1206" -> PosRotPacket1206.posPacket(id, deltaX, deltaY, deltaZ);
            case "121" -> PosRotPacket121.posPacket(id, deltaX, deltaY, deltaZ);
            case "1211" -> PosRotPacket1211.posPacket(id, deltaX, deltaY, deltaZ);
            case "1213" -> PosRotPacket1213.posPacket(id, deltaX, deltaY, deltaZ);
            case "1214" -> PosRotPacket1214.posPacket(id, deltaX, deltaY, deltaZ);
            case "1215" -> PosRotPacket1215.posPacket(id, deltaX, deltaY, deltaZ);
            case "1216" -> PosRotPacket1216.posPacket(id, deltaX, deltaY, deltaZ);
            case "1217" -> PosRotPacket1217.posPacket(id, deltaX, deltaY, deltaZ);
            default -> null;
        };
    }


    public static Object rotPacket(int id, int yaw, int pitch){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> PosRotPacket1202.rotPacket(id, yaw, pitch);
            case "1204" -> PosRotPacket1204.rotPacket(id, yaw, pitch);
            case "1206" -> PosRotPacket1206.rotPacket(id, yaw, pitch);
            case "121" -> PosRotPacket121.rotPacket(id, yaw, pitch);
            case "1211" -> PosRotPacket1211.rotPacket(id, yaw, pitch);
            case "1213" -> PosRotPacket1213.rotPacket(id, yaw, pitch);
            case "1214" -> PosRotPacket1214.rotPacket(id, yaw, pitch);
            case "1215" -> PosRotPacket1215.rotPacket(id, yaw, pitch);
            case "1216" -> PosRotPacket1216.rotPacket(id, yaw, pitch);
            case "1217" -> PosRotPacket1217.rotPacket(id, yaw, pitch);
            default -> null;
        };
    }

}
