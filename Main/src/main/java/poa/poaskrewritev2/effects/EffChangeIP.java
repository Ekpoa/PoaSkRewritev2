package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.ChangeIP;

public class EffChangeIP extends Effect {

    static {
        Skript.registerEffect(EffChangeIP.class, "set ip of %player% to %string% with port %number%");
    }

    private Expression<Player> playerExpression;
    private Expression<String> stringExpression;
    private Expression<Number> numberExpression;
    //private Expression<Player> playerListExpression;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        playerExpression = (Expression<Player>) exprs[0];
        stringExpression = (Expression<String>) exprs[1];
        numberExpression = (Expression<Number>) exprs[2];
        //playerListExpression = (Expression<Player>) exprs[2];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        Player player = playerExpression.getSingle(event);
        String ipString = stringExpression.getSingle(event);
        Number portNum = numberExpression.getSingle(event);
        if (player == null || ipString == null || portNum == null) return;

        int port = portNum.intValue();

        ChangeIP.changeIp(player, ipString, port);
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String player = playerExpression.toString(event, debug);
        String string = stringExpression.toString(event, debug);
        String number = numberExpression.toString(event, debug);
        return "set ip of " + player + " to " + string + " with port " + number;
    }

}
