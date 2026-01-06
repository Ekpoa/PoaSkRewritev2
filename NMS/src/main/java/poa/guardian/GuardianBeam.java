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
    GuardianBeam1213 beam1213;
    GuardianBeam1214 beam1214;
    GuardianBeam1215 beam1215;
    GuardianBeam1216 beam1216;
    GuardianBeam1217 beam1217;
    GuardianBeam1218 beam1218;
    GuardianBeam1219 beam1219;
    GuardianBeam12110 beam12110;
    GuardianBeam12111 beam12111;

    public GuardianBeam(List<Player> players, String id, Location startLoc, Location endLoc, String color, Plugin plugin) {
        this.id = id.toLowerCase();
        if (dataMap.containsKey(id)) {
            plugin.getLogger().log(Level.WARNING, id + " beam already exists");
            return;
        }

        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> beam1202 = new GuardianBeam1202(players, id, startLoc, endLoc, color, plugin);
            case "1204" -> beam1204 = new GuardianBeam1204(players, id, startLoc, endLoc, color, plugin);
            case "1206" -> beam1206 = new GuardianBeam1206(players, id, startLoc, endLoc, color, plugin);
            case "121" -> beam121 = new GuardianBeam121(players, id, startLoc, endLoc, color, plugin);
            case "1211" -> beam1211 = new GuardianBeam1211(players, id, startLoc, endLoc, color, plugin);
            case "1213" -> beam1213 = new GuardianBeam1213(players, id, startLoc, endLoc, color, plugin);
            case "1214" -> beam1214 = new GuardianBeam1214(players, id, startLoc, endLoc, color, plugin);
            case "1215" -> beam1215 = new GuardianBeam1215(players, id, startLoc, endLoc, color, plugin);
            case "1216" -> beam1216 = new GuardianBeam1216(players, id, startLoc, endLoc, color, plugin);
            case "1217" -> beam1217 = new GuardianBeam1217(players, id, startLoc, endLoc, color, plugin);
            case "1218" -> beam1218 = new GuardianBeam1218(players, id, startLoc, endLoc, color, plugin);
            case "1219" -> beam1219 = new GuardianBeam1219(players, id, startLoc, endLoc, color, plugin);
            case "12110" -> beam12110 = new GuardianBeam12110(players, id, startLoc, endLoc, color, plugin);
            case "12111" -> beam12111 = new GuardianBeam12111(players, id, startLoc, endLoc, color, plugin);
        }

        dataMap.put(this.id, this);
    }

    public void loop() {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> beam1202.loop();
            case "1204" -> beam1204.loop();
            case "1206" -> beam1206.loop();
            case "121" -> beam121.loop();
            case "1211" -> beam1211.loop();
            case "1213" -> beam1213.loop();
            case "1214" -> beam1214.loop();
            case "1215" -> beam1215.loop();
            case "1216" -> beam1216.loop();
            case "1217" -> beam1217.loop();
            case "1218" -> beam1218.loop();
            case "1219" -> beam1219.loop();
            case "12110" -> beam12110.loop();
            case "12111" -> beam12111.loop();
        }
    }

    public void destroy() {
        switch (BukkitVersion.getBukkitVersion()) {
            case "1202" -> beam1202.destroy();
            case "1204" -> beam1204.destroy();
            case "1206" -> beam1206.destroy();
            case "121" -> beam121.destroy();
            case "1211" -> beam1211.destroy();
            case "1213" -> beam1213.destroy();
            case "1214" -> beam1214.destroy();
            case "1215" -> beam1215.destroy();
            case "1216" -> beam1216.destroy();
            case "1217" -> beam1217.destroy();
            case "1218" -> beam1218.destroy();
            case "1219" -> beam1219.destroy();
            case "12110" -> beam12110.destroy();
            case "12111" -> beam12111.destroy();
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
