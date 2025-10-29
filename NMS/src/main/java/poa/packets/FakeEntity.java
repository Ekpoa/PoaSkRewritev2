package poa.packets;

import org.bukkit.Location;
import poa.packets.FakeEntity1202;
import poa.packets.FakeEntity1204;
import poa.packets.FakeEntity1206;
import poa.util.BukkitVersion;

import java.util.List;

public class FakeEntity {

    public static Object fakeEntityPacket(int id, Location location, String type, int data) {
        return switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> FakeEntity1202.fakeEntityPacket(id, location, type, data);
            case "1204" -> FakeEntity1204.fakeEntityPacket(id, location, type, data);
            case "1206" -> FakeEntity1206.fakeEntityPacket(id, location, type, data);
            case "121" -> FakeEntity121.fakeEntityPacket(id, location, type, data);
            case "1211" -> FakeEntity1211.fakeEntityPacket(id, location, type, data);
            case "1213" -> FakeEntity1213.fakeEntityPacket(id, location, type, data);
            case "1214" -> FakeEntity1214.fakeEntityPacket(id, location, type, data);
            case "1215" -> FakeEntity1215.fakeEntityPacket(id, location, type, data);
            case "1216" -> FakeEntity1216.fakeEntityPacket(id, location, type, data);
            case "1217" -> FakeEntity1217.fakeEntityPacket(id, location, type, data);
            case "1218" -> FakeEntity1218.fakeEntityPacket(id, location, type, data);
            case "1219" -> FakeEntity1219.fakeEntityPacket(id, location, type, data);
            case "12110" -> FakeEntity12110.fakeEntityPacket(id, location, type, data);
            default -> null;
        };
    }

    public static Object fakeEntityPacket(int id, Location location, String type) {
        return fakeEntityPacket(id, location, type, 0);
    }

    public static Object removeFakeEntityPacket(List<Integer> idList) {
        return switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> FakeEntity1202.removeFakeEntityPacket(idList);
            case "1204" -> FakeEntity1204.removeFakeEntityPacket(idList);
            case "1206" -> FakeEntity1206.removeFakeEntityPacket(idList);
            case "121" -> FakeEntity121.removeFakeEntityPacket(idList);
            case "1211" -> FakeEntity1211.removeFakeEntityPacket(idList);
            case "1213" -> FakeEntity1213.removeFakeEntityPacket(idList);
            case "1214" -> FakeEntity1214.removeFakeEntityPacket(idList);
            case "1215" -> FakeEntity1215.removeFakeEntityPacket(idList);
            case "1216" -> FakeEntity1216.removeFakeEntityPacket(idList);
            case "1217" -> FakeEntity1217.removeFakeEntityPacket(idList);
            case "1218" -> FakeEntity1218.removeFakeEntityPacket(idList);
            case "1219" -> FakeEntity1219.removeFakeEntityPacket(idList);
            case "12110" -> FakeEntity12110.removeFakeEntityPacket(idList);
            default -> null;
        };
    }

}
