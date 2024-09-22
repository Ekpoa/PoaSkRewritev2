package poa.packets.packetListener.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class PlayerInputEvent121 extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();


    float xxa;
    float zza;
    boolean isJumping;


    boolean isCancelled;

    public PlayerInputEvent121(@NotNull Player who, boolean async) {
        super(who, async);
    }

    public Vector getVector(){
        return new Vector(xxa, 0, zza);
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
