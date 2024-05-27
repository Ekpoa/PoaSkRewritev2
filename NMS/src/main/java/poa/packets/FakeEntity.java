package poa.packets;

import org.bukkit.Location;
import poa.packets.FakeEntity1202;
import poa.packets.FakeEntity1204;
import poa.packets.FakeEntity1206;
import poa.util.BukkitVersion;

import java.util.List;

public class FakeEntity {

    public static Object fakeEntityPacket(int id, Location location, String type, int data) {
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> FakeEntity1202.fakeEntityPacket(id, location, type, data);
            case "1204" -> FakeEntity1204.fakeEntityPacket(id, location, type, data);
            case "1206" -> FakeEntity1206.fakeEntityPacket(id, location, type, data);
            default -> null;
        };
    }
    public static Object fakeEntityPacket(int id, Location location, String type){
        return fakeEntityPacket(id, location, type, 0);
    }


    public static Object removeFakeEntityPacket(List<Integer> idList){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> FakeEntity1202.removeFakeEntityPacket(idList);
            case "1204" -> FakeEntity1204.removeFakeEntityPacket(idList);
            case "1206" -> FakeEntity1206.removeFakeEntityPacket(idList);
            default -> null;
        };
    }

}
