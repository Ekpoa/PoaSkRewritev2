package poa.poaskrewritev2.events.bukkitevents;

import lombok.Getter;
import org.apache.logging.log4j.core.LogEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class ConsoleLogEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final LogEvent logEvent;
    private final String message;
    private final String strippedMessage;

    private boolean cancelled;

    public ConsoleLogEvent(LogEvent logEvent, String message, String strippedMessage) {
        super(!Bukkit.isPrimaryThread());

        this.logEvent = logEvent;
        this.message = message;
        this.strippedMessage = strippedMessage;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}