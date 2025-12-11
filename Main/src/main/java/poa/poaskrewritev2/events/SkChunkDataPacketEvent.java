package poa.poaskrewritev2.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import poa.packets.packetListener.ChunkDataPacketEvent1211;
import poa.packets.packetListener.events.*;
import poa.util.BukkitVersion;

public class SkChunkDataPacketEvent extends SkriptEvent {

    static {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1211" -> Skript.registerEvent(
                    "Chunk Data Packet",
                    SkChunkDataPacketEvent.class,
                    ChunkDataPacketEvent1211.class,
                    "chunk data packet"
            );

            case "1213" -> Skript.registerEvent(
                    "Chunk Data Packet",
                    SkChunkDataPacketEvent.class,
                    ChunkDataPacketEvent1213.class,
                    "chunk data packet"
            );

            case "1214" -> Skript.registerEvent(
                    "Chunk Data Packet",
                    SkChunkDataPacketEvent.class,
                    ChunkDataPacketEvent1214.class,
                    "chunk data packet"
            );

            case "1215" -> Skript.registerEvent(
                    "Chunk Data Packet",
                    SkChunkDataPacketEvent.class,
                    ChunkDataPacketEvent1215.class,
                    "chunk data packet"
            );

            case "1216" -> Skript.registerEvent(
                    "Chunk Data Packet",
                    SkChunkDataPacketEvent.class,
                    ChunkDataPacketEvent1216.class,
                    "chunk data packet"
            );

            case "1217" -> Skript.registerEvent(
                    "Chunk Data Packet",
                    SkChunkDataPacketEvent.class,
                    ChunkDataPacketEvent1217.class,
                    "chunk data packet"
            );

            case "1218" -> Skript.registerEvent(
                    "Chunk Data Packet",
                    SkChunkDataPacketEvent.class,
                    ChunkDataPacketEvent1218.class,
                    "chunk data packet"
            );


            case "12110" -> Skript.registerEvent(
                    "Chunk Data Packet",
                    SkChunkDataPacketEvent.class,
                    ChunkDataPacketEvent12110.class,
                    "chunk data packet"
            );

        }
    }

    @Override
    public boolean canExecuteAsynchronously() {
        return true;
    }

    @Override
    public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "chunk data packet";
    }
}
