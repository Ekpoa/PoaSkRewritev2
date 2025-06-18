package poa.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.block.data.CraftBlockData;

public class GetBlockId1215 {

    public static int blockId(BlockData blockData){
        BlockState state = ((CraftBlockData) blockData).getState();

        return Block.getId((state));
    }

}
