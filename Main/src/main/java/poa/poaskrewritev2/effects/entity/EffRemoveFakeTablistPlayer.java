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
import poa.packets.FakePlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class EffRemoveFakeTablistPlayer extends Effect {

    static {
        Skript.registerEffect(EffRemoveFakeTablistPlayer.class,
                "remove tablist player[s] with uuid[s] %strings% for %players%");
    }

    private Expression<String> uuids;
    private Expression<Player> players;



    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        uuids = (Expression<String>) exprs[0];
        players = (Expression<Player>) exprs[1];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        List<UUID> uuids = new ArrayList<>();
        for (String s : this.uuids.getArray(event)) {
            uuids.add(UUID.fromString(s));
        }

        FakePlayer.removeTablistOnly(Arrays.stream(players.getArray(event)).toList(), uuids);
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "remove tablist player";
    }

}
