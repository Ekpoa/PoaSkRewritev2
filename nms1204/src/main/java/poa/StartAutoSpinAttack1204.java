package poa;


import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class StartAutoSpinAttack1204 {

    public static void startSpinAttack(Player player, int ticks){
        ((CraftPlayer) player).getHandle().startAutoSpinAttack(ticks);
    }

}
