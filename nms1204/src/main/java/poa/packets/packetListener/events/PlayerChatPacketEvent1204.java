package poa.packets.packetListener.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PlayerChatPacketEvent1204 extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();


    boolean cancelled;

    @Getter
    @Setter
    boolean isOverlay;


    @Getter
    @Setter
    String string;


    @Getter
    @Setter
    UUID sender;


    public PlayerChatPacketEvent1204(@NotNull Player who, boolean async) {
        super(who, async);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
