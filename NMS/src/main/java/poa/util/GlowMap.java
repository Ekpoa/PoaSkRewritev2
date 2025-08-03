package poa.util;

import org.bukkit.entity.Player;
import poa.packets.packetListener.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GlowMap {



    public static ConcurrentHashMap<Player, List<Integer>> getGlowMap(){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1215" -> GlowMap1215.getGlowMap();
            case "1216" -> GlowMap1216.getGlowMap();
            case "1217" -> GlowMap1217.getGlowMap();
            case "1218" -> GlowMap1218.getGlowMap();
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
