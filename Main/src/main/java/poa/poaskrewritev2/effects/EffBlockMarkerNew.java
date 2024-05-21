package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.Color;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import poa.SendPayloadPacket;

@Name("Create Debug Marker New") // Call this whatever you want!
@Description({"Create a colored debug marker at a location for players.",
    "Optionally you can add a text, alpha (transparency) and duration.",
    "Requires Minecraft 1.20.2+"})
@Examples({"create debug marker with color blue for player",
    "create debug marker with color rgb(200,100,100) for all players",
    "create debug marker with color red and with alpha 100 for all players",
    "create debug marker with color rgb(255,100,23) with alpha 50 for all players",
    "create debug marker with color green with alpha 25 with text \"I'M A MARKER!\" for player",
    "create debug marker with color yellow for all players for 10 seconds",
    "create debug marker with color rgb(100,100,100) for all players for 10 minutes"})
@Since("INSERT VERSION")
public class EffBlockMarkerNew extends Effect {

    static {
        // Packets changed in 1.20.2, this method is all new
        if (Skript.isRunningMinecraft(1, 20, 2)) {
            Skript.registerEffect(EffBlockMarkerNew.class, "create debug marker at %locations% with color %color% " +
                "[[and ]with alpha %-number%] [[and ]with text %-string%] for %players% [for %-timespan%]");
        }
    }

    private Expression<Location> location;
    private Expression<Color> color;
    private Expression<Number> alpha;
    private Expression<String> text;
    private Expression<Player> players;
    private Expression<Timespan> timespan;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int i, @NotNull Kleenean kleenean, @NotNull SkriptParser.ParseResult parseResult) {
        this.location = (Expression<Location>) exprs[0];
        this.color = (Expression<Color>) exprs[1];
        this.alpha = (Expression<Number>) exprs[2];
        this.text = (Expression<String>) exprs[3];
        this.players = (Expression<Player>) exprs[4];
        this.timespan = (Expression<Timespan>) exprs[5];
        return true;
    }

    @Override
    protected void execute(@NotNull Event event) {
        Color skriptColor = this.color.getSingle(event);
        if (skriptColor == null) return;

        org.bukkit.Color bukkitColor = skriptColor.asBukkitColor();
        if (this.alpha != null) {
            Number alpha = this.alpha.getSingle(event);
            if (alpha != null) {
                // Clamp between 0 and 255
                int i = Math.max(0, Math.min(alpha.intValue(), 255));
                bukkitColor = bukkitColor.setAlpha(i);
            }
        }

        String text = "";
        if (this.text != null) text = this.text.getSingle(event);

        int duration = Integer.MAX_VALUE;
        if (this.timespan != null) {
            Timespan timespan = this.timespan.getSingle(event);
            if (timespan != null) {
                // Don't go over max
                duration = Math.min(Integer.MAX_VALUE, (int) timespan.getMilliSeconds());
            }
        }

        for (Player player : this.players.getArray(event)) {
            for (Location location : this.location.getArray(event)) {
                SendPayloadPacket.sendGameTestMarker(player, location, text, bukkitColor, duration);
            }
        }
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        String location = this.location.toString(event, debug);
        String color = this.color.toString(event, debug);
        String alpha = this.alpha != null ? (" with alpha " + this.alpha.toString(event, debug)) : "";
        String text = this.text != null ? (" with text " + this.text.toString(event, debug)) : "";
        String players = this.players.toString(event, debug);
        String time = this.timespan != null ? (" for " + this.timespan.toString(event, debug)) : "";
        return String.format("create debug marker at %s with color %s%s%s%s%s", location, color, alpha, text, players, time);
    }

}
