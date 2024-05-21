package poa;


import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;

public class ChangeIP1204 {

    public static void changeIp(Player player, String ip, int port){
        ((CraftPlayer) player).getHandle().connection.connection.address = new InetSocketAddress(ip, port);
    }

}
