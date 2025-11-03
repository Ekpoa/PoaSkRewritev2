package poa.poaskrewritev2.effects.entity;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.packets.FakePlayer;
import poa.packets.FakeTablist;
import poa.util.BukkitVersion;
import poa.util.Messages;
import poa.util.Messages1214;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class EffFakeTablistPlayer extends Effect {
    // !add player named "&bHello there you sexy thing" to tablist with skin named "Ekpoa" for me with uuid (random uuid)
    static {
        Skript.registerEffect(EffFakeTablistPlayer.class,
                "add [player] [named] %string% to tablist [with skin named %-string%] [with latency %-number%] [with username %-string%] for %players% [with] uuid %string%");
    }

    private Expression<String> name;
    private Expression<String> skinName;
    private Expression<Number> latency;
    private Expression<String> username;
    private Expression<Player> players;
    private Expression<String> uuid;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        skinName = (Expression<String>) exprs[1];
        latency = (Expression<Number>) exprs[2];
        username = (Expression<String>) exprs[3];
        players = (Expression<Player>) exprs[4];
        uuid = (Expression<String>) exprs[5];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {

        String skinName = this.skinName.getSingle(event);
        String name = this.name.getSingle(event);


        Component username = Component.text(name);

        if (this.username != null)
            username = MiniMessage.miniMessage().deserialize(Messages.essentialsToMinimessage(this.username.getSingle(event)));





        UUID uuid = UUID.fromString(this.uuid.getSingle(event));


        int latency = 0;
        if (this.latency != null)
            latency = this.latency.getSingle(event).intValue();


        final List<Player> playerList = Arrays.stream(players.getArray(event)).toList();
        FakeTablist.spawnTablistOnly(playerList, name, username, skinName, uuid, latency);

    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "fake tablist";
    }

}
