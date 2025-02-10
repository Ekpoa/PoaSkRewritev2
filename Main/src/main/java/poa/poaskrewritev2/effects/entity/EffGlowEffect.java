package poa.poaskrewritev2.effects.entity;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Color;
import ch.njol.skript.util.SkriptColor;
import ch.njol.util.Kleenean;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.util.GetPose;
import poa.packets.Metadata;
import poa.packets.SendPacket;
import poa.packets.TeamPacket;
import poa.poaskrewritev2.PoaSkRewritev2;
import poa.util.GlowMap;

import java.util.ArrayList;
import java.util.List;

public class EffGlowEffect extends Effect {

    static {
        Skript.registerEffect(EffGlowEffect.class,
                "make %entities% glow %color% for %players%",
                "make %entities% not glow for %players%");
    }


    private int pattern;
    private Expression<Entity> entities;
    private Expression<Color> color;
    private Expression<Player> players;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        this.pattern = matchedPattern;
        entities = (Expression<Entity>) exprs[0];
        if (matchedPattern == 0) {
            color = (Expression<Color>) exprs[1];
            players = (Expression<Player>) exprs[2];
        } else {
            players = (Expression<Player>) exprs[1];
        }


        return true;
    }


    @SuppressWarnings("NullableProblems")
    @SneakyThrows
    @Override
    protected void execute(Event event) {

        String chatColor = "white";
        if (this.color != null)
            chatColor = ((SkriptColor) color.getSingle(event)).asChatColor().name();

        final boolean glow = pattern == 0;

        for (Entity entity : this.entities.getArray(event)) {
            String uuid = entity.getUniqueId().toString();
            if (entity instanceof Player player)
                uuid = player.getName();

            Metadata metadata = new Metadata(entity.getEntityId());
            metadata.setPose(GetPose.getPoseString(entity));
            metadata.setOnFire(entity.isVisualFire());

            metadata.setGlow(glow);

            final Object builtMetadata = metadata.build();

            for (Player player : players.getArray(event)) {
                if (entity instanceof LivingEntity li) {
                    metadata.setInvisible(li.isInvisible());
                    metadata.setGravity(li.hasGravity());
                }

                SendPacket.sendPacket(player, TeamPacket.teamPacketForGlow(uuid, chatColor, List.of(uuid)));
                if (glow) {
                    SendPacket.sendPacket(player, builtMetadata);
                }


                if (entity instanceof Player target) {
                    List<Integer> list = GlowMap.getGlowMap().get(player);
                    if (list == null)
                        list = new ArrayList<>();
                    if (!glow) {

                        if (!list.isEmpty())
                            list.remove((Integer) target.getEntityId());

                    } else {
                        if (!list.contains(target.getEntityId()))
                            list.add(target.getEntityId());
                    }
                    GlowMap.getGlowMap().put(player, list);
                }

                if (!glow)
                    SendPacket.sendPacket(player, builtMetadata);

//                if (entity instanceof Player p) //this is to update the glowing forcefully
//                    Bukkit.getScheduler().runTaskLater(PoaSkRewritev2.getINSTANCE(), () -> {
//                        p.setSilent(p.isSilent());
//                    }, 2L);
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        String entity = this.entities.toString(event, debug);
        String player = this.players.toString(event, debug);
        if (pattern == 0) {
            String color = this.color.toString(event, debug);
            return "make " + entity + " glow " + color + " for " + player;
        }
        return "make " + entity + " not glow for " + player;
    }


}
