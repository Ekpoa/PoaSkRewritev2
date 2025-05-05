package poa.poaskrewritev2.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Base64;

public class ExprEncodeItem extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprEncodeItem.class, String.class, ExpressionType.SIMPLE,
                "encode %itemtype% [to (string|base 64)]");
    }

    private Expression<ch.njol.skript.aliases.ItemType> itemTypeExpression;

    @SuppressWarnings({"unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        itemTypeExpression = (Expression<ch.njol.skript.aliases.ItemType>) exprs[0];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected @Nullable String[] get(Event event) {
        final ItemStack itemStack = itemTypeExpression.getSingle(event).getRandom();
        final String s = Base64.getEncoder().encodeToString(itemStack.serializeAsBytes());
        return new String[]{s};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "encode item stack";
    }

}
