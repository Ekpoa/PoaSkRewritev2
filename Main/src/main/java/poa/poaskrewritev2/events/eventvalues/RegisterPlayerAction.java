package poa.poaskrewritev2.events.eventvalues;

import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;
import poa.packets.packetListener.events.*;
import poa.util.BukkitVersion;

public class RegisterPlayerAction {

    @SuppressWarnings("removal")
    public static void registerValues(){
        switch (BukkitVersion.getBukkitVersion()) {
            case "121" -> {
                EventValues.registerEventValue(PlayerActionEvent121.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(PlayerActionEvent121 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent121.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(PlayerActionEvent121 event) {
                        return event.getSequence();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent121.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerActionEvent121 event) {
                        return event.getActionString();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent121.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(PlayerActionEvent121 event) {
                        return event.getBlock();
                    }
                }, EventValues.TIME_NOW);
            }

            case "1211" -> {
                EventValues.registerEventValue(PlayerActionEvent1211.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(PlayerActionEvent1211 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1211.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(PlayerActionEvent1211 event) {
                        return event.getSequence();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1211.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerActionEvent1211 event) {
                        return event.getActionString();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1211.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(PlayerActionEvent1211 event) {
                        return event.getBlock();
                    }
                }, EventValues.TIME_NOW);
            }

            case "1213" -> {
                EventValues.registerEventValue(PlayerActionEvent1213.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(PlayerActionEvent1213 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1213.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(PlayerActionEvent1213 event) {
                        return event.getSequence();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1213.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerActionEvent1213 event) {
                        return event.getActionString();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1213.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(PlayerActionEvent1213 event) {
                        return event.getBlock();
                    }
                }, EventValues.TIME_NOW);
            }

            case "1214" -> {
                EventValues.registerEventValue(PlayerActionEvent1214.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(PlayerActionEvent1214 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1214.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(PlayerActionEvent1214 event) {
                        return event.getSequence();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1214.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerActionEvent1214 event) {
                        return event.getActionString();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1214.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(PlayerActionEvent1214 event) {
                        return event.getBlock();
                    }
                }, EventValues.TIME_NOW);
            }

            case "1215" -> {
                EventValues.registerEventValue(PlayerActionEvent1215.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(PlayerActionEvent1215 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1215.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(PlayerActionEvent1215 event) {
                        return event.getSequence();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1215.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerActionEvent1215 event) {
                        return event.getActionString();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1215.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(PlayerActionEvent1215 event) {
                        return event.getBlock();
                    }
                }, EventValues.TIME_NOW);
            }

            case "1216" -> {
                EventValues.registerEventValue(PlayerActionEvent1216.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(PlayerActionEvent1216 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1216.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(PlayerActionEvent1216 event) {
                        return event.getSequence();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1216.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerActionEvent1216 event) {
                        return event.getActionString();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1216.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(PlayerActionEvent1216 event) {
                        return event.getBlock();
                    }
                }, EventValues.TIME_NOW);
            }

            case "1217" -> {
                EventValues.registerEventValue(PlayerActionEvent1217.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(PlayerActionEvent1217 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1217.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(PlayerActionEvent1217 event) {
                        return event.getSequence();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1217.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerActionEvent1217 event) {
                        return event.getActionString();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1217.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(PlayerActionEvent1217 event) {
                        return event.getBlock();
                    }
                }, EventValues.TIME_NOW);
            }

            case "1218" -> {
                EventValues.registerEventValue(PlayerActionEvent1218.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(PlayerActionEvent1218 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1218.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(PlayerActionEvent1218 event) {
                        return event.getSequence();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1218.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerActionEvent1218 event) {
                        return event.getActionString();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1218.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(PlayerActionEvent1218 event) {
                        return event.getBlock();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1219" -> {
                EventValues.registerEventValue(PlayerActionEvent1219.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(PlayerActionEvent1219 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1219.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(PlayerActionEvent1219 event) {
                        return event.getSequence();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1219.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerActionEvent1219 event) {
                        return event.getActionString();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent1219.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(PlayerActionEvent1219 event) {
                        return event.getBlock();
                    }
                }, EventValues.TIME_NOW);
            }

            case "12110" -> {
                EventValues.registerEventValue(PlayerActionEvent12110.class, Location.class, new Getter<>() {
                    @Override
                    public Location get(PlayerActionEvent12110 event) {
                        return event.getLocation();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent12110.class, Integer.class, new Getter<>() {
                    @Override
                    public Integer get(PlayerActionEvent12110 event) {
                        return event.getSequence();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent12110.class, String.class, new Getter<>() {
                    @Override
                    public String get(PlayerActionEvent12110 event) {
                        return event.getActionString();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(PlayerActionEvent12110.class, Block.class, new Getter<>() {
                    @Override
                    public Block get(PlayerActionEvent12110 event) {
                        return event.getBlock();
                    }
                }, EventValues.TIME_NOW);
            }

        }

    }



}
