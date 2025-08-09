package poa.poaskrewritev2.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.enginehub.linbus.stream.token.LinToken;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprRomanToNumber extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprRomanToNumber.class, Number.class, ExpressionType.SIMPLE,
                "roman numeral[s] %string% as [a] number");
    }

    private Expression<String> roman;

    @SuppressWarnings({"unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        roman = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    protected @Nullable Number[] get(Event event) {
        final String r = roman.getSingle(event);
        if (r == null) return null; // or: return new Number[0];
        return new Number[] { fromRoman(r) };
    }

    private int fromRoman(String roman) {
        int total = 0, prev = 0;
        roman = roman.toUpperCase();
        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = switch (roman.charAt(i)) {
                case 'I' -> 1;
                case 'V' -> 5;
                case 'X' -> 10;
                case 'L' -> 50;
                case 'C' -> 100;
                case 'D' -> 500;
                case 'M' -> 1000;
                default -> throw new IllegalArgumentException("Invalid Roman numeral: " + roman);
            };
            if (value < prev) total -= value;
            else total += value;
            prev = value;
        }
        return total;
    }


    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "roman to number";
    }

}
