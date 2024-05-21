package poa;

import poa.util.BukkitVersion;

public class SetPassengerPacket {


    public static Object packet(int id, int[] ids){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> SetPassengerPacket1202.packet(id, ids);
            case "1204" -> SetPassengerPacket1204.packet(id, ids);
            case "1206" -> SetPassengerPacket1206.packet(id, ids);
            default -> null;
        };
    }

}

