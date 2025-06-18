package poa.util;


import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import poa.util.BukkitVersion;
import poa.util.Components1202;
import poa.util.Components1204;
import poa.util.Components1206;

public class Components {



    public static Component paperComponent(Object nmsComponent){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> Components1202.component(nmsComponent);
            case "1204" -> Components1204.component(nmsComponent);
            case "1206" -> Components1206.component(nmsComponent);
            case "121" -> Components121.component(nmsComponent);
            case "1211" -> Components1211.component(nmsComponent);
            case "1213" -> Components1213.component(nmsComponent);
            case "1214" -> Components1214.component(nmsComponent);
            case "1215" -> Components1215.component(nmsComponent);
            default -> null;
        };
    }

    public static Object nmsComponent(Component component){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> Components1202.nmsComponent(component);
            case "1204" -> Components1204.nmsComponent(component);
            case "1206" -> Components1206.nmsComponent(component);
            case "121" -> Components121.nmsComponent(component);
            case "1211" -> Components1211.nmsComponent(component);
            case "1213" -> Components1213.nmsComponent(component);
            case "1214" -> Components1214.nmsComponent(component);
            case "1215" -> Components1215.nmsComponent(component);
            default -> null;
        };
    }
}