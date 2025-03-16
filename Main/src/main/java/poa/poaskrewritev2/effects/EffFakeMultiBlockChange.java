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
        Skript.registerEffect(EffFakeMultiBlockChange.class, "fake multi block set %locations% (as|to) %blockdata% for %players%");
    }

    private Expression<Location> locations;
    private Expression<BlockData> blockData;
    private Expression<Player> players;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        locations = (Expression<Location>) exprs[0];
        blockData = (Expression<BlockData>) exprs[1];
        players = (Expression<Player>) exprs[2];
        return true;
    }

    @Override
    protected void execute(Event event) {
        BlockData blockData = this.blockData.getSingle(event);
        if (blockData == null) return;

        Map<Location, BlockData> map = new HashMap<>();
        for (Location location : locations.getArray(event))
            map.put(location, blockData);
        
        if (map.isEmpty()) return;

        for (Player player : players.getArray(event))
            player.sendMultiBlockChange(map, false);
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String blocks = this.locations.toString(event, debug);
        String data = this.blockData.toString(event, debug);
        String players = this.players.toString(event, debug);
        return "fake multi block set " + blocks + " as " + data + " for " + players;
    }

}
