package poa.packetListener;

import io.netty.channel.ChannelPipeline;
import net.minecraft.network.Connection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerConnectionListener;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

public class PacketInjector1206 {


    CraftPlayer craftPlayer;
    Player player;
    String id;

    public PacketInjector1206(Player player){
        this.craftPlayer = (CraftPlayer) player;
        this.player = player;
        this.id = player.getName() + "-PoaSK-";
    }

    public void inject(Player player) {
        ChannelPipeline pipeline = getChannelPipeline((CraftPlayer) player);
        pipeline.addBefore("packet_handler", id, new PacketHandler1206(player));
    }

    private static ChannelPipeline getChannelPipeline(CraftPlayer player) {
        return player.getHandle().connection.connection.channel.pipeline();
    }

//    public void injectPlayer() {
//        final ServerConnectionListener serverConnection = MinecraftServer.getServer().getConnection();
//
//        List<Connection> connections = serverConnection.getConnections();
//
//
//        final Connection playerConnection = connections.stream()
//                .filter(connection -> connection.getRemoteAddress() instanceof InetSocketAddress)
//                .filter(connection -> ((InetSocketAddress) connection.getRemoteAddress()).getAddress() == address)
//                .findAny().orElseThrow(IllegalArgumentException::new);
//        playerConnection.channel.pipeline().addBefore(
//                "packet_handler", id, new PacketHandler1206(this.player));
//
//        System.out.println("Injected packet listener into " + this.player.getName());
//
//    }
//
    public void uninjectPlayer() {
        if (this.craftPlayer.getHandle().connection.connection.channel.pipeline().get(id) != null) {
            this.craftPlayer.getHandle().connection.connection.channel.pipeline().remove(id);
            System.out.println("Uninjected packet listener into " + this.player.getName());
        }
    }
}