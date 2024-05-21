package poa;


import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;

public class ChangeIP1202 {

    public static void changeIp(Player player, String ip, int port){
        ((CraftPlayer) player).getHandle().connection.connection.address = new InetSocketAddress(ip, port);
    }

}
