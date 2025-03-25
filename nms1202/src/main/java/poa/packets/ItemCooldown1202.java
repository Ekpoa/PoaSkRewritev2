package poa.packets;

import net.minecraft.network.protocol.game.ClientboundCooldownPacket;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemCooldown1202 {

    public static Object itemCooldown(org.bukkit.inventory.ItemStack itemStack, int cooldown) {
        final ItemStack nmsItem = ItemStack.fromBukkitCopy(itemStack);
        final Item item = nmsItem.getItem();

        return new ClientboundCooldownPacket(item, cooldown);
    }

}
