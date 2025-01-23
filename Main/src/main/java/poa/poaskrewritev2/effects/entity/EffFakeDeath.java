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
import poa.packets.*;

public class EffFakeDeath extends Effect{

    static {
        Skript.registerEffect(EffFakeDeath.class,
                "fake death [packet] of entity with id %number% for %players%");
    }

    private Expression<Number> id;
    private Expression<Player> players;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        id = (Expression<Number>) exprs[0];
        players = (Expression<Player>) exprs[1];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @SneakyThrows
    @Override
    protected void execute(Event event) {
        Number idNum = this.id.getSingle(event);
        if (idNum == null) return;

        int id = idNum.intValue();

        Metadata metadata = new Metadata(id);
        metadata.setHealth(0);
        Object build = metadata.build();

        Object packet = FakeDeathAnimation.fakeEntityPacket(id);

        for (Player player : this.players.getArray(event)) {
            SendPacket.sendPacket(player, packet);
            SendPacket.sendPacket(player, build);
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "fake death";
    }

}
