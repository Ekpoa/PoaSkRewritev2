package poa.poaskrewritev2.effects;

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
import poa.blocks.SetFast1214;


public class EffReplaceFast extends Effect {

    static {
        Skript.registerEffect(EffReplaceFast.class,
                "replace [blocks] %blockdata% (from|within) %location% and %location% to %blockdata% fast");
    }

    private Expression<BlockData> blockDataExpression1;
    private Expression<Location> locationExpression;
    private Expression<Location> locationExpression2;
    private Expression<BlockData> blockDataExpression2;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        blockDataExpression1 = (Expression<BlockData>) exprs[0];
        locationExpression = (Expression<Location>) exprs[1];
        locationExpression2 = (Expression<Location>) exprs[2];
        blockDataExpression2 = (Expression<BlockData>) exprs[3];
        return true;
    }


    @SneakyThrows
    @Override
    protected void execute(Event event) {
        final BlockData from = blockDataExpression1.getSingle(event);
        final BlockData to = blockDataExpression2.getSingle(event);

        final Location loc1 = locationExpression.getSingle(event);
        final Location loc2 = locationExpression2.getSingle(event);

        SetFast.replaceFast(loc1, loc2, from, to);
    }


    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "replace fast";
    }

}
