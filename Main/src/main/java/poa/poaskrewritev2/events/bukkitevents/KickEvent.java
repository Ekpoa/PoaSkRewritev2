package poa.poaskrewritev2.events.bukkitevents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class KickEvent implements Listener {

    @EventHandler
    public void onKick(PlayerKickEvent e){
        if (e.getCause() == PlayerKickEvent.Cause.SELF_INTERACTION)
            e.setCancelled(true);
    }

}
