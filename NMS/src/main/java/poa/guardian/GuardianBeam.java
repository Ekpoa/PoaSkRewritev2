package poa.guardian;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import poa.util.BukkitVersion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class GuardianBeam {

    public static Map<String, GuardianBeam> dataMap = new HashMap<>();

    public static GuardianBeam getBeam(String id){
        return dataMap.getOrDefault(id, null);
    }

    String id;

    GuardianBeam1202 beam1202;
    GuardianBeam1204 beam1204;
    GuardianBeam1206 beam1206;
    GuardianBeam121 beam121;
    GuardianBeam1211 beam1211;

    public GuardianBeam(List<Player> players, String id, Location startLoc, Location endLoc, String color, Plugin plugin){
        this.id = id.toLowerCase();
        if(dataMap.containsKey(id)){
            plugin.getLogger().log(Level.WARNING, id + " beam already exists");
            return;
        }

        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> beam1202 = new GuardianBeam1202(players, id, startLoc, endLoc, color, plugin);
            case "1204" -> beam1204 = new GuardianBeam1204(players, id, startLoc, endLoc, color, plugin);
            case "1206" -> beam1206 = new GuardianBeam1206(players, id, startLoc, endLoc, color, plugin);
            case "121" -> beam121 = new GuardianBeam121(players, id, startLoc, endLoc, color, plugin);
            case "1211" -> beam1211 = new GuardianBeam1211(players, id, startLoc, endLoc, color, plugin);
        }

        dataMap.put(this.id, this);
    }

    public void loop(){
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> beam1202.loop();
            case "1204" -> beam1204.loop();
            case "1206" -> beam1206.loop();
            case "121" -> beam121.loop();
            case "1211" -> beam1211.loop();
        }
    }

    public void destroy(){
        switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> beam1202.destroy();
            case "1204" -> beam1204.destroy();
            case "1206" -> beam1206.destroy();
            case "121" -> beam121.destroy();
            case "1211" -> beam121.destroy();
        }
        dataMap.remove(this.id);
    }


//    public GuardianBeam(List<Player> players, String id, Location startLoc, Location endLoc, String color, Plugin plugin) {
//        return switch (BukkitVersion.getBukkitVersion()){
//            case "1202" -> new GuardianBeam1202(players, id, startLoc, endLoc, color, plugin);
//            case "1204" -> new GuardianBeam1204(players, id, startLoc, endLoc, color, plugin);
//            case "1206" -> new GuardianBeam1206(players, id, startLoc, endLoc, color, plugin);
//            case "121" -> new GuardianBeam121(players, id, startLoc, endLoc, color, plugin);
//            default -> null;
//        };
//    }

}
