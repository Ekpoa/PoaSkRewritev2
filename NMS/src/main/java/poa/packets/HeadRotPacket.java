package poa.packets;

import poa.packets.HeadRotPacket1202;
import poa.packets.HeadRotPacket1204;
import poa.packets.HeadRotPacket1206;
import poa.util.BukkitVersion;

public class HeadRotPacket {


    public static Object packet(int id, int yRot){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> HeadRotPacket1202.packet(id, yRot);
            case "1204" -> HeadRotPacket1204.packet(id, yRot);
            case "1206" -> HeadRotPacket1206.packet(id, yRot);
            case "121" -> HeadRotPacket121.packet(id, yRot);
            case "1211" -> HeadRotPacket1211.packet(id, yRot);
            case "1213" -> HeadRotPacket1213.packet(id, yRot);
            default -> null;
        };

    }

}
