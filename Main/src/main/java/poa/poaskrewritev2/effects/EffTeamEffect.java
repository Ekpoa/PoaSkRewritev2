package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Color;
import ch.njol.skript.util.SkriptColor;
import ch.njol.util.Kleenean;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.packets.SendPacket;
import poa.packets.TeamPacket;

import java.util.Arrays;
import java.util.List;


public class EffTeamEffect extends Effect {

    static {
        Skript.registerEffect(EffTeamEffect.class,
                "send team packet to %players% with name %string% [displayname %-string%] [nametag visibility %-string%] " +
                        "[collision %-string%] [color %-color%] [prefix %-string%] [suffix %-string%] [and] [see friendly %-boolean%] with entity uuid %strings%");
    }

    private Expression<Player> player;
    private Expression<String> name;
    private Expression<String> displayName;
    private Expression<String> nameTagVisibility;
    private Expression<String> collision;
    private Expression<Color> color;
    private Expression<String> prefix;
    private Expression<String> suffix;
    private Expression<Boolean> seeFriendly;
    private Expression<String> uuid;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        player = (Expression<Player>) exprs[0];
        name = (Expression<String>) exprs[1];
        displayName = (Expression<String>) exprs[2];
        nameTagVisibility = (Expression<String>) exprs[3];
        collision = (Expression<String>) exprs[4];
        color = (Expression<Color>) exprs[5];
        prefix = (Expression<String>) exprs[6];
        suffix = (Expression<String>) exprs[7];
        seeFriendly = (Expression<Boolean>) exprs[8];
        uuid = (Expression<String>) exprs[9];
        return true;
    }

    @SneakyThrows
    @Override
    protected void execute(Event event) {
        Expression<Color> color = this.color;

        String s1 = name.getSingle(event);
        Expression<String> s2 = displayName;
        Expression<String> s3 = nameTagVisibility;
        Expression<String> s4 = collision;
        Expression<String> s5 = prefix;
        Expression<String> s6 = suffix;
        Expression<Boolean> s7 = seeFriendly;
        List<String> list = Arrays.stream(uuid.getArray(event)).toList();

        //String chatColor = ((SkriptColor) color).asChatColor().name();

        String displayname = "";
        String visibility = "always";
        String collision = "always";
        String chatColor = "white";
        String prefix = "";
        String suffix = "";
        boolean seeFriendly = true;

        if (s2 != null)
            displayname = s2.getSingle(event);
        if (s3 != null)
            visibility = s3.getSingle(event);
        if (s4 != null)
            collision = s4.getSingle(event);
        if (s5 != null)
            prefix = s5.getSingle(event);
        if (s6 != null)
            suffix = s6.getSingle(event);
        if (s7 != null)
            seeFriendly = s7.getSingle(event);

        if (color != null)
            chatColor = ((SkriptColor) color.getSingle(event)).asChatColor().name();

        for (Player p : player.getArray(event)) {
            SendPacket.sendPacket(p, TeamPacket.teamPacket(s1, displayname, visibility, collision, chatColor, prefix, suffix, seeFriendly, list));
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String player = this.player.toString(event, debug);
        String name = this.name.toString(event, debug);
        String displayName = this.displayName != null ? (" display name " + this.displayName.toString(event, debug)) : "";
        String nametag = this.nameTagVisibility != null ? (" nametag visibility " + this.nameTagVisibility.toString(event, debug)) : "";
        String collision = this.collision != null ? (" collision " + this.collision.toString(event, debug)) : "";
        String color = this.color != null ? (" color " + this.color.toString(event, debug)) : "";
        String prefix = this.prefix != null ? (" prefix " + this.prefix.toString(event, debug)) : "";
        String suffix = this.suffix != null ? (" suffix " + this.suffix.toString(event, debug)) : "";
        String uuid = this.uuid.toString(event, debug);
        return "send team placket to " + player + " with name " + name + displayName + nametag +
                collision + color + prefix + suffix + " with entity uuid " + uuid;
    }

}
