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
import poa.poaskrewritev2.PoaSkRewritev2;
import poa.poaskrewritev2.util.FetchSkin;

import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;

public class EffChangeSkin extends Effect {


    static {
        Skript.registerEffect(EffChangeSkin.class, "set skin of %players% to skin of %string% [with signature %-string%]");
    }

    private Expression<Player> playerExpression;
    private Expression<String> stringExpression;
    private Expression<String> signatureExpression;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        playerExpression = (Expression<Player>) exprs[0];
        stringExpression = (Expression<String>) exprs[1];
        signatureExpression = (Expression<String>) exprs[2];
        return true;
    }

    @SuppressWarnings({"NullableProblems", "ReassignedVariable"})
    @Override
    protected void execute(Event event) {
        String skinSignature = null;
        if(signatureExpression != null)
            skinSignature = signatureExpression.getSingle(event);

        String skinName = stringExpression.getSingle(event);

        String skinToUse = skinName;


        String signature;
        final boolean isPlayerSkin = skinName.length() <= 16;
        if(isPlayerSkin){
            OfflinePlayer skinPlayer = Bukkit.getOfflinePlayer(skinName);
            UUID uuid = skinPlayer.getUniqueId();
            skinToUse = FetchSkin.fetchSkinURL(uuid);
            signature = FetchSkin.fetchSkinSignature(uuid);
        }

        else if(skinSignature == null){
            PoaSkRewritev2.getINSTANCE().getLogger().log(Level.SEVERE, "Tried to get a skin with texture value: " + skinName + " but no signature was provided");
            return;
        }
        else
            signature = skinSignature;

        if(signature == null) {
            PoaSkRewritev2.getINSTANCE().getLogger().log(Level.SEVERE, "Could not get signature for " + skinName);
            return;
        }



        for (Player player : playerExpression.getArray(event)) {
            PlayerProfile profile = player.getPlayerProfile();

            Set<ProfileProperty> properties = profile.getProperties();


            properties.add(new ProfileProperty("textures", skinToUse, signature));

            player.setPlayerProfile(profile);
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "change skin";
    }

}
