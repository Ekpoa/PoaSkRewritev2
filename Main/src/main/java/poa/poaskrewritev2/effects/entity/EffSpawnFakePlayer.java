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

public class EffSpawnFakePlayer extends Effect {

    static {
        Skript.registerEffect(EffSpawnFakePlayer.class,
                "spawn fake player named %string% [with skin named %-string%] [with name hidden %-boolean%] [on tablist %-boolean%] [with latency %-number%] at %location% for %players% with id %number% [and] uuid %string%");
    }

    private Expression<String> name;
    private Expression<String> skinName;
    private Expression<Boolean> nameHidden;
    private Expression<Boolean> listed;
    private Expression<Number> latency;
    private Expression<Location> location;
    private Expression<Player> players;
    private Expression<Number> id;
    private Expression<String> uuid;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        skinName = (Expression<String>) exprs[1];
        nameHidden = (Expression<Boolean>) exprs[2];
        listed = (Expression<Boolean>) exprs[3];
        latency = (Expression<Number>) exprs[4];
        location = (Expression<Location>) exprs[5];
        players = (Expression<Player>) exprs[6];
        id = (Expression<Number>) exprs[7];
        uuid = (Expression<String>) exprs[8];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        String name = this.name.getSingle(event);

        String skinName = this.skinName.getSingle(event);

        String texture = null;
        String signature = null;

        if(skinName != null && skinName.length() > 16){
            skinName = skinName.replaceAll(" ", "");
            List<String> split = Arrays.stream(skinName.split(",")).toList();

            texture = split.get(0);
            signature = split.get(1);
        }

        boolean listed = false;

        boolean nameHidden = false;

        int id = this.id.getSingle(event).intValue();

        UUID uuid = UUID.fromString(this.uuid.getSingle(event));

        if (this.nameHidden != null)
            nameHidden = this.nameHidden.getSingle(event).booleanValue();

        if (this.listed != null)
            listed = this.listed.getSingle(event).booleanValue();

        int latency = 0;
        if (this.latency != null)
            latency = this.latency.getSingle(event).intValue();

        Location location = this.location.getSingle(event);




        final List<Player> playerList = Arrays.stream(players.getArray(event)).toList();
        if(texture == null) {
            FakePlayer.spawnFakePlayer(playerList, name, skinName, location, listed, latency, id, uuid);
        } else
            FakePlayer.spawnFakePlayer(playerList, name, texture, signature, location, listed, latency, id, uuid, 127);

        if(nameHidden){
            for (Player p : players.getArray(event)) {
                SendPacket.sendPacket(p, TeamPacket.teamPacket("nameHidden", "nameHidden", "never", "always", "white", "", "", List.of(name)));
            }
        }

    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "spawn fake player";
    }

}
