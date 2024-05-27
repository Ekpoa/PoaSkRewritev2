package poa.poaskrewritev2.effects.entity;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import lombok.SneakyThrows;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.packets.Metadata;
import poa.packets.SendPacket;

public class EffFakeEntityName extends Effect implements Listener {

    static {
        Skript.registerEffect(EffFakeEntityName.class, "fake %entity%'s name to %string% for %players%");
    }

    private Expression<Entity> entity;
    private Expression<String> name;
    private Expression<Player> players;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        entity = (Expression<Entity>) exprs[0];
        name = (Expression<String>) exprs[1];
        players = (Expression<Player>) exprs[2];
        return true;
    }

    @SuppressWarnings({"NullableProblems", "UnstableApiUsage"})
    @SneakyThrows
    @Override
    protected void execute(Event event) {
        Entity entity = this.entity.getSingle(event);
        String name = this.name.getSingle(event);
        if (entity == null || name == null) return;

        for (Player player : this.players.getArray(event)) {
            boolean invisible = !entity.isVisibleByDefault();

            if (entity instanceof LivingEntity livingEntity)
                invisible = livingEntity.isInvisible();

            SendPacket.sendPacket(player, Metadata.basePacketForEntity(entity.getEntityId(),
                    entity.isVisualFire(), invisible, entity.isGlowing(), name, entity.isCustomNameVisible(),
                    entity.isSilent(), entity.hasGravity(), "STANDING"));
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String entity = this.entity.toString(event, debug);
        String name = this.name.toString(event, debug);
        String players = this.players.toString(event, debug);
        return "fake " + entity + "'s name to " + name + " for " + players;
    }

}
