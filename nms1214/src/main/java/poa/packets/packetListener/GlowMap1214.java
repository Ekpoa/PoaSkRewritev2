package poa.packets.packetListener;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GlowMap1214 {


    @Getter
    public static ConcurrentHashMap<Player, List<Integer>> glowMap = new ConcurrentHashMap<>();//player -> target    removal handled in login


}
