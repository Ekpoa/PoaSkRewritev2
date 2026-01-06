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
            case "1216" -> Components1216.component(nmsComponent);
            case "1217" -> Components1217.component(nmsComponent);
            case "1218" -> Components1218.component(nmsComponent);
            case "1219" -> Components1219.component(nmsComponent);
            case "12110" -> Components12110.component(nmsComponent);
            case "12111" -> Components12111.component(nmsComponent);
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
            case "1216" -> Components1216.nmsComponent(component);
            case "1217" -> Components1217.nmsComponent(component);
            case "1218" -> Components1218.nmsComponent(component);
            case "1219" -> Components1219.nmsComponent(component);
            case "12110" -> Components12110.nmsComponent(component);
            case "12111" -> Components12111.nmsComponent(component);
            default -> null;
        };
    }
}