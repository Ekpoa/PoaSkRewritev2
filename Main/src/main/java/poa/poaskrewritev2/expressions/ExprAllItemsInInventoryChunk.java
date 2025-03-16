package poa.poaskrewritev2.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.entity.Item;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ExprAllItemsInInventoryChunk extends SimpleExpression<ItemType> {

    private final Set<Block> visitedBlocks = new HashSet<>();

    static {
        Skript.registerExpression(ExprAllItemsInInventoryChunk.class, ItemType.class, ExpressionType.COMBINED,
                "all items in containers in chunk %chunk%");
    }

    private Expression<Chunk> chunkExpression;


    @SuppressWarnings({"unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        chunkExpression = (Expression<Chunk>) exprs[0];
        return true;
    }

    @Override
    protected @Nullable ItemType[] get(Event event) {
        final Chunk chunk = chunkExpression.getSingle(event);
        if (chunk == null) return null;

        List<ItemType> items = new ArrayList<>();

        for (BlockState tileEntity : chunk.getTileEntities()) {
            if (tileEntity instanceof Container container) {
                ItemStack[] contents = container.getInventory().getStorageContents();

                for (ItemStack item : contents) {
                    if (item != null && !item.isEmpty()) {
                        items.add(new ItemType(item));
                    }
                }
            }
        }

        return items.toArray(new ItemType[0]);

    }



    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends ItemType> getReturnType() {
        return ItemType.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "items in containers in chunk";
    }

}
