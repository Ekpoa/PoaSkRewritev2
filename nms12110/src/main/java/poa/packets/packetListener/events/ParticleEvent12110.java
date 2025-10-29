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

public class ParticleEvent12110 extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    @Getter
    @Setter
    Particle particle;
    @Getter
    @Setter
    int count;

    @Getter
    String version = "1.21.5";

    @Getter
    @Setter
    World world;

    @Getter
    @Setter
    double x;
    @Getter
    @Setter
    double y;
    @Getter
    @Setter
    double z;

    @Getter
    @Setter
    float xOffset;
    @Getter
    @Setter
    float yOffset;
    @Getter
    @Setter
    float zOffset;

    @Getter
    @Setter
    float maxSpeed;


    boolean isCancelled;

    public ParticleEvent12110(@NotNull Player who, boolean async) {
        super(who, async);
    }


    public Location getLocation(){
        return new Location(world, x,y,z);
    }

    public Vector getOffset(){
        return new Vector(xOffset, yOffset, zOffset);
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
