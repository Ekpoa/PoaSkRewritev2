package poa.poaskrewritev2.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.conditions.CondIsWithin;
import ch.njol.skript.hooks.regions.WorldGuardHook;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.BlockUtils;
import ch.njol.util.Kleenean;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.poaskrewritev2.util.WorldGuardStuff;

public class ExprRegion extends SimpleExpression<Object> {

    static {
        Skript.registerExpression(ExprRegion.class, Object.class, ExpressionType.SIMPLE,
                "world guard region %string% in [world] %world%");
    }

    private Expression<String> string;
    private Expression<World> world;

    @SuppressWarnings({"unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        string = (Expression<String>) exprs[0];
        world = (Expression<World>) exprs[1];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected @Nullable Object[] get(Event event) {
        return new ProtectedRegion[]{WorldGuardStuff.getRegion(world.getSingle(event), string.getSingle(event))};
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
        return "get world guard region";
    }

}
