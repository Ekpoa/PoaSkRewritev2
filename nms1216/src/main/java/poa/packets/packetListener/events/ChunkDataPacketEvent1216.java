package poa.packets.packetListener.events;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChunkDataPacketEvent1216 extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    @Getter
    private final Player player;
    @Getter
    private final World world;

    @Getter
    private final int chunkX;
    @Getter
    private final int chunkZ;

    private boolean cancelled;


    private final List<FakeBlock> fakeBlocks = new ArrayList<>();


    public record FakeBlock(int x, int y, int z, BlockData blockData) {
    }

    public ChunkDataPacketEvent1216(Player player, boolean async, int chunkX, int chunkZ) {
        super(async);
        this.player = player;
        this.world = player.getWorld();
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }


    public void addFakeBlock(int x, int y, int z, BlockData data) {
        fakeBlocks.add(new FakeBlock(x, y, z, data));
    }

    public void addFakeBlock(Location location, BlockData data) {
        fakeBlocks.add(new FakeBlock(
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ(),
                data
        ));
    }


    public void addFakeBlock(Block block) {
        fakeBlocks.add(new FakeBlock(
                block.getX(),
                block.getY(),
                block.getZ(),
                block.getBlockData()
        ));
    }


    public List<FakeBlock> getFakeBlocks() {
        return Collections.unmodifiableList(fakeBlocks);
    }


    public List<FakeBlock> getMutableFakeBlocks() {
        return fakeBlocks;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
