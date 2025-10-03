package poa.packets.packetListener.events;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

@Setter
@Getter
public class PlayerActionEvent1213 extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    boolean isCancelled;

    Location location;
    String actionString;
    ServerboundPlayerActionPacket.Action action;
    int sequence;


    Block block;


    public PlayerActionEvent1213(@NotNull Player who, boolean async) {
        super(who, async);
    }


    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }


}
