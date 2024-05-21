package poa;

import org.bukkit.entity.Player;
import poa.util.BukkitVersion;

public class ChangeIP {


    public static void changeIp(Player player, String ip, int port) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> ChangeIP1202.changeIp(player, ip, port);
            case "1204" -> ChangeIP1204.changeIp(player, ip, port);
            case "1206" -> ChangeIP1206.changeIp(player, ip, port);
        }

    }

}
