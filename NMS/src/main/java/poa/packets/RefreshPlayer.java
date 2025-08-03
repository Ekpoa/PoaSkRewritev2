package poa.packets;

import org.bukkit.entity.Player;
import poa.util.*;

public class RefreshPlayer {

    public static void refreshPlayer(Player player){
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> RefreshPlayer1202.refreshPlayer(player);
            case "1204" -> RefreshPlayer1204.refreshPlayer(player);
            case "1206" -> RefreshPlayer1206.refreshPlayer(player);
            case "121" -> RefreshPlayer121.refreshPlayer(player);
            case "1211" -> RefreshPlayer1211.refreshPlayer(player);
            case "1213" -> RefreshPlayer1213.refreshPlayer(player);
            case "1214" -> RefreshPlayer1214.refreshPlayer(player);
            case "1215" -> RefreshPlayer1215.refreshPlayer(player);
            case "1216" -> RefreshPlayer1216.refreshPlayer(player);
            case "1217" -> RefreshPlayer1217.refreshPlayer(player);
            case "1218" -> RefreshPlayer1218.refreshPlayer(player);
        }
    }

}
