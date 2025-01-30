package poa.poaskrewritev2.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import poa.packets.packetListener.events.*;
import poa.poaskrewritev2.events.eventvalues.RegisterParticle;
import poa.util.BukkitVersion;

public class SkParticleEvent extends SkriptEvent {

    static {
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> Skript.registerEvent("particle send", SkParticleEvent.class, ParticleEvent1202.class, "particle send");
            case "1204" -> Skript.registerEvent("particle send", SkParticleEvent.class, ParticleEvent1204.class, "particle send");
            case "1206" -> Skript.registerEvent("particle send", SkParticleEvent.class, ParticleEvent1206.class, "particle send");
            case "121" -> Skript.registerEvent("particle send", SkParticleEvent.class, ParticleEvent121.class, "particle send");
            case "1211" -> Skript.registerEvent("particle send", SkParticleEvent.class, ParticleEvent1211.class, "particle send");
            case "1213" -> Skript.registerEvent("particle send", SkParticleEvent.class, ParticleEvent1213.class, "particle send");
            case "1214" -> Skript.registerEvent("particle send", SkParticleEvent.class, ParticleEvent1214.class, "particle send");
        }
        RegisterParticle.registerValues();
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
        return "particle send";
    }


}
