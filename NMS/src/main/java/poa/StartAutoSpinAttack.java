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
            case "1213" -> StartAutoSpinAttack1213.startSpinAttack(player, ticks);
            case "1214" -> StartAutoSpinAttack1214.startSpinAttack(player, ticks);
            case "1215" -> StartAutoSpinAttack1215.startSpinAttack(player, ticks);
            case "1216" -> StartAutoSpinAttack1216.startSpinAttack(player, ticks);
            case "1217" -> StartAutoSpinAttack1217.startSpinAttack(player, ticks);
            case "1218" -> StartAutoSpinAttack1218.startSpinAttack(player, ticks);
            case "1219" -> StartAutoSpinAttack1219.startSpinAttack(player, ticks);
            case "12110" -> StartAutoSpinAttack12110.startSpinAttack(player, ticks);
            case "12111" -> StartAutoSpinAttack12111.startSpinAttack(player, ticks);
        }
    }


}
