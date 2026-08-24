package poa.poaskrewritev2.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import poa.packets.packetListener.events.ParticleEvent1202;
import poa.poaskrewritev2.events.bukkitevents.ConsoleLogEvent;

public class SkConsoleLogEvent extends SkriptEvent {

    static {
        Skript.registerEvent("console log", SkConsoleLogEvent.class, ConsoleLogEvent.class, "console log");
        EventValues.registerEventValue(ConsoleLogEvent.class, String.class, new Getter<>() {
            @Override
            public String get(ConsoleLogEvent event) {
                return event.getStrippedMessage();
            }
        });
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
        return "console log";
    }
}
