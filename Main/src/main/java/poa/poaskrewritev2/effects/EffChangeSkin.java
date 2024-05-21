package poa.poaskrewritev2.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import poa.poaskrewritev2.util.FetchSkin;

import java.util.Set;
import java.util.UUID;

public class EffChangeSkin extends Effect {


    static {
        Skript.registerEffect(EffChangeSkin.class, "set skin of %player% to skin of %string%");
    }

    private Expression<Player> playerExpression;
    private Expression<String> stringExpression;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        playerExpression = (Expression<Player>) exprs[0];
        stringExpression = (Expression<String>) exprs[1];
        return true;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void execute(Event event) {
        Player player = playerExpression.getSingle(event);
        String skinName = stringExpression.getSingle(event);

        OfflinePlayer skinPlayer = Bukkit.getOfflinePlayer(skinName);
        UUID uuid = skinPlayer.getUniqueId();

        PlayerProfile profile = player.getPlayerProfile();

        Set<ProfileProperty> properties = profile.getProperties();

        properties.add(new ProfileProperty("textures", FetchSkin.fetchSkinURL(uuid), FetchSkin.fetchSkinSignature(uuid)));

        player.setPlayerProfile(profile);

    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "change skin";
    }

}
