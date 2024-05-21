package poa;

import org.bukkit.inventory.ItemStack;
import poa.util.BukkitVersion;

public class SetEquipmentPacket {

    public static Object packet(int id, String slot, ItemStack item){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> SetEquipmentPacket1202.packet(id, slot, item);
            case "1204" -> SetEquipmentPacket1204.packet(id, slot, item);
            case "1206" -> SetEquipmentPacket1206.packet(id, slot, item);
            default -> null;
        };

    }

}
