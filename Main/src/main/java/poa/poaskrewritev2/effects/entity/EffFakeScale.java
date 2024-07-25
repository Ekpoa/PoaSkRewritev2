package poa.poaskrewritev2.effects.entity;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.packets.AttributePacket121;
import poa.packets.FakeDeathAnimation;
import poa.packets.Metadata;
import poa.packets.SendPacket;
import poa.poaskrewritev2.PoaSkRewritev2;
import poa.util.BukkitVersion;

import java.util.List;
import java.util.logging.Level;

public class EffFakeScale extends Effect implements Listener {

    static {
        Skript.registerEffect(EffFakeScale.class,
                "fake scale [packet] of entity with id %number% with [value|size] %number% for %players%");
    }

    private Expression<Number> id;
    private Expression<Number> size;
    private Expression<Player> players;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        id = (Expression<Number>) exprs[0];
        size = (Expression<Number>) exprs[1];
        players = (Expression<Player>) exprs[2];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @SneakyThrows
    @Override
    protected void execute(Event event) {
        if(List.of("1202", "1204").contains(BukkitVersion.getBukkitVersion())){
            PoaSkRewritev2.getINSTANCE().getLogger().log(Level.SEVERE, "The scale effect cannot be used on version below 1.20.6");
            return;
        }

        Number idNum = this.id.getSingle(event);
        if (idNum == null) return;

        int id = idNum.intValue();
        double scale = size.getSingle(event).doubleValue();

        Object packet = AttributePacket121.packet(id, "generic_scale", scale);

        for (Player player : this.players.getArray(event)) {
            SendPacket.sendPacket(player, packet);
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "fake size";
    }

}
