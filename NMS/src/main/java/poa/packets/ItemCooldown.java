package poa.packets;

import org.bukkit.inventory.ItemStack;
import poa.util.BukkitVersion;

public class ItemCooldown {


    public static Object packet(ItemStack item, int ticks){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> ItemCooldown1202.itemCooldown(item, ticks);
            case "1204" -> ItemCooldown1204.itemCooldown(item, ticks);
            case "1206" -> ItemCooldown1206.itemCooldown(item, ticks);
            case "121" -> ItemCooldown121.itemCooldown(item, ticks);
            case "1211" -> ItemCooldown1211.itemCooldown(item, ticks);
            case "1213" -> ItemCooldown1213.itemCooldown(item, ticks);
            case "1214" -> ItemCooldown1214.itemCooldown(item, ticks);
            case "1215" -> ItemCooldown1215.itemCooldown(item, ticks);
            case "1216" -> ItemCooldown1216.itemCooldown(item, ticks);
            case "1217" -> ItemCooldown1217.itemCooldown(item, ticks);
            case "1218" -> ItemCooldown1218.itemCooldown(item, ticks);
            case "1219" -> ItemCooldown1219.itemCooldown(item, ticks);
            case "12110" -> ItemCooldown12110.itemCooldown(item, ticks);
            default -> null;
        };

    }

}
