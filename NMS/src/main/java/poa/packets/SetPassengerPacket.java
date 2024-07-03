package poa.packets;

import poa.packets.SetPassengerPacket1202;
import poa.packets.SetPassengerPacket1204;
import poa.packets.SetPassengerPacket1206;
import poa.util.BukkitVersion;

public class SetPassengerPacket {


    public static Object packet(int id, int[] ids){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> SetPassengerPacket1202.packet(id, ids);
            case "1204" -> SetPassengerPacket1204.packet(id, ids);
            case "1206" -> SetPassengerPacket1206.packet(id, ids);
            case "121" -> SetPassengerPacket121.packet(id, ids);
            default -> null;
        };
    }

}

