package poa.util;

import org.bukkit.entity.Player;
import poa.packets.packetListener.GlowMap1202;
import poa.packets.packetListener.GlowMap1204;
import poa.packets.packetListener.GlowMap1206;
import poa.packets.packetListener.GlowMap121;

import java.util.List;
import java.util.Map;

public class GlowMap {



    public static Map<Player, List<Integer>> getGlowMap(){
        return switch (BukkitVersion.getBukkitVersion()){
            case "121" -> GlowMap121.getGlowMap();
            case "1206" -> GlowMap1206.getGlowMap();
            case "1204" -> GlowMap1204.getGlowMap();
            case "1202" -> GlowMap1202.getGlowMap();
            default -> null;
        };
    }


}
