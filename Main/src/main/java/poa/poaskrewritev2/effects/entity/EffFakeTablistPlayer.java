package poa.poaskrewritev2.effects.entity;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.packets.FakePlayer;
import poa.packets.SendPacket;
import poa.packets.TeamPacket;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class EffFakeTablistPlayer extends Effect {

    static {
        Skript.registerEffect(EffFakeTablistPlayer.class,
                "add [player] [named] %string% to tablist [with skin named %-string%] [with latency %-number%] for %players% [with] uuid %string%");
    }

    private Expression<String> name;
    private Expression<String> skinName;
    private Expression<Number> latency;
    private Expression<Player> players;
    private Expression<String> uuid;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        skinName = (Expression<String>) exprs[1];
        latency = (Expression<Number>) exprs[2];
        players = (Expression<Player>) exprs[3];
        uuid = (Expression<String>) exprs[4];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        String name = this.name.getSingle(event);

        String skinName = this.skinName.getSingle(event);

        String texture = null;
        String signature = null;

        if (skinName != null && skinName.length() > 16) {
            skinName = skinName.replaceAll(" ", "");
            List<String> split = Arrays.stream(skinName.split(",")).toList();

            texture = split.get(0);
            signature = split.get(1);
        }


        UUID uuid = UUID.fromString(this.uuid.getSingle(event));


        int latency = 0;
        if (this.latency != null)
            latency = this.latency.getSingle(event).intValue();


        final List<Player> playerList = Arrays.stream(players.getArray(event)).toList();
        if (texture == null)
            FakePlayer.spawnTablistOnly(playerList, name, skinName, uuid, latency);
        else
            FakePlayer.spawnTablistOnly(playerList, name, uuid, texture, signature, latency);

    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "fake tablist";
    }

}
