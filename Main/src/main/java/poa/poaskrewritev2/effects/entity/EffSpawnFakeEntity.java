package poa.poaskrewritev2.effects.entity;

import ch.njol.skript.Skript;
import ch.njol.skript.bukkitutil.EntityUtils;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.packets.FakeEntity;
import poa.packets.SendPacket;

public class EffSpawnFakeEntity extends Effect {

    static {
        Skript.registerEffect(EffSpawnFakeEntity.class,
                "spawn fake %entitytype% at %location% for %players% with id %number%");
    }

    private Expression<ch.njol.skript.entity.EntityType> entityType;
    private Expression<Location> location;
    private Expression<Player> players;
    private Expression<Number> id;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        entityType = (Expression<ch.njol.skript.entity.EntityType>) exprs[0];
        location = (Expression<Location>) exprs[1];
        players = (Expression<Player>) exprs[2];
        id = (Expression<Number>) exprs[3];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        Location location = this.location.getSingle(event);
        ch.njol.skript.entity.EntityType skriptEntityType = this.entityType.getSingle(event);
        Number idNum = this.id.getSingle(event);
        if (location == null || skriptEntityType == null || idNum == null) return;

        EntityType entityType = EntityUtils.toBukkitEntityType(skriptEntityType.data);
        int id = idNum.intValue();

        Object packet = FakeEntity.fakeEntityPacket(id, location, entityType.toString());

        for (Player player : this.players.getArray(event))
            SendPacket.sendPacket(player, packet);
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String entityType = this.entityType.toString(event, debug);
        String location = this.location.toString(event, debug);
        String players = this.players.toString(event, debug);
        String id = this.id.toString(event, debug);
        return "spawn fake " + entityType + " at " + location + " for " + players + " with id " + id;
    }

}
