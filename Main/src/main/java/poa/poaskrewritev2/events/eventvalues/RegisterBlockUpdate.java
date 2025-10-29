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
            case "1215" -> {
                EventValues.registerEventValue(BlockUpdateEvent1215.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(BlockUpdateEvent1215 event) {
                        return event.getLocation().toCenterLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1215.class, Material.class, new Getter<>() {
                    @Override
                    public Material get(BlockUpdateEvent1215 event) {
                        return event.getMaterial();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1215.class, BlockData.class, new Getter<>() {
                    @Override
                    public BlockData get(BlockUpdateEvent1215 event) {
                        return event.getBlockData();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1215.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(BlockUpdateEvent1215 event) {
                        return event.getOriginalBlock();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1216" -> {
                EventValues.registerEventValue(BlockUpdateEvent1216.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(BlockUpdateEvent1216 event) {
                        return event.getLocation().toCenterLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1216.class, Material.class, new Getter<>() {
                    @Override
                    public Material get(BlockUpdateEvent1216 event) {
                        return event.getMaterial();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1216.class, BlockData.class, new Getter<>() {
                    @Override
                    public BlockData get(BlockUpdateEvent1216 event) {
                        return event.getBlockData();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1216.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(BlockUpdateEvent1216 event) {
                        return event.getOriginalBlock();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1217" -> {
                EventValues.registerEventValue(BlockUpdateEvent1217.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(BlockUpdateEvent1217 event) {
                        return event.getLocation().toCenterLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1217.class, Material.class, new Getter<>() {
                    @Override
                    public Material get(BlockUpdateEvent1217 event) {
                        return event.getMaterial();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1217.class, BlockData.class, new Getter<>() {
                    @Override
                    public BlockData get(BlockUpdateEvent1217 event) {
                        return event.getBlockData();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1217.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(BlockUpdateEvent1217 event) {
                        return event.getOriginalBlock();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1218" -> {
                EventValues.registerEventValue(BlockUpdateEvent1218.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(BlockUpdateEvent1218 event) {
                        return event.getLocation().toCenterLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1218.class, Material.class, new Getter<>() {
                    @Override
                    public Material get(BlockUpdateEvent1218 event) {
                        return event.getMaterial();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1218.class, BlockData.class, new Getter<>() {
                    @Override
                    public BlockData get(BlockUpdateEvent1218 event) {
                        return event.getBlockData();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1218.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(BlockUpdateEvent1218 event) {
                        return event.getOriginalBlock();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1219" -> {
                EventValues.registerEventValue(BlockUpdateEvent1219.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(BlockUpdateEvent1219 event) {
                        return event.getLocation().toCenterLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1219.class, Material.class, new Getter<>() {
                    @Override
                    public Material get(BlockUpdateEvent1219 event) {
                        return event.getMaterial();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1219.class, BlockData.class, new Getter<>() {
                    @Override
                    public BlockData get(BlockUpdateEvent1219 event) {
                        return event.getBlockData();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent1219.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(BlockUpdateEvent1219 event) {
                        return event.getOriginalBlock();
                    }
                }, EventValues.TIME_NOW);
            }

            case "12110" -> {
                EventValues.registerEventValue(BlockUpdateEvent12110.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(BlockUpdateEvent12110 event) {
                        return event.getLocation().toCenterLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent12110.class, Material.class, new Getter<>() {
                    @Override
                    public Material get(BlockUpdateEvent12110 event) {
                        return event.getMaterial();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent12110.class, BlockData.class, new Getter<>() {
                    @Override
                    public BlockData get(BlockUpdateEvent12110 event) {
                        return event.getBlockData();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(BlockUpdateEvent12110.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(BlockUpdateEvent12110 event) {
                        return event.getOriginalBlock();
                    }
                }, EventValues.TIME_NOW);
            }

        }
    }
}
