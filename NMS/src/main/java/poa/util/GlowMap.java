package poa.util;

import org.bukkit.entity.Player;
import poa.packets.packetListener.*;

import java.util.List;
import java.util.Map;

public class GlowMap {



    public static Map<Player, List<Integer>> getGlowMap(){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1214" -> GlowMap1214.getGlowMap();
            case "1213" -> GlowMap1213.getGlowMap();
            case "1211" -> GlowMap1211.getGlowMap();
            case "121" -> GlowMap121.getGlowMap();
            case "1206" -> GlowMap1206.getGlowMap();
            case "1204" -> GlowMap1204.getGlowMap();
            case "1202" -> GlowMap1202.getGlowMap();
            default -> null;
        };
    }


}
