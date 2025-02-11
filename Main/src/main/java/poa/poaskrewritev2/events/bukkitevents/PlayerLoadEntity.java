package poa.poaskrewritev2.events.bukkitevents;

import io.papermc.paper.event.player.PlayerTrackEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import poa.packets.Metadata;
import poa.packets.SendPacket;
import poa.poaskrewritev2.PoaSkRewritev2;
import poa.util.GetPose;
import poa.util.GlowMap;

import java.util.List;

public class PlayerLoadEntity implements Listener {

    @EventHandler
    public void loadPlayer(PlayerTrackEntityEvent e){
        final Player player = e.getPlayer();
        if(!(e.getEntity() instanceof Player target))
            return;

        final List<Integer> ids = GlowMap.getGlowMap().get(player);

        if(ids == null || !ids.contains(target.getEntityId()))
            return;

        Bukkit.getScheduler().runTaskLater(PoaSkRewritev2.getINSTANCE(), () -> {
            Metadata metadata = new Metadata(target.getEntityId());
            metadata.setGlow(true);
            SendPacket.sendPacket(player, metadata.build());
        }, 2L);


    }

}
