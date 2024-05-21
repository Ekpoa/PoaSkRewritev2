package poa;

import poa.util.BukkitVersion;

public class CameraPacket {

    public static Object cameraPacket(int id){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> CameraPacket1202.cameraPacket(id);
            case "1204" -> CameraPacket1204.cameraPacket(id);
            case "1206" -> CameraPacket1206.cameraPacket(id);
            default -> null;
        };
    }


}
