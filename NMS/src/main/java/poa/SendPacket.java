package poa;

import org.bukkit.entity.Player;
import poa.util.BukkitVersion;

public class SendPacket {
    public static void sendPacket(Player player, Object packet){
         switch (BukkitVersion.getBukkitVersion()){
             case "1202" -> SendPacket1202.sendPacket(player, packet);
            case "1204" -> SendPacket1204.sendPacket(player, packet);
            case "1206" -> SendPacket1206.sendPacket(player, packet);
        }

    }
}