package poa.poaskrewritev2.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.guardian.GuardianBeam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExprGetAllGuardianBeamIDs extends SimpleExpression<String> {


    static {
        Skript.registerExpression(ExprGetAllGuardianBeamIDs.class, String.class, ExpressionType.COMBINED,
                "all guardian beams [ids]");
    }

    private Expression<Chunk> chunkExpression;


    @SuppressWarnings({"unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        chunkExpression = (Expression<Chunk>) exprs[0];
        return true;
    }

    @Override
    protected @Nullable String[] get(Event event) {
        return GuardianBeam.dataMap.keySet().toArray(new String[0]);
    }



    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "get all guardian beam ids";
    }

}
