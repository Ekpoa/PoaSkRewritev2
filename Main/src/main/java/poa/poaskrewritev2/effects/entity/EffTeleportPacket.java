package poa.poaskrewritev2.effects.entity;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import lombok.SneakyThrows;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.packets.HeadRotPacket;
import poa.packets.SendPacket;
import poa.packets.TeleportPacket;

public class EffTeleportPacket extends Effect implements Listener {

    static {
        Skript.registerEffect(EffTeleportPacket.class,
                "[send] teleport [packet] [with] id %number% with [location] %location% to %players%");
    }

    private Expression<Number> id;
    private Expression<Location> location;
    private Expression<Player> players;

    @SuppressWarnings({"NullableProblems", "unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        id = (Expression<Number>) exprs[0];
        location = (Expression<Location>) exprs[1];
        players = (Expression<Player>) exprs[2];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @SneakyThrows
    @Override
    protected void execute(Event event) {
        Number idNum = this.id.getSingle(event);
        Location loc = this.location.getSingle(event);
        if (idNum == null || loc == null) return;


        for (Player player : this.players.getArray(event))
            SendPacket.sendPacket(player, TeleportPacket.packet(idNum.intValue(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch(), true));
    }


    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String id = this.id.toString(event, debug);
        String location = this.location.toString(event, debug);
        String players = this.players.toString(event, debug);
        return "teleport packet with id " + id + " with location " + location + " for " + players;
    }

}
