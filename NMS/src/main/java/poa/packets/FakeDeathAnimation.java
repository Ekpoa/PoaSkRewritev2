package poa.packets;

import poa.util.BukkitVersion;

public class FakeDeathAnimation {

    public static Object fakeEntityPacket(int id) {
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> FakeDeathAnimation1202.packet(id);
            case "1204" -> FakeDeathAnimation1204.packet(id);
            case "1206" -> FakeDeathAnimation1206.packet(id);
            case "121" -> FakeDeathAnimation121.packet(id);
            case "1211" -> FakeDeathAnimation1211.packet(id);
            default -> null;
        };
    }

}
