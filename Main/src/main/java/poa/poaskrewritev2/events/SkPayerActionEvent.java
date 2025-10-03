package poa.poaskrewritev2.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import poa.packets.packetListener.events.*;
import poa.poaskrewritev2.events.eventvalues.RegisterBlockUpdate;
import poa.poaskrewritev2.events.eventvalues.RegisterPlayerAction;
import poa.util.BukkitVersion;

public class SkPayerActionEvent extends SkriptEvent {

    static {
        switch (BukkitVersion.getBukkitVersion()){
            case "121" -> Skript.registerEvent("Player Action", SkPayerActionEvent.class, PlayerActionEvent121.class, "player action packet");
            case "1211" -> Skript.registerEvent("Player Action", SkPayerActionEvent.class, PlayerActionEvent1211.class, "player action packet");
            case "1213" -> Skript.registerEvent("Player Action", SkPayerActionEvent.class, PlayerActionEvent1213.class, "player action packet");
            case "1214" -> Skript.registerEvent("Player Action", SkPayerActionEvent.class, PlayerActionEvent1214.class, "player action packet");
            case "1215" -> Skript.registerEvent("Player Action", SkPayerActionEvent.class, PlayerActionEvent1215.class, "player action packet");
            case "1216" -> Skript.registerEvent("Player Action", SkPayerActionEvent.class, PlayerActionEvent1216.class, "player action packet");
            case "1217" -> Skript.registerEvent("Player Action", SkPayerActionEvent.class, PlayerActionEvent1217.class, "player action packet");
            case "1218" -> Skript.registerEvent("Player Action", SkPayerActionEvent.class, PlayerActionEvent1218.class, "player action packet");

        }
        RegisterPlayerAction.registerValues();
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
        return "Player Action";
    }


}
