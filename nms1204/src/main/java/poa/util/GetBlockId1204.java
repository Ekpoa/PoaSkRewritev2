package poa.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R3.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R3.block.data.CraftBlockData;

public class GetBlockId1204 {

    public static int blockId(BlockData blockData){
        BlockState state = ((CraftBlockData) blockData).getState();

        return Block.getId((state));
    }

}
