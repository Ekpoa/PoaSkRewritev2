
package poa.packets;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import poa.util.BukkitVersion;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class FakePlayer {

    public static void spawnFakePlayer(List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency, int id, UUID uuid) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> FakePlayer1202.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
            case "1204" -> FakePlayer1204.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
            case "1206" -> FakePlayer1206.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
            case "121" -> FakePlayer121.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
            case "1211" -> FakePlayer1211.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
            case "1213" -> FakePlayer1213.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
            case "1214" -> FakePlayer1214.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
            case "1215" -> FakePlayer1215.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
            case "1216" -> FakePlayer1216.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
            case "1217" -> FakePlayer1217.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
            case "1218" -> FakePlayer1218.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
            case "1219" -> FakePlayer1219.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
            case "12110" -> FakePlayer12110.spawnFakePlayer(sendTo, name, skinName, loc, listed, latency, id, uuid);
        }
    }

    public static void spawnFakePlayer( List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency, int id) {
        spawnFakePlayer( sendTo, name, skinName, loc, listed, latency, id, UUID.randomUUID());
    }

    public static void spawnFakePlayer( List<Player> sendTo, String name, String skinName, Location loc, boolean listed, int latency) {
        spawnFakePlayer( sendTo, name, skinName, loc, listed, latency, ThreadLocalRandom.current().nextInt(99999, Integer.MAX_VALUE - 1));
    }

    public static void spawnFakePlayer(List<Player> sendTo, String name, String texture, String signature, Location loc, boolean listed, int latency, int id, UUID uuid, int skinModel) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" ->
                    FakePlayer1202.spawnFakePlayer(sendTo, name, texture, signature, loc, listed, latency, id, uuid, skinModel);
            case "1204" ->
                    FakePlayer1204.spawnFakePlayer(sendTo, name, texture, signature, loc, listed, latency, id, uuid, skinModel);
            case "1206" ->
                    FakePlayer1206.spawnFakePlayer(sendTo, name, texture, signature, loc, listed, latency, id, uuid, skinModel);
            case "121" ->
                    FakePlayer121.spawnFakePlayer(sendTo, name, texture, signature, loc, listed, latency, id, uuid, skinModel);
            case "1211" ->
                    FakePlayer1211.spawnFakePlayer(sendTo, name, texture, signature, loc, listed, latency, id, uuid, skinModel);
            case "1213" ->
                    FakePlayer1213.spawnFakePlayer(sendTo, name, texture, signature, loc, listed, latency, id, uuid, skinModel);
            case "1214" ->
                    FakePlayer1214.spawnFakePlayer(sendTo, name, texture, signature, loc, listed, latency, id, uuid, skinModel);
            case "1215" ->
                    FakePlayer1215.spawnFakePlayer(sendTo, name, texture, signature, loc, listed, latency, id, uuid, skinModel);
            case "1216" ->
                    FakePlayer1216.spawnFakePlayer(sendTo, name, texture, signature, loc, listed, latency, id, uuid, skinModel);
            case "1217" ->
                    FakePlayer1217.spawnFakePlayer(sendTo, name, texture, signature, loc, listed, latency, id, uuid, skinModel);
            case "1218" ->
                    FakePlayer1218.spawnFakePlayer(sendTo, name, texture, signature, loc, listed, latency, id, uuid, skinModel);
        }
    }

    public static void removeFakePlayerPacket(List<Player> sendTo, List<UUID> uuids, List<Integer> ids) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> FakePlayer1202.removeFakePlayerPacket(sendTo, uuids, ids);
            case "1204" -> FakePlayer1204.removeFakePlayerPacket(sendTo, uuids, ids);
            case "1206" -> FakePlayer1206.removeFakePlayerPacket(sendTo, uuids, ids);
            case "121" -> FakePlayer121.removeFakePlayerPacket(sendTo, uuids, ids);
            case "1211" -> FakePlayer1211.removeFakePlayerPacket(sendTo, uuids, ids);
            case "1213" -> FakePlayer1213.removeFakePlayerPacket(sendTo, uuids, ids);
            case "1214" -> FakePlayer1214.removeFakePlayerPacket(sendTo, uuids, ids);
            case "1215" -> FakePlayer1215.removeFakePlayerPacket(sendTo, uuids, ids);
            case "1216" -> FakePlayer1216.removeFakePlayerPacket(sendTo, uuids, ids);
            case "1217" -> FakePlayer1217.removeFakePlayerPacket(sendTo, uuids, ids);
            case "1218" -> FakePlayer1218.removeFakePlayerPacket(sendTo, uuids, ids);
            case "1219" -> FakePlayer1219.removeFakePlayerPacket(sendTo, uuids, ids);
            case "12110" -> FakePlayer12110.removeFakePlayerPacket(sendTo, uuids, ids);
        }
    }





}