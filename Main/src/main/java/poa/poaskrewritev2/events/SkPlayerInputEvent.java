package poa.poaskrewritev2.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleEvent;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInputEvent;
import org.jetbrains.annotations.Nullable;
import poa.packets.packetListener.events.*;
import poa.poaskrewritev2.events.EventValues.RegisterInput;
import poa.poaskrewritev2.events.EventValues.RegisterParticle;
import poa.util.BukkitVersion;

import java.util.List;

public class SkPlayerInputEvent extends SkriptEvent {

    static {
        final String bukkitVersion = BukkitVersion.getBukkitVersion();
        switch (bukkitVersion) {
            case "1202" ->
                    Skript.registerEvent("player input", SkPlayerInputEvent.class, PlayerInputEvent1202.class, "player input [packet]");
            case "1204" ->
                    Skript.registerEvent("player input", SkPlayerInputEvent.class, PlayerInputEvent1204.class, "player input [packet]");
            case "1206" ->
                    Skript.registerEvent("player input", SkPlayerInputEvent.class, PlayerInputEvent1206.class, "player input [packet]");
            case "121" ->
                    Skript.registerEvent("player input", SkPlayerInputEvent.class, PlayerInputEvent121.class, "player input [packet]");
            case "1211" ->
                    Skript.registerEvent("player input", SkPlayerInputEvent.class, PlayerInputEvent1211.class, "player input [packet]");
            default ->
                    Skript.registerEvent("player input", SimpleEvent.class, PlayerInputEvent.class, "player input [packet]");
        }
        RegisterInput.registerValues();

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
        return "player input [packet]";
    }


}
