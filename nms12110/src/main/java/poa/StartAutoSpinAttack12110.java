package poa;

import net.minecraft.world.item.ItemStack;
import org.bukkit.Material;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class StartAutoSpinAttack12110 {

    public static void startSpinAttack(Player player, int ticks) {
        ((CraftPlayer) player).getHandle().startAutoSpinAttack(ticks, 0, ItemStack.fromBukkitCopy(new org.bukkit.inventory.ItemStack(Material.TRIDENT)));
    }
}