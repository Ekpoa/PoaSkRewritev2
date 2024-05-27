package poa;

import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class StartAutoSpinAttack1206 {

    public static void startSpinAttack(Player player, int ticks){
        ((CraftPlayer) player).getHandle().startAutoSpinAttack(ticks);
    }

}
