package poa.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static org.bukkit.Bukkit.getWorld;


public class SetFast1218 implements EventListener {



    public static void setFast(Location[] locations, BlockData blockData) {
        if (locations.length == 0)
            throw new RuntimeException("Locations null");

        Map<LevelChunk, List<BlockPos>> chunkMap = new HashMap<>();
        BlockState state = ((CraftBlockData) blockData).getState();
        World world = locations[0].getWorld();

        for (Location loc : locations) {
            LevelChunk chunk = getLevelChunk(loc.getChunk());
            chunkMap.computeIfAbsent(chunk, k -> new ArrayList<>())
                    .add(new BlockPos(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
        }

        for (Map.Entry<LevelChunk, List<BlockPos>> entry : chunkMap.entrySet()) {
            LevelChunk chunk = entry.getKey();
            List<BlockPos> blockPositions = entry.getValue();

            for (BlockPos pos : blockPositions) {
                chunk.setBlockState(pos, state, 16);
            }

            world.refreshChunk(chunk.locX, chunk.locZ);
        }
    }



    public static void setFaster(Location pos1, Location pos2, BlockData blockData){
        Map<LevelChunk, List<BlockPos>> chunkMap = getChunkBlockPositions(pos1, pos2);
        BlockState state = ((CraftBlockData) blockData).getState();
        World world = pos1.getWorld();

        for (Map.Entry<LevelChunk, List<BlockPos>> entry : chunkMap.entrySet()) {
            LevelChunk chunk = entry.getKey();
            List<BlockPos> blockPositions = entry.getValue();

            for (BlockPos pos : blockPositions)
                chunk.setBlockState(pos, state, 16);


            world.refreshChunk(chunk.locX, chunk.locZ);
        }


    }


    public static void replaceFast(Location pos1, Location pos2, BlockData replace, BlockData replaceTo){
        Map<LevelChunk, List<BlockPos>> chunkMap = getChunkBlockPositions(pos1, pos2);
        BlockState state1 = ((CraftBlockData) replace).getState();
        BlockState stateTo= ((CraftBlockData) replaceTo).getState();
        World world = pos1.getWorld();

        for (Map.Entry<LevelChunk, List<BlockPos>> entry : chunkMap.entrySet()) {
            LevelChunk chunk = entry.getKey();
            List<BlockPos> blockPositions = entry.getValue();

            for (BlockPos pos : blockPositions) {
                final BlockState blockState = chunk.getBlockState(pos);
                if(blockState == state1)
                    chunk.setBlockState(pos, stateTo, 16);
            }


            world.refreshChunk(chunk.locX, chunk.locZ);
        }


    }









    public static Map<LevelChunk, List<BlockPos>> getChunkBlockPositions(Location loc1, Location loc2) {
        Map<LevelChunk, List<BlockPos>> chunkMap = new HashMap<>();

        int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
        int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
        int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());

        int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
        int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
        int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());

        World world = loc1.getWorld();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    pos.set(x, y, z);

                    // Get the chunk once per block
                    LevelChunk chunk = getLevelChunk(world.getChunkAt(x >> 4, z >> 4));

                    // Store the block position in the corresponding chunk list
                    chunkMap.computeIfAbsent(chunk, k -> new ArrayList<>()).add(pos.immutable());
                }
            }
        }

        return chunkMap;
    }



    public static List<LevelChunk> getChunksBetween(Location loc1, Location loc2) {
        List<LevelChunk> chunks = new ArrayList<>();
        Set<LevelChunk> chunkSet = new HashSet<>();

        int minX = Math.min(loc1.getBlockX(), loc2.getBlockX()) >> 4;
        int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX()) >> 4;
        int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ()) >> 4;
        int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ()) >> 4;

        World world = loc1.getWorld();

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                LevelChunk chunk = getLevelChunk(world.getChunkAt(x, z));
                if (chunkSet.add(chunk)) {
                    chunks.add(chunk);
                }
            }
        }

        return chunks;
    }




    //Stolen from shane
    public static LevelChunk getLevelChunk(@NotNull Chunk chunk) {
        ServerLevel serverLevel = ((CraftWorld) chunk.getWorld()).getHandle();
        return serverLevel.getChunk(chunk.getX(), chunk.getZ());
    }
}
