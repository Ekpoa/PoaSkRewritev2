package poa.poaskrewritev2.effects.entity;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import lombok.SneakyThrows;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.blocks.SetFast;


public class EffSetFast extends Effect {

    static {
        Skript.registerEffect(EffSetFast.class,
                "set %locations% to %blockdata% fast",
                "set [blocks from] %location% to %location% to %blockdata% [even] fast[er]");
    }

    private Expression<Location> locationExpression;
    private Expression<Location> locationExpression2;
    private Expression<BlockData> blockDataExpression;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        if (matchedPattern == 0)
            locationExpression = (Expression<Location>) exprs[0];
        else {
            locationExpression = (Expression<Location>) exprs[0];
            locationExpression2 = (Expression<Location>) exprs[1];
        }
        blockDataExpression = (Expression<BlockData>) exprs[exprs.length - 1];
        return true;
    }


    @SneakyThrows
    @Override
    protected void execute(Event event) {
        final BlockData blockData = blockDataExpression.getSingle(event);
        if (locationExpression2 == null) {
            final Location[] locations = locationExpression.getArray(event);
            SetFast.setFast(locations, blockData);
            return;
        }
        final Location loc1 = locationExpression.getSingle(event);
        final Location loc2 = locationExpression2.getSingle(event);

        SetFast.setFaster(loc1, loc2, blockData);
    }


    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "fast/faster set";
    }

}
