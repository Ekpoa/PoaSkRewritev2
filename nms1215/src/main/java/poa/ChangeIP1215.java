package poa;

import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;

public class ChangeIP1215 {

    public static void changeIp(Player player, String ip, int port){
        ((CraftPlayer) player).getHandle().connection.connection.address = new InetSocketAddress(ip, port);

    }

}
