package poa;

import net.minecraft.network.protocol.Packet;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SendPacket1202 {
    public static void sendPacket(Player player, Object packet){
        ((CraftPlayer) player).getHandle().connection.send((Packet<?>) packet);

    }
}