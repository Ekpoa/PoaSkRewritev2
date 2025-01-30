package poa.poaskrewritev2.events.eventvalues;

import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import poa.packets.packetListener.events.*;
import poa.util.BukkitVersion;

public class RegisterBlockUpdate {

    public static void registerValues(){
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> {
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
