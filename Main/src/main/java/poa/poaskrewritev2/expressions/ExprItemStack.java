package poa.poaskrewritev2.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprItemStack extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprItemStack.class, ItemStack.class, ExpressionType.SIMPLE,
                "[convert|change] %itemtype% to [bukkit] item[ ]stack",
                "[get] item[ ]stack (from|of) %itemtype%");
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
    protected @Nullable ItemStack[] get(Event event) {
        return new ItemStack[]{itemTypeExpression.getSingle(event).getRandom()};
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
        return "get item stack";
    }

}
