package poa.poaskrewritev2.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.papermc.paper.event.player.PlayerTrackEntityEvent;
import io.papermc.paper.event.player.PlayerUntrackEntityEvent;
import org.bukkit.entity.Entity;
import poa.poaskrewritev2.events.eventvalues.RegisterChat;
import poa.poaskrewritev2.util.types.UUIDType;

public class SkriptEvents extends SimpleEvent {

    static {
        UUIDType.register();

        // Player load entity event
        Skript.registerEvent("Player Load Entity", SimpleEvent.class, PlayerTrackEntityEvent.class,
                "player load entity");

        EventValues.registerEventValue(PlayerTrackEntityEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(PlayerTrackEntityEvent event) {
                return event.getEntity();
            }
        }, EventValues.TIME_NOW);

        //Unload
        Skript.registerEvent("Player unload Entity", SimpleEvent.class, PlayerUntrackEntityEvent.class,
                "player unload entity");

        EventValues.registerEventValue(PlayerUntrackEntityEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(PlayerUntrackEntityEvent event) {
                return event.getEntity();
            }
        }, EventValues.TIME_NOW);


        //System Chat
        RegisterChat.register();


    }

}
