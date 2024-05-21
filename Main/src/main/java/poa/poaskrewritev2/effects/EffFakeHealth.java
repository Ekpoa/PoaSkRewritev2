package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EffFakeHealth extends Effect {

    static {
        Skript.registerEffect(EffFakeHealth.class, "fake %players%'[s] health to %number%");
    }

    private Expression<Player> player;
    private Expression<Number> number;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        player = (Expression<Player>) exprs[0];
        number = (Expression<Number>) exprs[1];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        Number healthNum = this.number.getSingle(event);
        if (healthNum == null) return;
        double health = healthNum.doubleValue();

        for (Player player : this.player.getArray(event)) {
            player.sendHealthUpdate(health, player.getFoodLevel(), player.getSaturation());
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String player = this.player.toString(event, debug);
        String number = this.number.toString(event, debug);
        return "fake " + player + "'s health to " + number;
    }

}
