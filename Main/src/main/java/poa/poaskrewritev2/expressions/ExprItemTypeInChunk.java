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
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExprItemTypeInChunk extends SimpleExpression<Location> {

    static {
        Skript.registerExpression(ExprItemTypeInChunk.class, Location.class, ExpressionType.COMBINED,
                "locations of %itemtypes% in %chunk%");
    }

    private Expression<ItemType> itemTypes;
    private Expression<Chunk> chunk;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        itemTypes = (Expression<ItemType>) exprs[0];
        chunk = (Expression<Chunk>) exprs[1];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected @Nullable Location[] get(Event event) {

        List<Material> materials = Arrays.stream(this.itemTypes.getArray(event)).map(ItemType::getMaterial).toList();
        Chunk chunk = this.chunk.getSingle(event);
        if (chunk == null) return null;

        List<Location> locList = new ArrayList<>();

        int x = chunk.getX() << 4;
        int z = chunk.getZ() << 4;

        World world = chunk.getWorld();

        int minHeight = world.getMinHeight();
        int maxHeight = world.getMaxHeight();

        for (int xx = x; xx < (x + 16); xx++)
            for (int zz = z; zz < (z + 16); zz++)
                for (int yy = minHeight; yy < maxHeight; yy++) {
                    Block block = world.getBlockAt(xx, yy, zz);
                    Material material = block.getType();
                    if (materials.contains(material))
                        locList.add(block.getLocation());
                }

        return locList.toArray(new Location[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String itemType = this.itemTypes.toString(event, debug);
        String chunk = this.chunk.toString(event, debug);
        return "location of " + itemType + " in " + chunk;
    }

}
