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
import poa.packets.CameraPacket;
import poa.packets.SendPacket;

public class EffSetCamera extends Effect {

    static {
        Skript.registerEffect(EffSetCamera.class, "[set] camera [packet] of %players% to entity [with] id %number%");
    }

    private Expression<Number> id;
    private Expression<Player> players;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        id = (Expression<Number>) exprs[1];
        players = (Expression<Player>) exprs[0];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @SneakyThrows
    @Override
    protected void execute(Event event) {
        Number idNum = this.id.getSingle(event);
        if (idNum == null) return;
        int id = idNum.intValue();

        for (Player player : this.players.getArray(event))
            SendPacket.sendPacket(player, CameraPacket.cameraPacket(id));
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String players = this.players.toString(event, debug);
        String id = this.id.toString(event, debug);
        return "set camera of " + players + " to entity with id " + id;
    }

}
