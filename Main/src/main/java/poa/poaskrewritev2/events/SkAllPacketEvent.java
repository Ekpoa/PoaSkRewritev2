package poa.poaskrewritev2.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import poa.packets.packetListener.events.*;
import poa.poaskrewritev2.PoaSkRewritev2;
import poa.poaskrewritev2.events.eventvalues.RegisterAllPacket;
import poa.poaskrewritev2.events.eventvalues.RegisterBlockUpdate;
import poa.util.BukkitVersion;

import java.util.logging.Level;

public class SkAllPacketEvent extends SkriptEvent {

    static {
        PoaSkRewritev2.getINSTANCE().getLogger().log(Level.SEVERE, "LOADEDDDDDDDD");
        switch (BukkitVersion.getBukkitVersion()){
            case "121" -> Skript.registerEvent("every packet", SkAllPacketEvent.class, AllPacketEvent121.class, "every packet");
            case "1211" -> Skript.registerEvent("every packet", SkAllPacketEvent.class, AllPacketEvent1211.class, "every packet");
            case "1213" -> Skript.registerEvent("every packet", SkAllPacketEvent.class, AllPacketEvent1213.class, "every packet");
            case "1214" -> Skript.registerEvent("every packet", SkAllPacketEvent.class, AllPacketEvent1214.class, "every packet");
            case "1215" -> Skript.registerEvent("every packet", SkAllPacketEvent.class, AllPacketEvent1215.class, "every packet");
            case "1216" -> Skript.registerEvent("every packet", SkAllPacketEvent.class, AllPacketEvent1216.class, "every packet");
            case "1217" -> Skript.registerEvent("every packet", SkAllPacketEvent.class, AllPacketEvent1217.class, "every packet");
            case "1218" -> Skript.registerEvent("every packet", SkAllPacketEvent.class, AllPacketEvent1218.class, "every packet");
            case "12110" -> {
                Skript.registerEvent("every packet", SkAllPacketEvent.class, AllPacketEvent12110.class, "every packet");
                PoaSkRewritev2.getINSTANCE().getLogger().log(Level.SEVERE, "REGISTERED");
            }
        }
        RegisterAllPacket.registerValues();
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
        return "every packet";
    }


}
