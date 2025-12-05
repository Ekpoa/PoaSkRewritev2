package poa.poaskrewritev2.events.eventvalues;

import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import poa.packets.packetListener.events.*;
import poa.util.BukkitVersion;

public class RegisterAllPacket {

    public static void registerValues(){
        switch (BukkitVersion.getBukkitVersion()) {
            case "121" -> {
                EventValues.registerEventValue(AllPacketEvent121.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(AllPacketEvent121 event) {
                        return event.isClientbound();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(AllPacketEvent121.class, Object.class, new Getter<>() {
                    @Override
                    public Object get(AllPacketEvent121 event) {
                        return event.getPacket();
                    }
                }, EventValues.TIME_NOW);
            }
            case "1211" -> {
                EventValues.registerEventValue(AllPacketEvent1211.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(AllPacketEvent1211 event) {
                        return event.isClientbound();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(AllPacketEvent1211.class, Object.class, new Getter<>() {
                    @Override
                    public Object get(AllPacketEvent1211 event) {
                        return event.getPacket();
                    }
                }, EventValues.TIME_NOW);
            }

            case "1213" -> {
                EventValues.registerEventValue(AllPacketEvent1213.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(AllPacketEvent1213 event) {
                        return event.isClientbound();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(AllPacketEvent1213.class, Object.class, new Getter<>() {
                    @Override
                    public Object get(AllPacketEvent1213 event) {
                        return event.getPacket();
                    }
                }, EventValues.TIME_NOW);
            }

            case "1214" -> {
                EventValues.registerEventValue(AllPacketEvent1214.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(AllPacketEvent1214 event) {
                        return event.isClientbound();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(AllPacketEvent1214.class, Object.class, new Getter<>() {
                    @Override
                    public Object get(AllPacketEvent1214 event) {
                        return event.getPacket();
                    }
                }, EventValues.TIME_NOW);
            }

            case "1215" -> {
                EventValues.registerEventValue(AllPacketEvent1215.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(AllPacketEvent1215 event) {
                        return event.isClientbound();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(AllPacketEvent1215.class, Object.class, new Getter<>() {
                    @Override
                    public Object get(AllPacketEvent1215 event) {
                        return event.getPacket();
                    }
                }, EventValues.TIME_NOW);
            }

            case "1216" -> {
                EventValues.registerEventValue(AllPacketEvent1216.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(AllPacketEvent1216 event) {
                        return event.isClientbound();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(AllPacketEvent1216.class, Object.class, new Getter<>() {
                    @Override
                    public Object get(AllPacketEvent1216 event) {
                        return event.getPacket();
                    }
                }, EventValues.TIME_NOW);
            }

            case "1217" -> {
                EventValues.registerEventValue(AllPacketEvent1217.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(AllPacketEvent1217 event) {
                        return event.isClientbound();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(AllPacketEvent1217.class, Object.class, new Getter<>() {
                    @Override
                    public Object get(AllPacketEvent1217 event) {
                        return event.getPacket();
                    }
                }, EventValues.TIME_NOW);
            }

            case "1218" -> {
                EventValues.registerEventValue(AllPacketEvent1218.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(AllPacketEvent1218 event) {
                        return event.isClientbound();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(AllPacketEvent1218.class, Object.class, new Getter<>() {
                    @Override
                    public Object get(AllPacketEvent1218 event) {
                        return event.getPacket();
                    }
                }, EventValues.TIME_NOW);
            }

            case "12110" -> {
                EventValues.registerEventValue(AllPacketEvent12110.class, Boolean.class, new Getter<>() {
                    @Override
                    public Boolean get(AllPacketEvent12110 event) {
                        return event.isClientbound();
                    }
                }, EventValues.TIME_NOW);
                EventValues.registerEventValue(AllPacketEvent12110.class, Object.class, new Getter<>() {
                    @Override
                    public Object get(AllPacketEvent12110 event) {
                        return event.getPacket();
                    }
                }, EventValues.TIME_NOW);
            }

        }
    }
}
