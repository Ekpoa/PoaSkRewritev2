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

public class ExprDecodeItem extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprDecodeItem.class, ItemStack.class, ExpressionType.SIMPLE,
                "decode %string% [to item]");
    }

    private Expression<String> stringExpression;

    @SuppressWarnings({"unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        stringExpression = (Expression<String>) exprs[0];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected @Nullable ItemStack[] get(Event event) {
        return new ItemStack[]{ItemStack.deserializeBytes(Base64.getDecoder().decode(stringExpression.getSingle(event)))};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "decode item stack";
    }

}
