package poa.poaskrewritev2.hooks;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.nametag.NameTagManager;
import org.bukkit.entity.Player;

public class Tab {

    public static void removeListener(Player player){
        TabPlayer tp = TabAPI.getInstance().getPlayer(player.getUniqueId());
        NameTagManager nt = TabAPI.getInstance().getNameTagManager();
        nt.pauseTeamHandling(tp);
    }

}
