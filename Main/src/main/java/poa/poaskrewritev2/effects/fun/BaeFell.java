package poa.poaskrewritev2.effects.fun;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.FakePlayer;
import poa.SendPacket;
import poa.TeamPacket;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class BaeFell extends Effect {

    static {
        Skript.registerEffect(BaeFell.class, "baefell[-]ify [the] %entities% for %players%");
    }

    private Expression<Entity> entities;
    private Expression<Player> players;


    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        entities = (Expression<Entity>) exprs[0];
        players = (Expression<Player>) exprs[1];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        if(this.entities == null)
            return;

        for (Entity entity : this.entities.getArray(event)) {
            String name = "BF" + ThreadLocalRandom.current().nextInt(0, 99999999);
            FakePlayer.spawnFakePlayer(Arrays.stream(players.getArray(event)).toList(), name, "BaeFell", entity.getLocation(), false, 0, entity.getEntityId(), UUID.randomUUID());
            for (Player p : players.getArray(event)) {
                SendPacket.sendPacket(p, TeamPacket.teamPacket("nameHidden", "nameHidden", "never", "always", "white", "", "", List.of(name)));
            }
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String player = this.players.toString(event, debug);
        Entity[] ent = this.entities.getArray(event);
        return "baefellify " + Arrays.toString(ent) + " for " + player;
    }

}
