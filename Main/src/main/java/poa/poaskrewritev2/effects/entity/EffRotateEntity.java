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
import poa.packets.PosRotPacket;
import poa.packets.SendPacket;

public class EffRotateEntity extends Effect implements Listener {

    static {
        Skript.registerEffect(EffRotateEntity.class,
                "[send] rotate entity [packet] [with] id %number% with yaw %number% [and] pitch %number% for %players%");
    }

    private Expression<Number> id;
    private Expression<Number> yaw;
    private Expression<Number> pitch;
    private Expression<Player> players;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        id = (Expression<Number>) exprs[0];
        yaw = (Expression<Number>) exprs[1];
        pitch = (Expression<Number>) exprs[2];
        players = (Expression<Player>) exprs[3];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @SneakyThrows
    @Override
    protected void execute(Event event) {
        Number idNum = this.id.getSingle(event);
        Number yawNum = this.yaw.getSingle(event);
        Number pitchNum = this.pitch.getSingle(event);
        if (idNum == null || yawNum == null || pitchNum == null) return;

        int id = idNum.intValue();
        int yaw = yawNum.intValue();
        int pitch = pitchNum.intValue();

        for (Player player : this.players.getArray(event)) {
            SendPacket.sendPacket(player, PosRotPacket.rotPacket(id, yaw, pitch));
            SendPacket.sendPacket(player, HeadRotPacket.packet(id, yaw));
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String id = this.id.toString(event, debug);
        String yaw = this.yaw.toString(event, debug);
        String pitch = this.pitch.toString(event, debug);
        String players = this.players.toString(event, debug);
        return "rotate entity with id " + id + " with yaw " + yaw + " with pitch " + pitch + " for " + players;
    }

}
