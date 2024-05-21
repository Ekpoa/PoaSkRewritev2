package poa.poaskrewritev2.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.ExpressionType;
import com.destroystokyo.paper.event.player.PlayerConnectionCloseEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExprHostname extends SimplePropertyExpression<Player, String> implements Listener {

    public static Map<UUID, String> hostnameMap = new HashMap<>();

    static {
        Skript.registerExpression(ExprHostname.class, String.class, ExpressionType.SIMPLE,
                "%player%'s hostname");

        register(ExprHostname.class, String.class, "host[ ]name", "players");
    }

    @Override
    public @Nullable String convert(Player player) {
        return hostnameMap.get(player.getUniqueId());
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "host name";
    }

    @EventHandler
    public void onConnect(AsyncPlayerPreLoginEvent event) {
        hostnameMap.put(event.getUniqueId(), event.getHostname());
    }

    @EventHandler
    public void connectClose(PlayerConnectionCloseEvent event) {
        hostnameMap.remove(event.getPlayerUniqueId());
    }

}
