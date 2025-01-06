package poa.packets;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.ChatFormatting;
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Team;
import poa.util.Components1214;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Optional;

public class TeamPacket1214 {


    public static Object teamPacket(String teamName, String displayName, String nameTagVisibility, String collision, String color, String prefix, String suffix, Collection<String> players) {
        try {
            Class<ClientboundSetPlayerTeamPacket> clazz = ClientboundSetPlayerTeamPacket.class;

            Constructor<ClientboundSetPlayerTeamPacket> constructor = clazz.getDeclaredConstructor(String.class, int.class, Optional.class, Collection.class);

            constructor.setAccessible(true);

            PlayerTeam playerTeam = new PlayerTeam(new Scoreboard(), teamName);

            playerTeam.setColor(ChatFormatting.valueOf(color.toUpperCase()));
            playerTeam.setPlayerPrefix(Components1214.nmsComponentActual(MiniMessage.miniMessage().deserialize(prefix)));
            playerTeam.setPlayerSuffix(Components1214.nmsComponentActual(MiniMessage.miniMessage().deserialize(suffix)));

            playerTeam.setCollisionRule(Team.CollisionRule.valueOf(collision.toUpperCase()));

            playerTeam.setNameTagVisibility(Team.Visibility.valueOf(nameTagVisibility.toUpperCase()));



            playerTeam.setDisplayName(Components1214.nmsComponentActual(MiniMessage.miniMessage().deserialize(displayName)));

            Optional<ClientboundSetPlayerTeamPacket.Parameters> parameters = Optional.of(new ClientboundSetPlayerTeamPacket.Parameters(playerTeam));


            return constructor.newInstance(teamName, 0, parameters, players);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Object teamPacketForGlow(String teamName, String color, Collection<String> players) {
        return teamPacket(teamName, "", "always", "always", color, "", "", players);
    }

    public static Object teamPacketForBeam(Collection<String> entities, String color) {
        return teamPacket("noclipG", "", "never", "never", color, "", "", entities);
    }

}
