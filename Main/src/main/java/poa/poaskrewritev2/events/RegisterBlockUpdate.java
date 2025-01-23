package poa.poaskrewritev2.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.util.Vector;
import poa.packets.packetListener.events.*;
import poa.util.BukkitVersion;

public class RegisterBlockUpdate {

    public static void register(){
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> {
                Skript.registerEvent("Block Update Event", SimpleEvent.class, BlockUpdateEvent1202.class,
                        "block change packet");
                EventValues.registerEventValue(BlockUpdateEvent1202.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(BlockUpdateEvent1202 event) {
                        return event.getLocation().toCenterLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1202.class, Material.class, new Getter<>() {
                    @Override
                    public Material get(BlockUpdateEvent1202 event) {
                        return event.getMaterial();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1202.class, BlockData.class, new Getter<>() {
                    @Override
                    public BlockData get(BlockUpdateEvent1202 event) {
                        return event.getBlockData();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1202.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(BlockUpdateEvent1202 event) {
                        return event.getOriginalBlock();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1204" -> {
                Skript.registerEvent("Block Update Event", SimpleEvent.class, BlockUpdateEvent1204.class,
                        "block change packet");
                EventValues.registerEventValue(BlockUpdateEvent1204.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(BlockUpdateEvent1204 event) {
                        return event.getLocation().toCenterLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1204.class, Material.class, new Getter<>() {
                    @Override
                    public Material get(BlockUpdateEvent1204 event) {
                        return event.getMaterial();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1204.class, BlockData.class, new Getter<>() {
                    @Override
                    public BlockData get(BlockUpdateEvent1204 event) {
                        return event.getBlockData();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1204.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(BlockUpdateEvent1204 event) {
                        return event.getOriginalBlock();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1206" -> {
                Skript.registerEvent("Block Update Event", SimpleEvent.class, BlockUpdateEvent1206.class,
                        "block change packet");
                EventValues.registerEventValue(BlockUpdateEvent1206.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(BlockUpdateEvent1206 event) {
                        return event.getLocation().toCenterLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1206.class, Material.class, new Getter<>() {
                    @Override
                    public Material get(BlockUpdateEvent1206 event) {
                        return event.getMaterial();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1206.class, BlockData.class, new Getter<>() {
                    @Override
                    public BlockData get(BlockUpdateEvent1206 event) {
                        return event.getBlockData();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1206.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(BlockUpdateEvent1206 event) {
                        return event.getOriginalBlock();
                    }
                }, EventValues.TIME_NOW);
            }
            case "121" -> {
                Skript.registerEvent("Block Update Event", SimpleEvent.class, BlockUpdateEvent121.class,
                        "block change packet");
                EventValues.registerEventValue(BlockUpdateEvent121.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(BlockUpdateEvent121 event) {
                        return event.getLocation().toCenterLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent121.class, Material.class, new Getter<>() {
                    @Override
                    public Material get(BlockUpdateEvent121 event) {
                        return event.getMaterial();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent121.class, BlockData.class, new Getter<>() {
                    @Override
                    public BlockData get(BlockUpdateEvent121 event) {
                        return event.getBlockData();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent121.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(BlockUpdateEvent121 event) {
                        return event.getOriginalBlock();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1211" -> {
                Skript.registerEvent("Block Update Event", SimpleEvent.class, BlockUpdateEvent1211.class,
                        "block change packet");
                EventValues.registerEventValue(BlockUpdateEvent1211.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(BlockUpdateEvent1211 event) {
                        return event.getLocation().toCenterLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1211.class, Material.class, new Getter<>() {
                    @Override
                    public Material get(BlockUpdateEvent1211 event) {
                        return event.getMaterial();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1211.class, BlockData.class, new Getter<>() {
                    @Override
                    public BlockData get(BlockUpdateEvent1211 event) {
                        return event.getBlockData();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1211.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(BlockUpdateEvent1211 event) {
                        return event.getOriginalBlock();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1213" -> {
                Skript.registerEvent("Block Update Event", SimpleEvent.class, BlockUpdateEvent1213.class,
                        "block change packet");
                EventValues.registerEventValue(BlockUpdateEvent1213.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(BlockUpdateEvent1213 event) {
                        return event.getLocation().toCenterLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1213.class, Material.class, new Getter<>() {
                    @Override
                    public Material get(BlockUpdateEvent1213 event) {
                        return event.getMaterial();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1213.class, BlockData.class, new Getter<>() {
                    @Override
                    public BlockData get(BlockUpdateEvent1213 event) {
                        return event.getBlockData();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1213.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(BlockUpdateEvent1213 event) {
                        return event.getOriginalBlock();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1214" -> {
                Skript.registerEvent("Block Update Event", SimpleEvent.class, BlockUpdateEvent1214.class,
                        "block change packet");
                EventValues.registerEventValue(BlockUpdateEvent1214.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(BlockUpdateEvent1214 event) {
                        return event.getLocation().toCenterLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1214.class, Material.class, new Getter<>() {
                    @Override
                    public Material get(BlockUpdateEvent1214 event) {
                        return event.getMaterial();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1214.class, BlockData.class, new Getter<>() {
                    @Override
                    public BlockData get(BlockUpdateEvent1214 event) {
                        return event.getBlockData();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1214.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(BlockUpdateEvent1214 event) {
                        return event.getOriginalBlock();
                    }
                }, EventValues.TIME_NOW);
            }
        }
    }
}
