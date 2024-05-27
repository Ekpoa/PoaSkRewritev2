package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.StartAutoSpinAttack;
import poa.packets.SendPayloadPacket;

public class EffAutoSpinAttack extends Effect {


    static {
        Skript.registerEffect(EffAutoSpinAttack.class, "make %players% spin [attack] for %timespan% ");

    }


    private Expression<Player> players;
    private Expression<Timespan> time;


    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        players = (Expression<Player>) exprs[0];
        time = (Expression<Timespan>) exprs[1];
        return true;
    }

    @Override
    protected void execute(Event event) {
        long ticks = time.getSingle(event).getTicks_i();

        int ticksInt = (ticks > Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int) ticks;


        for (Player player : players.getArray(event)) {
            StartAutoSpinAttack.startSpinAttack(player, ticksInt);
        }

    }


    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String time = this.time.toString(event, debug);
        String player = this.players.toString(event, debug);
        return "make " + player + " spin attack for " + time;
    }

}
