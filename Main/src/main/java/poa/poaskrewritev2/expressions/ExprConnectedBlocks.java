package poa.poaskrewritev2.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ExprConnectedBlocks extends SimpleExpression<Block> {

    private final Set<Block> visitedBlocks = new HashSet<>();

    static {
        Skript.registerExpression(ExprConnectedBlocks.class, Block.class, ExpressionType.COMBINED,
                "[next] %number% connected blocks from %location%");
    }

    private Expression<Number> number;
    private Expression<Location> location;


    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        number = (Expression<Number>) exprs[0];
        location = (Expression<Location>) exprs[1];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected @Nullable Block[] get(Event event) {

        return findStructure(location.getSingle(event), number.getSingle(event).intValue()).toArray(new Block[0]);
    }

    public Set<Block> findStructure(Location start, int limit) {
        visitedBlocks.clear();
        Queue<Block> queue = new LinkedList<>();
        queue.add(start.getBlock());

        while (!queue.isEmpty() && visitedBlocks.size() < limit) {
            Block current = queue.poll();
            if (!visitedBlocks.contains(current) && !current.getType().isAir()) {
                // Mark the current block as visited
                visitedBlocks.add(current);

                // Add all adjacent blocks to the queue for exploration
                addIfValid(queue, current.getRelative(0, 1, 0)); // Up
                addIfValid(queue, current.getRelative(0, -1, 0)); // Down
                addIfValid(queue, current.getRelative(1, 0, 0)); // East
                addIfValid(queue, current.getRelative(-1, 0, 0)); // West
                addIfValid(queue, current.getRelative(0, 0, 1)); // South
                addIfValid(queue, current.getRelative(0, 0, -1)); // North
            }
        }
        return visitedBlocks;
    }

    private void addIfValid(Queue<Block> queue, Block block) {
        if (!block.getType().isAir() && !visitedBlocks.contains(block)) {
            queue.add(block);
        }
    }




    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends Block> getReturnType() {
        return Block.class;
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "connected blocks";
    }

}
