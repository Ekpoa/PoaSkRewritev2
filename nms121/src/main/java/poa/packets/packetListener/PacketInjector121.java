package poa.packets.packetListener;

import io.netty.channel.ChannelPipeline;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class PacketInjector121 {


    CraftPlayer craftPlayer;
    Player player;
    String id;

    public PacketInjector121(Player player, String id){
        this.craftPlayer = (CraftPlayer) player;
        this.player = player;
        this.id = player.getName() + "-" + id + "-";
    }

    public void inject(Player player) {
        ChannelPipeline pipeline = getChannelPipeline((CraftPlayer) player);
        pipeline.addBefore("packet_handler", id, new PacketHandler121(player));
        System.out.println("Injected packet listener into " + this.player.getName() + " with id " + id);
    }

    private static ChannelPipeline getChannelPipeline(CraftPlayer player) {
        return player.getHandle().connection.connection.channel.pipeline();
    }

    public void uninjectPlayer() {
        if (this.craftPlayer.getHandle().connection.connection.channel.pipeline().get(id) != null) {
            this.craftPlayer.getHandle().connection.connection.channel.pipeline().remove(id);
            System.out.println("Uninjected packet listener into " + this.player.getName() + " with id " + id);
        }
    }
}