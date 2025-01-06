package poa;

import org.bukkit.Material;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.net.InetSocketAddress;

public class ChangeIP1213 {

    public static void changeIp(Player player, String ip, int port){
        ((CraftPlayer) player).getHandle().connection.connection.address = new InetSocketAddress(ip, port);

    }

}
