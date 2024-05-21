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
import poa.SendPacket;
import poa.SetEquipmentPacket;

public class EffFakeItem extends Effect {

    static {
        Skript.registerEffect(EffFakeItem.class, "fake %livingentities/numbers%['s] equipment slot %string% to %itemtype% for %players%");
    }

    private Expression<?> entities;
    private Expression<String> slot;
    private Expression<ItemType> item;
    private Expression<Player> players;

    @SuppressWarnings({"NullableProblems", "unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        entities = exprs[0];
        slot = (Expression<String>) exprs[1];
        item = (Expression<ItemType>) exprs[2];
        players = (Expression<Player>) exprs[3];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        String slot = this.slot.getSingle(event);
        ItemType itemType = this.item.getSingle(event);
        if (slot == null || itemType == null) return;

        ItemStack itemStack = itemType.getRandom();

        EquipmentSlot equipmentSlot = EquipmentSlot.valueOf(slot.toUpperCase());

        for (Player player : this.players.getArray(event))
            for (Object object : this.entities.getArray(event))
                if(object instanceof LivingEntity livingEntity)
                    player.sendEquipmentChange(livingEntity, equipmentSlot, itemStack);
                else if (object instanceof Long)
                  SendPacket.sendPacket(player, SetEquipmentPacket.packet(((Long) object).intValue(), slot, itemStack));


    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String entity = this.entities.toString(event, debug);
        String slot = this.slot.toString(event, debug);
        String item = this.item.toString(event, debug);
        String player = this.players.toString(event, debug);
        return "fake " + entity + "'s equipment slot " + slot + " to " + item + " for " + player;
    }

}
