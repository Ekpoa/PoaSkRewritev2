package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.LiteralUtils;
import ch.njol.util.Kleenean;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.packets.Metadata;
import poa.packets.SendPacket;

public class EffSendPacket extends Effect {

    static {
        Skript.registerEffect(EffSendPacket.class, "send packet %object% to %players%");
    }

    private Expression<Object> packet;
    private Expression<Player> players;

    // Thanks Fusezion for fixing the console errors
    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        packet = LiteralUtils.defendExpression(exprs[0]); // might need to cast or you can change 'object' to '?' they'll be the same
        players = (Expression<Player>) exprs[1];
        return LiteralUtils.canInitSafely(packet); // Once you defend it's been parsed and you can run this to ensure it's safe to continue
    }

    @SuppressWarnings("NullableProblems")
    @SneakyThrows
    @Override
    protected void execute(Event event) {
        Object packet = this.packet.getSingle(event);
        if (packet instanceof Metadata metadata)
            packet = metadata.build();


        for (Player player : this.players.getArray(event))
            SendPacket.sendPacket(player, packet);
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "send packet " + this.packet.toString(event, debug) + " to " + this.players.toString(event, debug);
    }

}
