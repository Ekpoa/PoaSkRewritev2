package poa.packets;

import com.mojang.datafixers.util.Pair;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.world.entity.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SetEquipmentPacket12111 {

    public static Object packet(int id, String nmsSlot, ItemStack item){

        EquipmentSlot equipmentSlot = EquipmentSlot.valueOf(nmsSlot.toUpperCase());


        List<Pair<EquipmentSlot, net.minecraft.world.item.ItemStack>> pairs = List.of(new Pair<>(equipmentSlot, net.minecraft.world.item.ItemStack.fromBukkitCopy(item)));

        return new ClientboundSetEquipmentPacket(id, pairs);

    }

}
