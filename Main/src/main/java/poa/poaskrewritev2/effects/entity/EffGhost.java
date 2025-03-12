package poa.poaskrewritev2.effects.entity;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.packets.AnimatePacket;
import poa.packets.SendPacket;
import poa.packets.TeamPacket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class EffGhost extends Effect {

    static {
        Skript.registerEffect(EffGhost.class,
                "(send|make) %players% see %entities% as ghost [packet]");
    }

    private Expression<Player> playerExpression;
    private Expression<Entity> entityExpression;

    @SuppressWarnings({"NullableProblems", "unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        playerExpression = (Expression<Player>) exprs[0];
        entityExpression = (Expression<Entity>) exprs[1];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @SneakyThrows
    @Override
    protected void execute(Event event) {
        final Entity[] entities = entityExpression.getArray(event);
        final Player[] players = playerExpression.getArray(event);

        List<String> uuids = new ArrayList<>();
        uuids.addAll(Arrays.stream(entities).map(Entity::getUniqueId).map(UUID::toString).toList());
        uuids.addAll(Arrays.stream(players).map(Player::getName).toList());

        final Object packet = TeamPacket.teamPacket(UUID.randomUUID().toString(), "", "always", "always", "white", "", "", true, uuids);
        for (Player player : players)
            SendPacket.sendPacket(player, packet);

    }


    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "ghost packet";
    }

}
