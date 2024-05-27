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

public class EffRemoveFakePlayer extends Effect {

    static {
        Skript.registerEffect(EffRemoveFakePlayer.class,
                "remove fake player named %strings% for %players%");
    }

    private Expression<String> name;
    private Expression<Player> players;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        players = (Expression<Player>) exprs[1];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        String[] name = this.name.getArray(event);
        List<UUID> uuids = new ArrayList<>();

        for (String s : name)
            uuids.add(FakePlayer.getNameToUuidMap().get(s));

        FakePlayer.removeFakePlayerPacket(Arrays.stream(players.getArray(event)).toList(), uuids);

    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "spawn fake player";
    }

}
