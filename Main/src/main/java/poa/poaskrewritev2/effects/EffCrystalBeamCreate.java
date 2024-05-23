package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import fr.skytasul.guardianbeam.Laser;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.poaskrewritev2.PoaSkRewritev2;


import java.util.HashMap;
import java.util.Map;

public class EffCrystalBeamCreate extends Effect {

    static {
        Skript.registerEffect(EffCrystalBeamCreate.class,
                "create a crystal beam from %location% to %location% for %number% with id %string%");
    }

    private Expression<Location> loc1;
    private Expression<Location> loc2;
    private Expression<Number> seconds;
    private Expression<String> id;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        loc1 = (Expression<Location>) exprs[0];
        loc2 = (Expression<Location>) exprs[1];
        seconds = (Expression<Number>) exprs[2];
        id = (Expression<String>) exprs[3];
        return true;
    }

    public static Map<String, Laser> crystalIDMap = new HashMap<>();

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        Laser laser;
        String laserID = id.getSingle(event);
        Location loc1 = this.loc1.getSingle(event);
        Location loc2 = this.loc2.getSingle(event);
        Number secondsNum = this.seconds.getSingle(event);
        if (laserID == null || loc1 == null || loc2 == null || secondsNum == null) return;

        if (crystalIDMap.containsKey(laserID))
            return;
        try {
            laser = new Laser.CrystalLaser(loc1, loc2, secondsNum.intValue(), 140);
            crystalIDMap.put(laserID, laser);
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException(ex);
        }
        laser.start(PoaSkRewritev2.getINSTANCE());
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String loc1 = this.loc1.toString(event, debug);
        String loc2 = this.loc2.toString(event, debug);
        String seconds = this.seconds.toString(event, debug);
        String id = this.id.toString(event, debug);
        return "create crystal beam from " + loc1 + " to " + loc2 + " for " + seconds + " with id " + id;
    }

}
