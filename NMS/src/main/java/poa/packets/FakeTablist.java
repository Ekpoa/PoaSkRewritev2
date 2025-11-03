package poa.packets;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import poa.util.BukkitVersion;

import java.util.List;
import java.util.UUID;

public class FakeTablist {

    public static void spawnTablistOnly(List<Player> sendTo, String name, Component tablistName, String skinName, UUID uuid, int latency) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> FakePlayer1202.spawnTablistOnly(sendTo, name, tablistName, skinName, uuid, latency);
            case "1204" -> FakePlayer1204.spawnTablistOnly(sendTo, name, tablistName, skinName, uuid, latency);
            case "1206" -> FakePlayer1206.spawnTablistOnly(sendTo, name, tablistName, skinName, uuid, latency);
            case "121" -> FakePlayer121.spawnTablistOnly(sendTo, name, tablistName, skinName, uuid, latency);
            case "1211" -> FakeTablist1211.addTabPlayer(sendTo, name, tablistName, skinName, uuid, latency, 127);
            case "1213" -> FakeTablist1213.addTabPlayer(sendTo, name, tablistName, skinName, uuid, latency, 127);
            case "1214" -> FakeTablist1214.addTabPlayer(sendTo, name, tablistName, skinName, uuid, latency, 127);
            case "1215" -> FakeTablist1215.addTabPlayer(sendTo, name, tablistName, skinName, uuid, latency, 127);
            case "1216" -> FakeTablist1216.addTabPlayer(sendTo, name, tablistName, skinName, uuid, latency, 127);
            case "1217" -> FakeTablist1217.addTabPlayer(sendTo, name, tablistName, skinName, uuid, latency, 127);
            case "1218" -> FakeTablist1218.addTabPlayer(sendTo, name, tablistName, skinName, uuid, latency, 127);
            case "1219" -> FakeTablist1219.addTabPlayer(sendTo, name, tablistName, skinName, uuid, latency, 127);
            case "12110" -> FakeTablist12110.addTabPlayer(sendTo, name, tablistName, skinName, uuid, latency, 127);
        }
    }

//    public static void spawnTablistOnly(List<Player> sendTo, String name, Component tablistName, String texture, String signature, UUID uuid, int latency) {
//        switch (BukkitVersion.getBukkitVersion()) {
//           // case "1214" -> FakeTablist1214.addTabPlayer(sendTo, name, tablistName, texture, signature, uuid, latency, 127);
////            case "1215" -> FakeTablist1215.addTabPlayer(sendTo, name, tablistName, skinName, uuid, latency, position);
////            case "1216" -> FakeTablist1216.addTabPlayer(sendTo, name, tablistName, skinName, uuid, latency, position);
////            case "1217" -> FakeTablist1217.addTabPlayer(sendTo, name, tablistName, skinName, uuid, latency, position);
////            case "1218" -> FakeTablist1218.addTabPlayer(sendTo, name, tablistName, skinName, uuid, latency, position);
////            case "1219" -> FakeTablist1219.addTabPlayer(sendTo, name, tablistName, skinName, uuid, latency, position);
//            case "12110" -> FakeTablist12110.addTabPlayer(sendTo, name, tablistName, texture, signature, uuid, latency, 127);
//        }
//    }


    public static void removeTablistOnly(List<Player> sendTo, List<UUID> uuids) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> FakePlayer1202.removeTablistPacket(sendTo, uuids);
            case "1204" -> FakePlayer1204.removeTablistPacket(sendTo, uuids);
            case "1206" -> FakePlayer1206.removeTablistPacket(sendTo, uuids);
            case "121" -> FakePlayer121.removeTablistPacket(sendTo, uuids);
            case "1211" -> FakeTablist1211.removeTabPlayer(sendTo, uuids.getFirst());
            case "1213" -> FakeTablist1213.removeTabPlayer(sendTo, uuids.getFirst());
            case "1214" -> FakeTablist1214.removeTabPlayer(sendTo, uuids.getFirst());
            case "1215" -> FakeTablist1215.removeTabPlayer(sendTo, uuids.getFirst());
            case "1216" -> FakeTablist1216.removeTabPlayer(sendTo, uuids.getFirst());
            case "1217" -> FakeTablist1217.removeTabPlayer(sendTo, uuids.getFirst());
            case "1218" -> FakeTablist1218.removeTabPlayer(sendTo, uuids.getFirst());
            case "1219" -> FakeTablist1219.removeTabPlayer(sendTo, uuids.getFirst());
            case "12110" -> FakeTablist12110.removeTabPlayer(sendTo, uuids.getFirst());
        }
    }
}
