package poa.packetListener;

import net.minecraft.network.Connection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerConnectionListener;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

public class PacketInjector1204 {


    CraftPlayer craftPlayer;
    InetAddress address;
    Player player;
    String id;

    public PacketInjector1204(Player player, InetAddress address){
        this.craftPlayer = (CraftPlayer) player;
        this.address = address;
        this.player = player;
        this.id = player.getName() + "-PoaSK-";
    }


    public void injectPlayer() {
        final ServerConnectionListener serverConnection = MinecraftServer.getServer().getConnection();

        List<Connection> connections = serverConnection.getConnections();


        final Connection playerConnection = connections.stream()
                .filter(connection -> connection.getRemoteAddress() instanceof InetSocketAddress)
                .filter(connection -> ((InetSocketAddress) connection.getRemoteAddress()).getAddress() == address)
                .findAny().orElseThrow(IllegalArgumentException::new);
        playerConnection.channel.pipeline().addBefore(
                "packet_handler", id, new PacketHandler1204());

        System.out.println("Injected packet listener into " + this.player.getName());

    }

    public void uninjectPlayer() {
        if (this.craftPlayer.getHandle().connection.connection.channel.pipeline().get(id) != null) {
            this.craftPlayer.getHandle().connection.connection.channel.pipeline().remove(id);
            System.out.println("Uninjected packet listener into " + this.player.getName());
        }
    }
}