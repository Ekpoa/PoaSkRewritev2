package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.SendPacket;
import poa.SetPassengerPacket;

import java.util.Arrays;

public class EffSetPassenger extends Effect implements Listener {

    static {
        Skript.registerEffect(EffSetPassenger.class, "make entit(ies|y) with id[s] %numbers% ride entity with id %number% for %players%");
    }

    private Expression<Number> passengerID;
    private Expression<Number> vehicleID;
    private Expression<Player> player;

    @SuppressWarnings({"NullableProblems", "unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        passengerID = (Expression<Number>) exprs[0];
        vehicleID = (Expression<Number>) exprs[1];
        player = (Expression<Player>) exprs[2];
        return true;
    }

    @SneakyThrows
    @Override

    protected void execute(Event event) {
        Number vehicleNum = this.vehicleID.getSingle(event);
        if (vehicleNum == null) return;

        int[] passengers = Arrays.stream( this.passengerID.getArray(event)).mapToInt(Number::intValue).toArray();
        int vehicle = vehicleNum.intValue();

        for (Player player : player.getArray(event)) {
            SendPacket.sendPacket(player, SetPassengerPacket.packet(vehicle, passengers));
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String passenger = this.passengerID.toString(event, debug);
        String vehicle = this.vehicleID.toString(event, debug);
        String player = this.player.toString(event, debug);
        return "make entities with id " + passenger + " ride entity with id " + vehicle + " for " + player;
    }

}
