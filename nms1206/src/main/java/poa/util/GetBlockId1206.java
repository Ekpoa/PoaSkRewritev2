package poa.util;

import net.minecraft.world.level.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.block.CraftBlockState;

public class GetBlockId1206 {

    public static int blockId(BlockData blockData){
        return Block.getId(((CraftBlockState) blockData).getHandle());
    }

}
