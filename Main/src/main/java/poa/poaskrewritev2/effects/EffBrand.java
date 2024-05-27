package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.packets.SendPayloadPacket;

public class EffBrand extends Effect {


    static {
        Skript.registerEffect(EffBrand.class, "set server brand to %string% for %players%");

    }

    private Expression<String> string;
    private Expression<Player> players;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        string = (Expression<String>) exprs[0];
        players = (Expression<Player>) exprs[1];
        return true;
    }

    @Override
    protected void execute(Event event) {
        String s = string.getSingle(event);
        for (Player player : players.getArray(event)) {
            SendPayloadPacket.sendBrandPayload(player, s);
        }

    }


    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String string = this.string.toString(event, debug);
        String player = this.players.toString(event, debug);
        return "set server brand to " + string + " for " + player;
    }

}
