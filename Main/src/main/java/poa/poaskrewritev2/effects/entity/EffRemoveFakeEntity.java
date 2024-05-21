package poa.poaskrewritev2.effects.entity;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.FakeEntity;
import poa.SendPacket;

import java.util.ArrayList;
import java.util.List;

public class EffRemoveFakeEntity extends Effect {

    static {
        Skript.registerEffect(EffRemoveFakeEntity.class, "remove fake entit(y|ies) with id[s] %numbers% for %players%");
    }

    private Expression<Number> ids;
    private Expression<Player> players;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        ids = (Expression<Number>) exprs[0];
        players = (Expression<Player>) exprs[1];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        List<Integer> idList = new ArrayList<>();

        for (Number id : this.ids.getArray(event))
            idList.add(id.intValue());

        Object packet = FakeEntity.removeFakeEntityPacket(idList);

        for (Player player : this.players.getArray(event))
            SendPacket.sendPacket(player, packet);
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String ids = this.ids.toString(event, debug);
        String players = this.players.toString(event, debug);
        return "remove fake entities with ids " + ids + " for " + players;
    }

}
