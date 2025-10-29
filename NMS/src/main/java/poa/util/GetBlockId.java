package poa.util;

import org.bukkit.block.data.BlockData;
import poa.util.BukkitVersion;
import poa.util.GetBlockId1202;
import poa.util.GetBlockId1204;
import poa.util.GetBlockId1206;

public class GetBlockId {

    public static int blockId(BlockData blockData){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> GetBlockId1202.blockId(blockData);
            case "1204" -> GetBlockId1204.blockId(blockData);
            case "1206" -> GetBlockId1206.blockId(blockData);
            case "121" -> GetBlockId121.blockId(blockData);
            case "1211" -> GetBlockId1211.blockId(blockData);
            case "1213" -> GetBlockId1213.blockId(blockData);
            case "1214" -> GetBlockId1214.blockId(blockData);
            case "1215" -> GetBlockId1215.blockId(blockData);
            case "1216" -> GetBlockId1216.blockId(blockData);
            case "1217" -> GetBlockId1217.blockId(blockData);
            case "1218" -> GetBlockId1218.blockId(blockData);
            case "1219" -> GetBlockId1219.blockId(blockData);
            case "12110" -> GetBlockId12110.blockId(blockData);
            default -> 0;
        };
    }

}
