package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.guardian.GuardianBeam;

public class EffGuardianLaserRemove extends Effect {

    static {
        Skript.registerEffect(EffGuardianLaserRemove.class, "remove guardian laser with id %string%");
    }

    private Expression<String> id;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        id = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    protected void execute(Event e) {
        String laserID = id.getSingle(e);

        final GuardianBeam beam = GuardianBeam.getBeam(laserID);
        if(beam == null)
            return;
        beam.destroy();
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "remove guardian laser beam with id " + this.id.toString(event, debug);
    }

}
