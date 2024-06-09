package poa.poaskrewritev2.effects.entity;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.parser.ParserInstance;
import ch.njol.util.Kleenean;
import io.papermc.paper.event.player.PlayerTrackEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.packets.FakePlayer;
import poa.packets.SendPacket;
import poa.packets.TeamPacket;
import poa.poaskrewritev2.PoaSkRewritev2;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class EffSetPlayerNameAndSkin extends Effect implements Listener {

    static {
        Skript.registerEffect(EffSetPlayerNameAndSkin.class,
                "fake name of %player% to %string% with skin named %string% for %players%");
    }



    private static final ConcurrentMap<Player, Map<Player, String>> playerSkinMap = new ConcurrentHashMap<>();
    private static final ConcurrentMap<Player, Map<Player, String>> playerNameMap = new ConcurrentHashMap<>();



    private Expression<Player> target;
    private Expression<String> name;
    private Expression<String> skinName;
    private Expression<Player> players;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        target = (Expression<Player>) exprs[0];
        name = (Expression<String>) exprs[1];
        skinName = (Expression<String>) exprs[2];
        players = (Expression<Player>) exprs[3];
        return true;
    }


    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        String name = this.name.getSingle(event);
        String skinName = this.skinName.getSingle(event);

        Player target = this.target.getSingle(event);
        int id = target.getEntityId();

        Player[] players = this.players.getArray(event);

        FakePlayer.spawnFakePlayer(Arrays.stream(players).toList(), name, skinName, target.getLocation(), true, target.getPing(), id, UUID.randomUUID());



        for (Player player : players) {
            Map<Player, String> nameMap = new HashMap<>();
            Map<Player, String> skinMap = new HashMap<>();

            if (playerNameMap.containsKey(player))
                nameMap = playerNameMap.get(player);
            if (playerSkinMap.containsKey(player))
                nameMap = playerSkinMap.get(player);

            nameMap.put(target, name);
            skinMap.put(target, skinName);

            playerNameMap.put(player, nameMap);
            playerSkinMap.put(player, skinMap);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "set player name and skin";
    }


    @EventHandler
    public void entityLoadEvent(PlayerTrackEntityEvent e){
        if(!(e.getEntity() instanceof Player target))
            return;

        Player player = e.getPlayer();

        if(!playerNameMap.containsKey(player)) {
            Bukkit.broadcastMessage("a");
            return;
        }

        String name = playerNameMap.get(player).get(target);
        String skinName = playerSkinMap.get(player).get(target);

        Bukkit.broadcastMessage("Name: " + name + "    Skin: " + skinName);

        Bukkit.getScheduler().runTaskLaterAsynchronously(PoaSkRewritev2.getINSTANCE(),
                () -> FakePlayer.spawnFakePlayer(List.of(player), name, skinName, target.getLocation(), true, target.getPing(), target.getEntityId(), UUID.randomUUID()),
                1L);


    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        for (Map.Entry<Player, Map<Player, String>> entry : playerNameMap.entrySet()) {
            Map<Player, String> map = entry.getValue();
            if(map.containsKey(player)){
                map.remove(player);
                playerNameMap.put(entry.getKey(), map);
            }
        }

        for (Map.Entry<Player, Map<Player, String>> entry : playerSkinMap.entrySet()) {
            Map<Player, String> map = entry.getValue();
            if(map.containsKey(player)){
                map.remove(player);
                playerSkinMap.put(entry.getKey(), map);
            }
        }
    }

}
