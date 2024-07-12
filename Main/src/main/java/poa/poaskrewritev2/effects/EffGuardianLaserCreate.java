package poa.poaskrewritev2.effects;

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
import poa.guardian.GuardianBeam;
import poa.poaskrewritev2.PoaSkRewritev2;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class EffGuardianLaserCreate extends Effect {

    static {
        Skript.registerEffect(EffGuardianLaserCreate.class, "create [a] guardian laser from %location% to %location% [with color %-string%] with id %string% for %players%");
    }

    private Expression<Location> loc1;
    private Expression<Location> loc2;
    private Expression<String> color;
    private Expression<Player> players;
    private Expression<String> id;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        loc1 = (Expression<Location>) exprs[0];
        loc2 = (Expression<Location>) exprs[1];
        color = (Expression<String>) exprs[2];
        id = (Expression<String>) exprs[3];
        players = (Expression<Player>) exprs[4];
        return true;
    }


    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        String laserID = id.getSingle(event);
        Location location1 = loc1.getSingle(event);
        Location location2 = loc2.getSingle(event);
        String color = "white";
        if(this.color != null)
            color = this.color.getSingle(event);

        final List<Player> list = Arrays.stream(players.getArray(event)).toList();

        final GuardianBeam guardianBeam = new GuardianBeam(list, laserID, location1, location2, color, PoaSkRewritev2.getINSTANCE());
        guardianBeam.loop();
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String loc1 = this.loc1.toString(event,debug);
        String loc2 = this.loc2.toString(event,debug);
        String id = this.id.toString(event,debug);
        return "create a guardian laser from " + loc1 + " to " + loc2 + " with id " + id;
    }

}
