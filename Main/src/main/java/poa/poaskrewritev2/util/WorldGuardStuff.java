package poa.poaskrewritev2.util;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.internal.platform.WorldGuardPlatform;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import poa.poaskrewritev2.PoaSkRewritev2;

import java.util.Map;
import java.util.logging.Level;

public class WorldGuardStuff {



    public static ProtectedRegion getRegion(World world, String string){
        final Map<String, ProtectedRegion> regions = getRegions(world);
        if(regions == null){
            PoaSkRewritev2.getINSTANCE().getLogger().log(Level.WARNING, "Regions null for world " + world);
            return null;
        }

        for (Map.Entry<String, ProtectedRegion> entry : regions.entrySet()) {
            if(entry.getKey().equalsIgnoreCase(string))
                return entry.getValue();
        }
        return null;
    }

    public static Map<String, ProtectedRegion> getRegions(World world) {
        if(!Bukkit.getPluginManager().isPluginEnabled("WorldGuard")){
            PoaSkRewritev2.getINSTANCE().getLogger().log(Level.WARNING, "World Guard Not Found");
            return null;
        }

        final WorldGuardPlatform platform = WorldGuard.getInstance().getPlatform();
        final RegionContainer regionContainer = platform.getRegionContainer();
        RegionManager manager = regionContainer.get(BukkitAdapter.adapt(world));

        if (manager == null) return null;

        return manager.getRegions();

    }


}
