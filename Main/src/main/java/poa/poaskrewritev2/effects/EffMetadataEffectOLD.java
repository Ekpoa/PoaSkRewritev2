package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.VariableString;
import ch.njol.util.Kleenean;
import lombok.SneakyThrows;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import poa.packets.Metadata;
import poa.packets.SendPacket;
import poa.poaskrewritev2.PoaSkRewritev2;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.logging.Level;


public class EffMetadataEffectOLD extends Effect implements Listener {

    static {
        if (Skript.isRunningMinecraft(1, 19, 3)) {
            Skript.registerEffect(EffMetadataEffectOLD.class,
                    "send metadata packet with (entity id %-number%|1:uuid %-string%) [on fire %-boolean%][,] " +
                            "[invisible %-boolean%][,] [glowing %-boolean%][,] [name %-string%][,] [name visible %-boolean%][,] " +
                            "[silent %-boolean%][,] [has gravity %-boolean%][,] [pose %-string%] to %players%");
        }
    }

    private Expression<?> id;
    private Expression<Boolean> onFire;
    private Expression<Boolean> isInvisible;
    private Expression<Boolean> isGlowing;
    private Expression<String> name;
    private Expression<Boolean> isNameVisible;
    private Expression<Boolean> isSilent;
    private Expression<Boolean> hasGravity;
    private Expression<String> pose;
    private Expression<Player> players;

    @SuppressWarnings({"NullableProblems", "unchecked"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        id = parseResult.hasTag("1") ? (Expression<String>) exprs[1] : (Expression<Number>) exprs[0];
        onFire = (Expression<Boolean>) exprs[2];
        isInvisible = (Expression<Boolean>) exprs[3];
        isGlowing = (Expression<Boolean>) exprs[4];
        name = (Expression<String>) exprs[5];
        isNameVisible = (Expression<Boolean>) exprs[6];
        isSilent = (Expression<Boolean>) exprs[7];
        hasGravity = (Expression<Boolean>) exprs[8];
        pose = (Expression<String>) exprs[9];
        players = (Expression<Player>) exprs[10];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @SneakyThrows
    @Override
    protected void execute(Event event) {
        Object single = id.getSingle(event);
        String stringUUID = null;
        int id = 0;
        if (single instanceof String s)
            stringUUID = s;
        else if (single instanceof Number n)
            id = n.intValue();

        Entity entity = null;
        if (stringUUID != null) {
            UUID uuid = UUID.fromString(stringUUID);
            entity = Bukkit.getEntity(uuid);
            if (entity == null) {
                PoaSkRewritev2.getINSTANCE().getLogger().log(Level.SEVERE, "No entity exists with that UUID. If it is a fake entity use the entity id");
                return;
            }
            id = entity.getEntityId();
        }

        boolean fire = false;
        boolean invisible = false;
        boolean glowing = false;
        String name = null;
        boolean nameVisible = false;
        boolean silent = false;
        boolean gravity = true;
        String pose = "STANDING";

        if (this.onFire == null) {
            if (entity != null)
                fire = entity.isVisualFire();
        } else
            fire = Boolean.TRUE.equals(this.onFire.getSingle(event));

        if (this.isInvisible == null) {
            if (entity instanceof LivingEntity li)
                invisible = li.isInvisible();
        } else
            invisible = Boolean.TRUE.equals(this.isInvisible.getSingle(event));

        if (this.isGlowing == null) {
            if (entity != null)
                glowing = entity.isGlowing();
        } else
            glowing = Boolean.TRUE.equals(this.isGlowing.getSingle(event));

        if (this.name == null) {
            if (entity != null && entity.customName() != null)
                name = MiniMessage.miniMessage().serialize(entity.customName());
        } else {
            name = this.name.getSingle(event);
            if (this.name instanceof VariableString vs)
                name = vs.toUnformattedString(event);
        }

        if (this.isNameVisible == null) {
            if (entity != null)
                nameVisible = entity.isCustomNameVisible();
        } else
            nameVisible = Boolean.TRUE.equals(this.isNameVisible.getSingle(event));

        if (this.isSilent == null) {
            if (entity != null)
                silent = entity.isSilent();
        } else
            silent = Boolean.TRUE.equals(this.isSilent.getSingle(event));

        if (this.hasGravity == null) {
            if (entity != null)
                gravity = entity.hasGravity();
        } else
            gravity = Boolean.TRUE.equals(this.hasGravity.getSingle(event));

        if (this.pose == null) {
            if (entity != null)
                pose = entity.getPose().name();
        } else
            pose = this.pose.getSingle(event);


        for (Player player : players.getArray(event)) {
            SendPacket.sendPacket(player, Metadata.basePacketForEntity(id, fire, invisible, glowing, name, nameVisible, silent, gravity, pose));
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String id = this.id.toString(event, debug);
        String onFire = this.onFire != null ? (" on fire " + this.onFire.getSingle(event)) : "";
        String invis = this.isInvisible != null ? (" invisible " + this.isInvisible.toString(event, debug)) : "";
        String glow = this.isGlowing != null ? (" glowing " + this.isGlowing.toString(event, debug)) : "";
        String name = this.name != null ? (" name " + this.name.toString(event, debug)) : "";
        String nameVis = this.isNameVisible != null ? (" name visible " + this.isNameVisible.toString(event, debug)) : "";
        String silent = this.isSilent != null ? (" silent " + this.isSilent.toString(event, debug)) : "";
        String gravity = this.hasGravity != null ? (" has gravity " + this.hasGravity.toString(event, debug)) : "";
        String pose = this.pose != null ? (" pose " + this.pose.toString(event, debug)) : "";
        String players = " to players " + this.players.toString(event, debug);
        return "send metadata packet with id/uuid " +
                id + onFire + invis + glow + name + nameVis + silent + gravity + pose + players;
    }

}
