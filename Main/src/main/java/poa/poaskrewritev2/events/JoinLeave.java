package poa.poaskrewritev2.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import poa.packets.listener.PacketInjector;
import poa.util.BukkitVersion;
import poa.util.GlowMap;

import java.util.List;

public class JoinLeave implements Listener {



    @EventHandler
    public void connect(PlayerLoginEvent e){
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> PacketInjector.inject(e);
        }
    }

    @EventHandler
    public void join(PlayerJoinEvent e){
        switch (BukkitVersion.getBukkitVersion()) {
            case "1204", "1206", "121" -> PacketInjector.inject(e);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();

        GlowMap.getGlowMap().entrySet().removeIf(entry -> entry.getValue().contains(player.getEntityId()));

        GlowMap.getGlowMap().remove(player);

        PacketInjector.unInject(player);
    }

}
