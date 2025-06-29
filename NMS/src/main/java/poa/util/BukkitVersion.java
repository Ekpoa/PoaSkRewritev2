package poa.util;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class BukkitVersion {

    private static String version;

    public static String getBukkitVersion() {
        if(version != null)
            return version;

        version = Bukkit.getMinecraftVersion().replaceAll("[.]", "");

        if(version.equalsIgnoreCase("1212"))
            version = "1213";

        else if (version.equalsIgnoreCase("1216"))
            version.equalsIgnoreCase("1215");

        Bukkit.getLogger().log(Level.INFO, "Using version: " + Bukkit.getMinecraftVersion());




        return version;
    }
}
