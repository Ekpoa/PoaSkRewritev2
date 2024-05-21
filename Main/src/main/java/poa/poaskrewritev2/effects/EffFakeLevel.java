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

public class EffFakeLevel extends Effect {

    static {
        Skript.registerEffect(EffFakeLevel.class, "fake %players%'[s] level to %number%");
    }

    private Expression<Player> player;
    private Expression<Number> level;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        player = (Expression<Player>) exprs[0];
        level = (Expression<Number>) exprs[1];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        Number levelNum = this.level.getSingle(event);
        if (levelNum == null) return;
        int level = levelNum.intValue();

        for (Player player : this.player.getArray(event))
            player.sendExperienceChange(0F, level);
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String player = this.player.toString(event, debug);
        String level = this.level.toString(event, debug);
        return "fake " + player + "'s level to " + level;
    }

}
