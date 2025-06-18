package poa.util;

import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;

public class getEntityFromID {

    public static Entity getEntityFromId(int id){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> EntityFromID1202.getEntityFromId(id);
            case "1204" -> EntityFromID1204.getEntityFromId(id);
            case "1206" -> EntityFromID1206.getEntityFromId(id);
            case "121" -> EntityFromID121.getEntityFromId(id);
            case "1211" -> EntityFromID1211.getEntityFromId(id);
            case "1213" -> EntityFromID1213.getEntityFromId(id);
            case "1214" -> EntityFromID1214.getEntityFromId(id);
            case "1215" -> EntityFromID1215.getEntityFromId(id);
            default -> null;
        };
    }

}
