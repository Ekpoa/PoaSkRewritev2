package poa.packets;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.ChatFormatting;
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerScoreboard;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Team;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;
import poa.util.Components12110;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Optional;

public class TeamPacket12110 {


    public static Object teamPacket(String teamName, String displayName, String nameTagVisibility, String collision, String color, String prefix, String suffix, boolean seeFriendly, Collection<String> players) {
        try {
            CraftServer craftServer = (CraftServer) Bukkit.getServer();
            ServerLevel nmsLevel = craftServer.getServer().overworld();
            Scoreboard scoreboard = nmsLevel.getScoreboard();
            PlayerTeam playerTeam = null;
            if (scoreboard.getTeamNames().contains(teamName))
                playerTeam = scoreboard.getPlayerTeam(teamName);

            if (playerTeam == null) {
                playerTeam = new PlayerTeam(scoreboard, teamName);
                scoreboard.addPlayerTeam(teamName);
            }

            playerTeam.setColor(ChatFormatting.valueOf(color.toUpperCase()));
            playerTeam.setPlayerPrefix(Components12110.nmsComponentActual(MiniMessage.miniMessage().deserialize(prefix)));
            playerTeam.setPlayerSuffix(Components12110.nmsComponentActual(MiniMessage.miniMessage().deserialize(suffix)));

            playerTeam.setCollisionRule(Team.CollisionRule.valueOf(collision.toUpperCase()));

            playerTeam.setNameTagVisibility(Team.Visibility.valueOf(nameTagVisibility.toUpperCase()));

            playerTeam.setSeeFriendlyInvisibles(seeFriendly);

            playerTeam.setDisplayName(Components12110.nmsComponentActual(MiniMessage.miniMessage().deserialize(displayName)));

            playerTeam.getPlayers().addAll(players);

            return ClientboundSetPlayerTeamPacket.createAddOrModifyPacket(playerTeam, true);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object teamPacket(String teamName, String displayName, String nameTagVisibility, String collision, String color, String prefix, String suffix, Collection<String> players) {
        return teamPacket(teamName, displayName, nameTagVisibility, collision, color, prefix, suffix, false, players);
    }

    public static Object teamPacketForGlow(String teamName, String color, Collection<String> players) {
        return teamPacket(teamName, "", "always", "always", color, "", "", players);
    }

    public static Object teamPacketForBeam(Collection<String> entities, String color) {
        return teamPacket("noclipG", "", "never", "never", color, "", "", entities);
    }


}
