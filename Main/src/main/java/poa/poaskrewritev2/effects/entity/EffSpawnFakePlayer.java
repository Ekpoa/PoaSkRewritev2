package poa.poaskrewritev2.effects.entity;

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
import poa.FakePlayer;

import java.util.Arrays;

public class EffSpawnFakePlayer extends Effect {

    static {
        Skript.registerEffect(EffSpawnFakePlayer.class,
                "spawn fake player named %string% [with skin named %string%] [on tablist %-boolean%] [with latency %-number%] at %location% for %players%");
    }

    private Expression<String> name;
    private Expression<String> skinName;
    private Expression<Boolean> listed;
    private Expression<Number> latency;
    private Expression<Location> location;
    private Expression<Player> players;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        skinName = (Expression<String>) exprs[1];
        listed = (Expression<Boolean>) exprs[2];
        latency = (Expression<Number>) exprs[3];
        location = (Expression<Location>) exprs[4];
        players = (Expression<Player>) exprs[5];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        String name = this.name.getSingle(event);
        boolean listed = false;

        if (this.listed != null)
            listed = this.listed.getSingle(event).booleanValue();

        int latency = 0;
        if (this.latency != null)
            latency = this.latency.getSingle(event).intValue();

        Location location = this.location.getSingle(event);

        FakePlayer.spawnFakePlayer(Arrays.stream(players.getArray(event)).toList(), name, skinName.getSingle(event), location, listed, latency);
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "spawn fake player";
    }

}
