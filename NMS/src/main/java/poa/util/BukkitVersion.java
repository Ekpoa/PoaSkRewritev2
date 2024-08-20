package poa.util;

import org.bukkit.Bukkit;

public class BukkitVersion {

    private static String version;

    public static String getBukkitVersion() {
        if(version != null)
            return version;

        version = Bukkit.getMinecraftVersion().replaceAll("[.]", "");
        System.out.println("Raw version: " + Bukkit.getMinecraftVersion());
        System.out.println("Processed version: " + version);

        if (version.equals("1211")) {
            version = "121";
            System.out.println("Version modified to: " + version);
        }

        return version;
    }
}
