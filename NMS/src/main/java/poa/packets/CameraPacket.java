package poa.packets;

import poa.packets.CameraPacket1202;
import poa.packets.CameraPacket1204;
import poa.packets.CameraPacket1206;
import poa.util.BukkitVersion;

public class CameraPacket {

    public static Object cameraPacket(int id){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> CameraPacket1202.cameraPacket(id);
            case "1204" -> CameraPacket1204.cameraPacket(id);
            case "1206" -> CameraPacket1206.cameraPacket(id);
            case "121" -> CameraPacket121.cameraPacket(id);
            case "1211" -> CameraPacket1211.cameraPacket(id);
            case "1213" -> CameraPacket1213.cameraPacket(id);
            case "1214" -> CameraPacket1214.cameraPacket(id);
            case "1215" -> CameraPacket1215.cameraPacket(id);
            default -> null;
        };
    }


}
