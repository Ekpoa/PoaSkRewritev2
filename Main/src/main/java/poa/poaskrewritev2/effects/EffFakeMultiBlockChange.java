package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class EffFakeMultiBlockChange extends Effect {

    static {
        Skript.registerEffect(EffFakeMultiBlockChange.class, "fake multi block set %blocks% as %blockdata% for %players%");
    }

    private Expression<Block> blocks;
    private Expression<BlockData> blockData;
    private Expression<Player> players;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        blocks = (Expression<Block>) exprs[0];
        blockData = (Expression<BlockData>) exprs[1];
        players = (Expression<Player>) exprs[2];
        return true;
    }

    @Override
    protected void execute(Event e) {
        BlockData blockData = this.blockData.getSingle(e);
        if (blockData == null) return;

        Map<Location, BlockData> map = new HashMap<>();
        for (Block block : blocks.getArray(e))
            map.put(block.getLocation(), blockData);

        for (Player player : players.getArray(e))
            player.sendMultiBlockChange(map, false);
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String blocks = this.blocks.toString(event, debug);
        String data = this.blockData.toString(event, debug);
        String players = this.players.toString(event, debug);
        return "fake multi block set " + blocks + " as " + data + " for " + players;
    }

}
