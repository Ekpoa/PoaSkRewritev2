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
import poa.packets.PosRotPacket;
import poa.packets.SendPacket;

public class EffMoveEntity extends Effect {

    static {
        Skript.registerEffect(EffMoveEntity.class,
                "[send] move entity [packet] [with] id %number% by %number%[,] %number%[,] %number% for %players%");
    }

    private Expression<Number> id;
    private Expression<Number> x;
    private Expression<Number> y;
    private Expression<Number> z;
    private Expression<Player> players;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        id = (Expression<Number>) exprs[0];
        x = (Expression<Number>) exprs[1];
        y = (Expression<Number>) exprs[2];
        z = (Expression<Number>) exprs[3];
        players = (Expression<Player>) exprs[4];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @SneakyThrows
    @Override
    protected void execute(Event event) {
        Number idNum = this.id.getSingle(event);
        Number xNum = this.x.getSingle(event);
        Number yNum = this.y.getSingle(event);
        Number zNum = this.z.getSingle(event);
        if (idNum == null || xNum == null || yNum == null || zNum == null) return;
        int id = idNum.intValue();
        short x = xNum.shortValue();
        short y = yNum.shortValue();
        short z = zNum.shortValue();

        Object packet = PosRotPacket.posPacket(id, x, y, z);

        for (Player player : this.players.getArray(event)) {
            SendPacket.sendPacket(player, packet);
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String id = this.id.toString(event, debug);
        String x = this.x.toString(event, debug);
        String y = this.y.toString(event, debug);
        String z = this.z.toString(event, debug);
        String players = this.players.toString(event, debug);
        return String.format("move entity with id %s by %s,%s,%s for %s", id, x, y, z, players);
    }

}
