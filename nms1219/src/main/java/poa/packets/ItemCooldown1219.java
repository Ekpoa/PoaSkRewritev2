package poa.packets;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundCooldownPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemCooldown1219 {

    public static Object itemCooldown(org.bukkit.inventory.ItemStack itemStack, int cooldown){
        final ItemStack nmsItem = ItemStack.fromBukkitCopy(itemStack);
        final Item item = nmsItem.getItem();

        final ResourceLocation key = BuiltInRegistries.ITEM.getKey(item);

        return new ClientboundCooldownPacket(key, cooldown);
    }

}
