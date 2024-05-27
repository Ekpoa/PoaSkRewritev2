package poa.poaskrewritev2.effects.entity;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.packets.FakeEntity;
import poa.packets.Metadata;
import poa.packets.SendPacket;

public class EffSpawnFakeDroppedItem extends Effect {

    static {
        Skript.registerEffect(EffSpawnFakeDroppedItem.class,
                "spawn fake [dropped] item at %location% for %players% with id %number% with [item] %itemtype%");
    }

    private Expression<Location> location;
    private Expression<Player> players;
    private Expression<Number> id;
    private Expression<ItemType> itemType;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        location = (Expression<Location>) exprs[0];
        players = (Expression<Player>) exprs[1];
        id = (Expression<Number>) exprs[2];
        itemType = (Expression<ItemType>) exprs[3];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        Location location = this.location.getSingle(event);
        Number idNum = this.id.getSingle(event);
        ItemType itemType = this.itemType.getSingle(event);
        if (location == null || idNum == null || itemType == null) return;

        int id = idNum.intValue();
        ItemStack itemStack = itemType.getRandom();

        Object packet = FakeEntity.fakeEntityPacket(id, location, EntityType.DROPPED_ITEM.toString());
        Metadata entityMetadata = new Metadata(id);
        entityMetadata.setItem(itemStack);
        Object metadataPacket = entityMetadata.build();

        for (Player player : this.players.getArray(event)) {
            SendPacket.sendPacket(player, packet);
            SendPacket.sendPacket(player, metadataPacket);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String location = this.location.toString(event, debug);
        String players = this.players.toString(event, debug);
        String id = this.id.toString(event, debug);
        String itemType = this.itemType.toString(event, debug);
        return "spawn fake item at " + location + " for " + players + " with id " + id + " with " + itemType;
    }

}
