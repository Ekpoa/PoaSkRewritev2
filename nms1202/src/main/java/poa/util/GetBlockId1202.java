package poa.util;

import net.minecraft.world.level.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R2.block.CraftBlockState;

public class GetBlockId1202 {

    public static int blockId(BlockData blockData){
        return Block.getId(((CraftBlockState) blockData).getHandle());
    }

}
