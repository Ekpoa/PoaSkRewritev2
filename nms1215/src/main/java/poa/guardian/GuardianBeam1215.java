package poa.guardian;

import lombok.Getter;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import org.checkerframework.framework.qual.PostconditionAnnotation;
import poa.packets.FakeEntity1215;
import poa.packets.Metadata1215;
import poa.packets.TeamPacket1215;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GuardianBeam1215 {

   // public static Map<String, GuardianBeam121> dataMap = new HashMap<>();


    @Getter
    List<UUID> uuids = new ArrayList<>();
    List<UUID> currentlySeeing = new ArrayList<>();
    int guardianID;
    int squidID;
    UUID guardianUUID;
    UUID squidUUID;
    String beamID;
    Location guardianLoc;
    Location batLoc;
    String color;
    Plugin plugin;
    int taskID;


    public GuardianBeam1215(List<Player> players, String id, Location startLoc, Location endLoc, String color, Plugin plugin) {
//        if(dataMap.containsKey(id)){
//            plugin.getLogger().log(Level.WARNING, "creation of guardian beam id " + id + " failed, already existing id");
//            return;
//        }
        this.color = color;

        this.plugin = plugin;

        for (Player player : players)
            this.uuids.add(player.getUniqueId());
        this.beamID = id;
        this.guardianLoc = startLoc;
        this.batLoc = getEndLocation(startLoc, endLoc);
        this.guardianUUID = UUID.randomUUID();
        this.squidUUID = UUID.randomUUID();

        final ThreadLocalRandom current = ThreadLocalRandom.current();
        final int maxValue = Integer.MAX_VALUE - 1;
        this.guardianID = current.nextInt(99999, maxValue);
        this.squidID = current.nextInt(99999, maxValue);


        for (UUID uuid : this.uuids) {
            final Player player = Bukkit.getPlayer(uuid);
            if (player == null)
                continue;

            runCheckAndShow((CraftPlayer) player);
        }


     //   dataMap.put(this.beamID, this);
    }


    public void runCheckAndShow(CraftPlayer player) {
        final UUID uuid = player.getUniqueId();
        if (player.getWorld() != this.guardianLoc.getWorld()) {
            currentlySeeing.remove(uuid);
            return;
        }

        final Location playerLocation = player.getLocation();
        if (playerLocation.distanceSquared(guardianLoc) > 22500 || playerLocation.distanceSquared(batLoc) > 22500) { //150 blocks
            currentlySeeing.remove(uuid);
            return;
        }

        if (currentlySeeing.contains(uuid))
            return;

        currentlySeeing.add(uuid);

        show(player);
    }

    public void show(CraftPlayer player) {
        try {
            final Object guardianPacket = FakeEntity1215.fakeEntityPacket(this.guardianID, this.guardianLoc, "guardian", this.guardianUUID);
            final Object squidPacket = FakeEntity1215.fakeEntityPacket(this.squidID, this.batLoc, "bat", this.squidUUID);

            final Object teamPacket = TeamPacket1215.teamPacketForBeam(List.of(this.guardianUUID.toString(), this.squidUUID.toString()), this.color);

            final Metadata1215 guardianMeta = new Metadata1215(this.guardianID);
            guardianMeta.setInvisible(true);
            guardianMeta.setGuardianTarget(this.squidID);
            guardianMeta.setGuardianSpikes(false);

            if(!this.color.equalsIgnoreCase("white"))
                guardianMeta.setGlow(true);

            final Metadata1215 squidMeta = new Metadata1215(this.squidID);
            squidMeta.setInvisible(true);


            final ServerGamePacketListenerImpl connection = player.getHandle().connection;
            connection.send((Packet<?>) guardianPacket);
            connection.send((Packet<?>) squidPacket);
            connection.send((Packet<?>) teamPacket);
            connection.send((Packet<?>) guardianMeta.build());
            connection.send((Packet<?>) squidMeta.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Location getEndLocation(Location start, Location end) {
        Vector guardianVector = start.toVector();
        Vector batVector = end.toVector();
        Vector direction = guardianVector.subtract(batVector).normalize();

        Vector newBatVector = batVector.add(direction);
        return newBatVector.toLocation(end.getWorld());
    }

    public void loop() {
        this.taskID = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            boolean skip = true;
            for (UUID player : uuids) {
                if (Bukkit.getPlayer(player) != null) {
                    skip = false;
                    break;
                }
            }

            if (skip) { //no point running checks if no user is online
                return;
            }

            for (UUID uuid : this.uuids) {
                final Player player = Bukkit.getPlayer(uuid);
                if (player == null)
                    continue;

                CraftPlayer craftPlayer = (CraftPlayer) player;

                runCheckAndShow(craftPlayer);
            }
        }, 20L, 20L).getTaskId();


    }

    public void destroy() {
        final Object removePacket = FakeEntity1215.removeFakeEntityPacket(List.of(this.guardianID, this.squidID));
        for (UUID uuid : this.uuids) {
            final Player player = Bukkit.getPlayer(uuid);
            if (player == null)
                continue;

            CraftPlayer craftPlayer = (CraftPlayer) player;
            craftPlayer.getHandle().connection.send((Packet<?>) removePacket);
        }
        Bukkit.getScheduler().cancelTask(this.taskID);
        //  dataMap.remove(this.beamID);
    }

}
