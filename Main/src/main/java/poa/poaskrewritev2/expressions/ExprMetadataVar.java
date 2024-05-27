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
import poa.packets.Metadata;

public class ExprMetadataVar extends SimpleExpression<Object> {

    static {
        Skript.registerExpression(ExprMetadataVar.class, Object.class, ExpressionType.SIMPLE,
                "[raw] metadata packet with id %number%");
    }

    private Expression<Number> id;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        id = (Expression<Number>) exprs[0];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected @Nullable Object[] get(Event event) {
        Number idNum = this.id.getSingle(event);
        if (idNum == null) return null;
        return new Object[]{new Metadata(idNum.intValue())};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<?> getReturnType() {
        return Object.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "raw metadata packet with id " + this.id.toString(event, debug);
    }

}
