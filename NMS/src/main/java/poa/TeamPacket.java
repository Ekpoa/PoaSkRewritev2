package poa;

import poa.util.BukkitVersion;

import java.util.Collection;

public class TeamPacket {


    public static Object teamPacket(String teamName, String displayName, String nameTagVisibility, String collision, String color, String prefix, String suffix, Collection<String> players) {
        return switch (BukkitVersion.getBukkitVersion()){
            case "1202" -> TeamPacket1202.teamPacket(teamName, displayName, nameTagVisibility, collision, color, prefix, suffix, players);
            case "1204" -> TeamPacket1204.teamPacket(teamName, displayName, nameTagVisibility, collision, color, prefix, suffix, players);
            case "1206" -> TeamPacket1206.teamPacket(teamName, displayName, nameTagVisibility, collision, color, prefix, suffix, players);
            default -> null;
        };
    }

    public static Object teamPacketForGlow(String teamName, String color, Collection<String> players) {
        return teamPacket(teamName, "", "always", "always", color, "", "", players);
    }


}
