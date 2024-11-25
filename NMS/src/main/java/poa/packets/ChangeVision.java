package poa.packets;

import org.bukkit.entity.Player;
import poa.util.BukkitVersion;

public class ChangeVision {

    public static void changeVision(Player player, String entity) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> ChangeVision1202.changeVision(player, entity);
            case "1204" -> ChangeVision1204.changeVision(player, entity);
            case "1206" -> ChangeVision1206.changeVision(player, entity);
            case "121" -> ChangeVision121.changeVision(player, entity);
            case "1211" -> ChangeVision1211.changeVision(player, entity);
            case "1213" -> ChangeVision1213.changeVision(player, entity);
        }
    }

}
