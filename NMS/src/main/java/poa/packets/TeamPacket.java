package poa.packets;

import poa.packets.TeamPacket1202;
import poa.packets.TeamPacket1204;
import poa.packets.TeamPacket1206;
import poa.util.BukkitVersion;

import java.util.Collection;

public class TeamPacket {


    public static Object teamPacket(String teamName, String displayName, String nameTagVisibility, String collision, String color, String prefix, String suffix, boolean seeFriendly, Collection<String> players) {
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> TeamPacket1202.teamPacket(teamName, displayName, nameTagVisibility, collision, color, prefix, suffix, seeFriendly, players);
            case "1204" -> TeamPacket1204.teamPacket(teamName, displayName, nameTagVisibility, collision, color, prefix, suffix, seeFriendly, players);
            case "1206" -> TeamPacket1206.teamPacket(teamName, displayName, nameTagVisibility, collision, color, prefix, suffix, seeFriendly, players);
            case "121" -> TeamPacket121.teamPacket(teamName, displayName, nameTagVisibility, collision, color, prefix, suffix, seeFriendly, players);
            case "1211" -> TeamPacket1211.teamPacket(teamName, displayName, nameTagVisibility, collision, color, prefix, suffix, seeFriendly, players);
            case "1213" -> TeamPacket1213.teamPacket(teamName, displayName, nameTagVisibility, collision, color, prefix, suffix, seeFriendly, players);
            case "1214" -> TeamPacket1214.teamPacket(teamName, displayName, nameTagVisibility, collision, color, prefix, suffix, seeFriendly, players);
            default -> null;
        };
    }

    public static Object teamPacket(String teamName, String displayName, String nameTagVisibility, String collision, String color, String prefix, String suffix, Collection<String> players){
        return teamPacket(teamName, displayName, nameTagVisibility, collision, color, prefix,suffix, true, players);
    }

    public static Object teamPacketForGlow(String teamName, String color, Collection<String> players) {
        return teamPacket(teamName, "", "always", "always", color, "", "", players);
    }


}
