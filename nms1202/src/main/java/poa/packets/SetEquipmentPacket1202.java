package poa.packets;

import com.mojang.datafixers.util.Pair;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.world.entity.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SetEquipmentPacket1202 {

    public static Object packet(int id, String slot, ItemStack item){
        slot = slot.replace("off_hand", "offhand")
                .replace("off hand", "offhand");

        EquipmentSlot equipmentSlot = EquipmentSlot.valueOf(slot.toUpperCase());

        List<Pair<EquipmentSlot, net.minecraft.world.item.ItemStack>> pairs = List.of(new Pair<>(equipmentSlot, net.minecraft.world.item.ItemStack.fromBukkitCopy(item)));

        return new ClientboundSetEquipmentPacket(id, pairs);

    }

}
