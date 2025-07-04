package poa.packets.packetListener.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class SoundEvent1217 extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();


    double x;
    double y;
    double z;

    float pitch;
    float volume;

    String id;
    String source;

    boolean isEntity = false;

    long seed;

    boolean isCancelled;

    public SoundEvent1217(@NotNull Player who, boolean async) {
        super(who, async);
    }

    public Location getLocation(){
        return new Location(player.getWorld(), x, y, z);
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
