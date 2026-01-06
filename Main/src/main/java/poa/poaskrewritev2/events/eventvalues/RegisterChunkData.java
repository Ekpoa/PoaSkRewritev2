package poa.poaskrewritev2.events.eventvalues;

import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Chunk;
import org.bukkit.World;
import poa.packets.packetListener.ChunkDataPacketEvent1211;
import poa.packets.packetListener.events.*;
import poa.util.BukkitVersion;

public class RegisterChunkData {

    public static void registerValues(){
        switch (BukkitVersion.getBukkitVersion()) {
            case "1211" -> {
                EventValues.registerEventValue(ChunkDataPacketEvent1211.class, Chunk.class, new Getter<>() {
                    @Override
                    public Chunk get(ChunkDataPacketEvent1211 event) {
                        return event.getWorld().getChunkAt(event.getChunkX(), event.getChunkZ());
                    }
                }, EventValues.TIME_NOW);
            }
            case "1213" -> {
                EventValues.registerEventValue(ChunkDataPacketEvent1213.class, Chunk.class, new Getter<>() {
                    @Override
                    public Chunk get(ChunkDataPacketEvent1213 event) {
                        return event.getWorld().getChunkAt(event.getChunkX(), event.getChunkZ());
                    }
                }, EventValues.TIME_NOW);
            }
            case "1214" -> {
                EventValues.registerEventValue(ChunkDataPacketEvent1214.class, Chunk.class, new Getter<>() {
                    @Override
                    public Chunk get(ChunkDataPacketEvent1214 event) {
                        return event.getWorld().getChunkAt(event.getChunkX(), event.getChunkZ());
                    }
                }, EventValues.TIME_NOW);
            }
            case "1215" -> {
                EventValues.registerEventValue(ChunkDataPacketEvent1215.class, Chunk.class, new Getter<>() {
                    @Override
                    public Chunk get(ChunkDataPacketEvent1215 event) {
                        return event.getWorld().getChunkAt(event.getChunkX(), event.getChunkZ());
                    }
                }, EventValues.TIME_NOW);
            }
            case "1216" -> {
                EventValues.registerEventValue(ChunkDataPacketEvent1216.class, Chunk.class, new Getter<>() {
                    @Override
                    public Chunk get(ChunkDataPacketEvent1216 event) {
                        return event.getWorld().getChunkAt(event.getChunkX(), event.getChunkZ());
                    }
                }, EventValues.TIME_NOW);
            }
            case "1217" -> {
                EventValues.registerEventValue(ChunkDataPacketEvent1217.class, Chunk.class, new Getter<>() {
                    @Override
                    public Chunk get(ChunkDataPacketEvent1217 event) {
                        return event.getWorld().getChunkAt(event.getChunkX(), event.getChunkZ());
                    }
                }, EventValues.TIME_NOW);
            }
            case "1218" -> {
                EventValues.registerEventValue(ChunkDataPacketEvent1218.class, Chunk.class, new Getter<>() {
                    @Override
                    public Chunk get(ChunkDataPacketEvent1218 event) {
                        return event.getWorld().getChunkAt(event.getChunkX(), event.getChunkZ());
                    }
                }, EventValues.TIME_NOW);
            }

            case "12110" -> {
                EventValues.registerEventValue(ChunkDataPacketEvent12110.class, Chunk.class, new Getter<>() {
                    @Override
                    public Chunk get(ChunkDataPacketEvent12110 event) {
                        return event.getWorld().getChunkAt(event.getChunkX(), event.getChunkZ());
                    }
                }, EventValues.TIME_NOW);
            }
            case "12111" -> {
                EventValues.registerEventValue(ChunkDataPacketEvent12111.class, Chunk.class, new Getter<>() {
                    @Override
                    public Chunk get(ChunkDataPacketEvent12111 event) {
                        return event.getWorld().getChunkAt(event.getChunkX(), event.getChunkZ());
                    }
                }, EventValues.TIME_NOW);
            }
        }

    }
}
