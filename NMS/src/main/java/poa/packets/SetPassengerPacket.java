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
            case "1211" -> SetPassengerPacket1211.packet(id, ids);
            case "1213" -> SetPassengerPacket1213.packet(id, ids);
            case "1214" -> SetPassengerPacket1214.packet(id, ids);
            case "1215" -> SetPassengerPacket1215.packet(id, ids);
            case "1216" -> SetPassengerPacket1216.packet(id, ids);
            case "1217" -> SetPassengerPacket1217.packet(id, ids);
            case "1218" -> SetPassengerPacket1218.packet(id, ids);
            case "1219" -> SetPassengerPacket1219.packet(id, ids);
            case "12110" -> SetPassengerPacket12110.packet(id, ids);
            default -> null;
        };
    }

}

