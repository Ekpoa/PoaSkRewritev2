package poa.poaskrewritev2.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import poa.packets.packetListener.events.*;
import poa.poaskrewritev2.events.eventvalues.RegisterBlockUpdate;
import poa.util.BukkitVersion;

public class SkBlockUpdateEvent extends SkriptEvent {

    static {
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> Skript.registerEvent("Block Change Packet", SkBlockUpdateEvent.class, BlockUpdateEvent1202.class, "block change packet");
            case "1204" -> Skript.registerEvent("Block Change Packet", SkBlockUpdateEvent.class, BlockUpdateEvent1204.class, "block change packet");
            case "1206" -> Skript.registerEvent("Block Change Packet", SkBlockUpdateEvent.class, BlockUpdateEvent1206.class, "block change packet");
            case "121" -> Skript.registerEvent("Block Change Packet", SkBlockUpdateEvent.class, BlockUpdateEvent121.class, "block change packet");
            case "1211" -> Skript.registerEvent("Block Change Packet", SkBlockUpdateEvent.class, BlockUpdateEvent1211.class, "block change packet");
            case "1213" -> Skript.registerEvent("Block Change Packet", SkBlockUpdateEvent.class, BlockUpdateEvent1213.class, "block change packet");
            case "1214" -> Skript.registerEvent("Block Change Packet", SkBlockUpdateEvent.class, BlockUpdateEvent1214.class, "block change packet");
            case "1215" -> Skript.registerEvent("Block Change Packet", SkBlockUpdateEvent.class, BlockUpdateEvent1215.class, "block change packet");
            case "1216" -> Skript.registerEvent("Block Change Packet", SkBlockUpdateEvent.class, BlockUpdateEvent1216.class, "block change packet");
            case "1217" -> Skript.registerEvent("Block Change Packet", SkBlockUpdateEvent.class, BlockUpdateEvent1217.class, "block change packet");
        }
        RegisterBlockUpdate.registerValues();
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
        return "block change packet";
    }


}
