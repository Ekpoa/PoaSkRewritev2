package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import fr.skytasul.guardianbeam.Laser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.poask.PoaSK;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class EffGuardianLaserCreate extends Effect {

    static {
        Skript.registerEffect(EffGuardianLaserCreate.class, "create a guardian laser from %location% to %location% with id %string%");
    }

    private Expression<Location> loc1;
    private Expression<Location> loc2;
    private Expression<String> id;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        loc1 = (Expression<Location>) exprs[0];
        loc2 = (Expression<Location>) exprs[1];
        id = (Expression<String>) exprs[2];
        return true;
    }

    public static Map<String, Laser> laserIDMap = new HashMap<>();

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        Laser laser;
        String laserID = id.getSingle(event);
        Location location1 = loc1.getSingle(event);
        Location location2 = loc2.getSingle(event);
        if (location1 == null || location2 == null) {
            Bukkit.getLogger().log(Level.WARNING, "Location 1 = " + location1 + " Location 2 = " + location2 + " One is missing. For guardian laser");
            return;
        }
        if (laserIDMap.containsKey(laserID))
            return;
        try {
            laser = new Laser.GuardianLaser(location1, location2, Integer.MAX_VALUE, 90);
            laserIDMap.put(laserID, laser);
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
        laser.start(PoaSK.getInstance());
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
