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

public class SkSoundEvent extends SkriptEvent {

    static {
        switch (BukkitVersion.getBukkitVersion()){
            case "1206" -> Skript.registerEvent("Sound Event", SkSoundEvent.class, SoundEvent1206.class, "sound event");
            case "121" -> Skript.registerEvent("Sound Event", SkSoundEvent.class, SoundEvent121.class, "sound event");
            case "1211" -> Skript.registerEvent("Sound Event", SkSoundEvent.class, SoundEvent1211.class, "sound event");
            case "1213" -> Skript.registerEvent("Sound Event", SkSoundEvent.class, SoundEvent1213.class, "sound event");
            case "1214" -> Skript.registerEvent("Sound Event", SkSoundEvent.class, SoundEvent1214.class, "sound event");
            case "1215" -> Skript.registerEvent("Sound Event", SkSoundEvent.class, SoundEvent1215.class, "sound event");
            case "1216" -> Skript.registerEvent("Sound Event", SkSoundEvent.class, SoundEvent1216.class, "sound event");
            case "1217" -> Skript.registerEvent("Sound Event", SkSoundEvent.class, SoundEvent1217.class, "sound event");
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
        return "Sound Event";
    }


}
