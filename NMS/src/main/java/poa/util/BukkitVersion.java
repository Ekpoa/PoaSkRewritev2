package poa.util;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class BukkitVersion {

    private static String version;

    public static String getBukkitVersion() {
        if(version != null)
            return version;

        version = Bukkit.getMinecraftVersion().replaceAll("[.]", "");
        Bukkit.getLogger().log(Level.INFO, "Raw version: " + Bukkit.getMinecraftVersion());
        return version;
    }
}
