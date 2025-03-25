package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.packets.ItemCooldown;
import poa.packets.ItemCooldown1214;
import poa.packets.SendPacket;
import poa.packets.SetEquipmentPacket;
import poa.poaskrewritev2.PoaSkRewritev2;

import java.util.logging.Level;

public class EffFakeItemCooldown extends Effect {

    static {
        Skript.registerEffect(EffFakeItemCooldown.class, "fake item cooldown of %itemtype% for %players% for %number% ticks");
    }

    private Expression<ItemType> item;
    private Expression<Player> players;
    private Expression<Number> time;

    @SuppressWarnings({"NullableProblems", "unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        item = (Expression<ItemType>) exprs[0];
        players = (Expression<Player>) exprs[1];
        time = (Expression<Number>) exprs[2];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        final @Nullable ItemStack itemStack = item.getSingle(event).getRandom();
        if(itemStack == null)
            return;

        final Player[] sendTo = players.getArray(event);
        final int ticks = time.getSingle(event).intValue();

        for (Player player : sendTo) {
            SendPacket.sendPacket(player, ItemCooldown.packet(itemStack, ticks));
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "fake item cooldown";
    }

}
