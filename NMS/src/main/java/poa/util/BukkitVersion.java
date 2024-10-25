package poa.util;

import org.bukkit.Bukkit;

public class BukkitVersion {

    private static String version;

    public static String getBukkitVersion() {
        if(version != null)
            return version;

        version = Bukkit.getMinecraftVersion().replaceAll("[.]", "");
        System.out.println("Raw version: " + Bukkit.getMinecraftVersion());
        return version;
    }
}
