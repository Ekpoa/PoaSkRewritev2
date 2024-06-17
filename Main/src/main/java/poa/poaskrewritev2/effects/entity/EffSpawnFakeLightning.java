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

import java.util.concurrent.ThreadLocalRandom;

public class EffSpawnFakeLightning extends Effect {

    static {
        Skript.registerEffect(EffSpawnFakeLightning.class,
                "spawn fake lightning at %location% for %players%");
    }

    private Expression<Location> location;
    private Expression<Player> players;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        location = (Expression<Location>) exprs[0];
        players = (Expression<Player>) exprs[1];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        Location location = this.location.getSingle(event);

        int id = ThreadLocalRandom.current().nextInt(99999,9999999);

        Object packet = FakeEntity.fakeEntityPacket(id, location, "LIGHTNING_BOLT");

        for (Player player : this.players.getArray(event))
            SendPacket.sendPacket(player, packet);
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String location = this.location.toString(event, debug);
        String players = this.players.toString(event, debug);
        return "spawn fake lighting at " + location + " for " + players;
    }

}
