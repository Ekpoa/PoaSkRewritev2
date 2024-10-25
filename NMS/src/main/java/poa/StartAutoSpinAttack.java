package poa;

import org.bukkit.entity.Player;
import poa.util.BukkitVersion;

public class StartAutoSpinAttack {


    public static void startSpinAttack(Player player, int ticks) {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> StartAutoSpinAttack1202.startSpinAttack(player, ticks);
            case "1204" -> StartAutoSpinAttack1204.startSpinAttack(player, ticks);
            case "1206" -> StartAutoSpinAttack1206.startSpinAttack(player, ticks);
            case "121" -> StartAutoSpinAttack121.startSpinAttack(player, ticks);
            case "1211" -> StartAutoSpinAttack1211.startSpinAttack(player, ticks);
        }
    }


}
