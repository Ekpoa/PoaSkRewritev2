package poa.packets;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import poa.packets.FakePlayer1202;
import poa.packets.FakePlayer1204;
import poa.packets.FakePlayer1206;
import poa.util.BukkitVersion;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class FakePlayer {

    public static void spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency, int id, UUID uuid) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> FakePlayer1202.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
            case "1204" -> FakePlayer1204.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
            case "1206" -> FakePlayer1206.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
            case "121" -> FakePlayer121.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
        }
    }

    public static void spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency, int id) {
        spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, UUID.randomUUID());
    }

    public static void spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency) {
        spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, ThreadLocalRandom.current().nextInt(99999, Integer.MAX_VALUE -1));
    }

    public static void removeFakePlayerPacket(List<Player> sendTo, List<UUID> uuids, List<Integer> ids) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> FakePlayer1202.removeFakePlayerPacket(sendTo, uuids, ids);
            case "1204" -> FakePlayer1204.removeFakePlayerPacket(sendTo, uuids, ids);
            case "1206" -> FakePlayer1206.removeFakePlayerPacket(sendTo, uuids, ids);
            case "121" -> FakePlayer121.removeFakePlayerPacket(sendTo, uuids, ids);
        }
    }


}
