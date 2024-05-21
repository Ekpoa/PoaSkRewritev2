package poa.poaskrewritev2.effects.entity;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.FakeEntity;
import poa.GetBlockId;
import poa.SendPacket;

public class EffSpawnFakeFallingBlock extends Effect {

    static {
        Skript.registerEffect(EffSpawnFakeFallingBlock.class,
                "spawn fake falling block at %location% for %players% with id %number% with [block] %blockdata%");
    }

    private Expression<Location> location;
    private Expression<Player> players;
    private Expression<Number> id;
    private Expression<BlockData> blockData;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        location = (Expression<Location>) exprs[0];
        players = (Expression<Player>) exprs[1];
        id = (Expression<Number>) exprs[2];
        blockData = (Expression<BlockData>) exprs[3];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        Location location = this.location.getSingle(event);
        Number idNum = this.id.getSingle(event);
        BlockData bd = this.blockData.getSingle(event);
        if (location == null || idNum == null || bd == null) return;

        int id = idNum.intValue();


        Object packet = FakeEntity.fakeEntityPacket(id, location, EntityType.FALLING_BLOCK.toString(), GetBlockId.blockId(bd));

        for (Player player : this.players.getArray(event))
            SendPacket.sendPacket(player, packet);
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String location = this.location.toString(event, debug);
        String players = this.players.toString(event, debug);
        String id = this.id.toString(event, debug);
        return "spawn fake item at " + location + " for " + players + " with id " + id + " with " + blockData;
    }

}
