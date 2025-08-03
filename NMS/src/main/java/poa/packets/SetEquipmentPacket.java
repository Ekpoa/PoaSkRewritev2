package poa.packets;

import org.bukkit.inventory.ItemStack;
import poa.packets.SetEquipmentPacket1202;
import poa.packets.SetEquipmentPacket1204;
import poa.packets.SetEquipmentPacket1206;
import poa.util.BukkitVersion;

public class SetEquipmentPacket {

    public static Object packet(int id, String slot, ItemStack item){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> SetEquipmentPacket1202.packet(id, slot, item);
            case "1204" -> SetEquipmentPacket1204.packet(id, slot, item);
            case "1206" -> SetEquipmentPacket1206.packet(id, slot, item);
            case "121" -> SetEquipmentPacket121.packet(id, slot, item);
            case "1211" -> SetEquipmentPacket1211.packet(id, slot, item);
            case "1213" -> SetEquipmentPacket1213.packet(id, slot, item);
            case "1214" -> SetEquipmentPacket1214.packet(id, slot, item);
            case "1215" -> SetEquipmentPacket1215.packet(id, slot, item);
            case "1216" -> SetEquipmentPacket1216.packet(id, slot, item);
            case "1217" -> SetEquipmentPacket1217.packet(id, slot, item);
            case "1218" -> SetEquipmentPacket1218.packet(id, slot, item);
            default -> null;
        };

    }

}
