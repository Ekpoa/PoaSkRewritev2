package poa;

import poa.util.BukkitVersion;

public class HeadRotPacket {


    public static Object packet(int id, int yRot){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> HeadRotPacket1202.packet(id, yRot);
            case "1204" -> HeadRotPacket1204.packet(id, yRot);
            case "1206" -> HeadRotPacket1206.packet(id, yRot);
            default -> null;
        };

    }

}
