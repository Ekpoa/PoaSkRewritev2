package poa.util;

import org.bukkit.entity.Player;
import poa.packetListener.GlowMap1202;
import poa.packetListener.GlowMap1204;
import poa.packetListener.GlowMap1206;

import java.util.List;
import java.util.Map;

public class GlowMap {



    public static Map<Player, List<Integer>> getGlowMap(){
        return switch (BukkitVersion.getBukkitVersion()){
            case "1206" -> GlowMap1206.getGlowMap();
            case "1204" -> GlowMap1204.getGlowMap();
            case "1202" -> GlowMap1202.getGlowMap();
            default -> null;
        };
    }


}
