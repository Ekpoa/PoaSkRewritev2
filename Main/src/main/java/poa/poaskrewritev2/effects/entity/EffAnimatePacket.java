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
import poa.packets.AnimatePacket;
import poa.packets.SendPacket;
import poa.packets.TeleportPacket;

public class EffAnimatePacket extends Effect implements Listener {

    static {
        Skript.registerEffect(EffAnimatePacket.class,
                "[send] animation [packet] [with] id %number% for entity [with id] %number% for %players%");
    }

    private Expression<Number> id;
    private Expression<Number> animation;
    private Expression<Player> players;

    @SuppressWarnings({"NullableProblems", "unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        id = (Expression<Number>) exprs[0];
        animation = (Expression<Number>) exprs[1];
        players = (Expression<Player>) exprs[2];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @SneakyThrows
    @Override
    protected void execute(Event event) {
        final int entityId = id.getSingle(event).intValue();
        final int animationId = animation.getSingle(event).intValue();
        for (Player player : this.players.getArray(event)) {
            SendPacket.sendPacket(player, AnimatePacket.packet(entityId, animationId));
        }
    }


    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "animation packet";
    }

}
