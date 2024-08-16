package poa.poaskrewritev2.util;

import org.bukkit.Bukkit;

public class BukkitVersion {

    private static String version;

    public static String getBukkitVersion() {
        if (version == null)
            version = Bukkit.getMinecraftVersion().replaceAll("[.]", "");

        if(version.equals("1211"))
            version = "121";

        return version;
    }
}
