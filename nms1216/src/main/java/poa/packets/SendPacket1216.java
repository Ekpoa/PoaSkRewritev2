package poa.packets;

import net.minecraft.network.protocol.Packet;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SendPacket1216 {
    public static void sendPacket(Player player, Object packet){
        ((CraftPlayer) player).getHandle().connection.send((Packet<?>) packet);

    }
}