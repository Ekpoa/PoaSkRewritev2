package poa.util;

import org.bukkit.plugin.Plugin;

public class PoaPlugin {

    public static void setPlugin(Plugin plugin){
         switch (BukkitVersion.getBukkitVersion()){
             case "121" -> PoaPlugin121.setPlugin(plugin);
             case "1211" -> PoaPlugin1211.setPlugin(plugin);
             case "1213" -> PoaPlugin1213.setPlugin(plugin);
            case "1214" -> PoaPlugin1214.setPlugin(plugin);
             case "1215" -> PoaPlugin1215.setPlugin(plugin);
             case "1216" -> PoaPlugin1216.setPlugin(plugin);
             case "1217" -> PoaPlugin1217.setPlugin(plugin);
             case "1218" -> PoaPlugin1218.setPlugin(plugin);
        }
    }

}
