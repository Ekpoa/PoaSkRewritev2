package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.blocks.Biomes;
import poa.poaskrewritev2.PoaSkRewritev2;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EffFakeBiome extends Effect {

    static {
        Skript.registerEffect(EffFakeBiome.class, "[send] fake biome [named] %string% between %location% and %location% for %players%");
    }

    private Expression<Player> player;
    private Expression<String> biomeExpression;
    private Expression<Location> loc1;
    private Expression<Location> loc2;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        biomeExpression = (Expression<String>) exprs[0];
        loc1 = (Expression<Location>) exprs[1];
        loc2 = (Expression<Location>) exprs[2];
        player = (Expression<Player>) exprs[3];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        final Set<Player> players = Arrays.stream(player.getArray(event)).collect(Collectors.toSet());
        final String biome = biomeExpression.getSingle(event);
        final Location l1 = loc1.getSingle(event);
        final Location l2 = loc2.getSingle(event);

        Biomes.sendBiomePacketAsync(PoaSkRewritev2.getINSTANCE(), players, l1, l2, biome.toLowerCase());
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "fake biome";
    }

}
