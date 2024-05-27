package poa.poaskrewritev2.effects.entity;

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
import poa.packets.HeadRotPacket;
import poa.packets.SendPacket;

public class EffFakeHeadRotation extends Effect implements Listener {

    static {
        Skript.registerEffect(EffFakeHeadRotation.class,
                "[send] head rotation [packet] [with] id %number% with yaw %number% for %players%");
    }

    private Expression<Number> id;
    private Expression<Number> yaw;
    private Expression<Player> players;

    @SuppressWarnings({"NullableProblems", "unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        id = (Expression<Number>) exprs[0];
        yaw = (Expression<Number>) exprs[1];
        players = (Expression<Player>) exprs[2];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @SneakyThrows
    @Override
    protected void execute(Event event) {
        Number idNum = this.id.getSingle(event);
        Number yawNum = this.yaw.getSingle(event);
        if (idNum == null || yawNum == null) return;

        int id = idNum.intValue();
        int yaw = yawNum.intValue();
        for (Player player : this.players.getArray(event))
            SendPacket.sendPacket(player, HeadRotPacket.packet(id, yaw));
    }


    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String id = this.id.toString(event, debug);
        String yaw = this.yaw.toString(event, debug);
        String players = this.players.toString(event, debug);
        return "head rotation with id " + id + " with yaw " + yaw + " for " + players;
    }

}
