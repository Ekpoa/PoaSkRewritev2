package poa.poaskrewritev2.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.FakePlayer;
import poa.poaskrewritev2.PoaSkRewritev2;

import java.util.UUID;
import java.util.logging.Level;

public class ExprEntityIDFromFakePlayer extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprEntityIDFromFakePlayer.class, Integer.class, ExpressionType.COMBINED,
                "entity id of fake player named %string%");
    }

    private Expression<String> name;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected @Nullable Integer[] get(Event event) {
        String name = this.name.getSingle(event);

        if(!FakePlayer.getNameToUuidMap().containsKey(name)){
            PoaSkRewritev2.getINSTANCE().getLogger().log(Level.WARNING, "Could not find player in the map");
            return null;
        }

        UUID uuid = FakePlayer.getNameToUuidMap().get(name);
        if(!FakePlayer.getUuidToIdMap().containsKey(uuid)){
            PoaSkRewritev2.getINSTANCE().getLogger().log(Level.WARNING, "Could not find fake player uuid in the map");
            return null;
        }

        return new Integer[]{FakePlayer.getUuidToIdMap().get(uuid)};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {

        return "entity id of fake player";
    }

}
