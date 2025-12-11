package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import poa.packets.packetListener.events.ChunkDataPacketEvent12110;
import poa.poaskrewritev2.PoaSkRewritev2;

import java.util.logging.Level;

public class EffFakeBlockPacketForChunk extends Effect {

    static {
        PoaSkRewritev2.getINSTANCE().getLogger().log(Level.WARNING, "asdadasdad");
        Skript.registerEffect(
                EffFakeBlockPacketForChunk.class,
                "fake block packet at %location% to %blockdata%"
        );
    }

    private Expression<Location> locationExpression;
    private Expression<BlockData> blockDataExpression;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs,
                        int matchedPattern,
                        Kleenean isDelayed,
                        SkriptParser.ParseResult parseResult) {

        locationExpression = (Expression<Location>) exprs[0];
        blockDataExpression = (Expression<BlockData>) exprs[1];
        return true;
    }

    @Override
    protected void execute(Event e) {
        if (!(e instanceof ChunkDataPacketEvent12110 event)) {
            Bukkit.broadcastMessage("as");
            return;
        }

        final Location location = locationExpression.getSingle(e);

        int
                x = location.getBlockX(),
                y = location.getBlockY(),
                z = location.getBlockZ();

        BlockData data = blockDataExpression.getSingle(e);

        int chunkX = event.getChunkX();
        int chunkZ = event.getChunkZ();

        int coordChunkX = x >> 4;
        int coordChunkZ = z >> 4;

        if (coordChunkX != chunkX || coordChunkZ != chunkZ) {
            PoaSkRewritev2.getINSTANCE().getLogger().log(Level.WARNING,
                    "fake block packet at ("
                            + x + ", " + y + ", " + z + ") is outside of chunk ("
                            + chunkX + ", " + chunkZ + ")"
            );
            return;
        }

        event.addFakeBlock(x, y, z, data);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "fake block packet";
    }
}
