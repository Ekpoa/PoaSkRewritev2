package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.bukkitutil.EntityUtils;
import ch.njol.skript.entity.EntityType;
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
import poa.packets.ChangeVision;

public class EffChangeVision extends Effect {

    static {
        Skript.registerEffect(EffChangeVision.class, "change vision of %players% to [entity] %entitytype%");

    }


    private Expression<Player> players;
    private Expression<ch.njol.skript.entity.EntityType> entityType;


    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        players = (Expression<Player>) exprs[0];
        entityType = (Expression<EntityType>) exprs[1];
        return true;
    }

    @Override
    protected void execute(Event event) {

        String entity = EntityUtils.toBukkitEntityType(entityType.getSingle(event).data).toString();

        for (Player player : players.getArray(event)) {
            ChangeVision.changeVision(player, entity);
        }

    }


    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "todo";
    }

}
