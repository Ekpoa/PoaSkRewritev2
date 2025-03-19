package poa.poaskrewritev2.events.bukkitevents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import poa.packets.listener.PacketInjector;
import poa.poaskrewritev2.PoaSkRewritev2;
import poa.util.BukkitVersion;
import poa.util.GlowMap;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class JoinLeave implements Listener {

    @EventHandler
    public void connect(PlayerLoginEvent e) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> PacketInjector.inject(e, "PoaSk");
        }
    }

    @EventHandler
    public void join(PlayerJoinEvent e) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1204", "1206", "121", "1211", "1213", "1214" -> PacketInjector.inject(e.getPlayer(), "PoaSk");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        Map<Player, List<Integer>> glowMap = GlowMap.getGlowMap();
        if (glowMap == null) {
            PoaSkRewritev2.getINSTANCE().getLogger().log(Level.WARNING, "could not find glow map, its null: " + BukkitVersion.getBukkitVersion());
            return;
        }
        glowMap.remove(player);

        glowMap.entrySet().removeIf(entry -> entry.getValue().contains(player.getEntityId()));

        PacketInjector.unInject(player);
    }

}
