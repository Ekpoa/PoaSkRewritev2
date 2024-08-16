package poa.packets.packetListener;

import io.netty.channel.ChannelPipeline;
import net.minecraft.network.Connection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerConnectionListener;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.logging.Level;

public class PacketInjector1204 {


    CraftPlayer craftPlayer;
    Player player;
    String id;

    public PacketInjector1204(Player player){
        this.craftPlayer = (CraftPlayer) player;
        this.player = player;
        this.id = player.getName() + "-PoaSK-";
    }


    public void inject(Player player) {
        Bukkit.getLogger().log(Level.INFO, "Injected " + player.getName());
        ChannelPipeline pipeline = getChannelPipeline((CraftPlayer) player);
        pipeline.addBefore("packet_handler", id, new PacketHandler1204(player));
    }
    private static ChannelPipeline getChannelPipeline(CraftPlayer player) {
        return player.getHandle().connection.connection.channel.pipeline();
    }

    public void uninjectPlayer() {
        if (this.craftPlayer.getHandle().connection.connection.channel.pipeline().get(id) != null) {
            Bukkit.getLogger().log(Level.INFO, "Uninjected " + player.getName());
            this.craftPlayer.getHandle().connection.connection.channel.pipeline().remove(id);
            System.out.println("Uninjected packet listener into " + this.player.getName());
        }
    }
}