package poa;

import net.minecraft.world.entity.EntityType;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;

public class ChangeIP121 {

    public static void changeIp(Player player, String ip, int port){
        ((CraftPlayer) player).getHandle().connection.connection.address = new InetSocketAddress(ip, port);
    }

}
