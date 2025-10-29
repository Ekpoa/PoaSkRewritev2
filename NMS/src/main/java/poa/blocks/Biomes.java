package poa.blocks;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import poa.util.BukkitVersion;

import java.util.Set;
import java.util.logging.Level;

public class Biomes {

    public static void sendBiomePacketAsync(Plugin plugin, Set<Player> players, Location loc1, Location loc2, String biome){
        switch (BukkitVersion.getBukkitVersion()){
            case "1213" -> Biomes1213.sendBiomeBetweenAsync(plugin, players, loc1, loc2, biome);
            case "1214" -> Biomes1214.sendBiomeBetweenAsync(plugin, players, loc1, loc2, biome);
            case "1215" -> Biomes1215.sendBiomeBetweenAsync(plugin, players, loc1, loc2, biome);
            case "1216" -> Biomes1216.sendBiomeBetweenAsync(plugin, players, loc1, loc2, biome);
            case "1217" -> Biomes1217.sendBiomeBetweenAsync(plugin, players, loc1, loc2, biome);
            case "1218" -> Biomes1218.sendBiomeBetweenAsync(plugin, players, loc1, loc2, biome);
            case "1219" -> Biomes1219.sendBiomeBetweenAsync(plugin, players, loc1, loc2, biome);
            case "12110" -> Biomes12110.sendBiomeBetweenAsync(plugin, players, loc1, loc2, biome);
            default -> plugin.getLogger().log(Level.WARNING, "Fake biomes is only for 1.21.3 +");
        }
    }

}
