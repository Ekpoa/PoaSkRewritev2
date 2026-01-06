package poa.util;

import net.minecraft.network.protocol.game.ClientboundCooldownPacket;
import net.minecraft.network.protocol.game.ClientboundRespawnPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket;
import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.phys.AABB;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Map;

public class RefreshPlayer12110 {
    public static void refreshPlayer(Player player) {
        ServerPlayer serverPlayer = ((CraftPlayer) player).getHandle();
        ServerLevel serverLevel = serverPlayer.level();
        serverPlayer.connection.send(new ClientboundRespawnPacket(serverPlayer.createCommonSpawnInfo(serverLevel), ClientboundRespawnPacket.KEEP_ALL_DATA));
        final Location location = player.getLocation();
        serverPlayer.connection.teleport(location.x(), location.y(), location.z(), location.getPitch(), location.getYaw());

        if (serverPlayer.isPassenger())
            serverPlayer.connection.send(new ClientboundSetPassengersPacket(serverPlayer.getVehicle()));

        if (serverPlayer.isVehicle())
            serverPlayer.connection.send(new ClientboundSetPassengersPacket(serverPlayer));

        AABB boundingBox = new AABB(serverPlayer.position(), serverPlayer.position()).inflate(10);
        for (Mob nmsMob : serverLevel.getEntitiesOfClass(Mob.class, boundingBox, nmsMob -> serverPlayer.equals(nmsMob.getLeashHolder())))
            serverPlayer.connection.send(new ClientboundSetEntityLinkPacket(nmsMob, serverPlayer));


        if (!serverPlayer.getCooldowns().cooldowns.isEmpty()) {
            int tickCount = serverPlayer.getCooldowns().tickCount;
            for (Map.Entry<ResourceLocation, ItemCooldowns.CooldownInstance> entry : serverPlayer.getCooldowns().cooldowns.entrySet()) {
                serverPlayer.connection.send(new ClientboundCooldownPacket(entry.getKey(), entry.getValue().endTime() - tickCount));
            }
        }


        serverPlayer.onUpdateAbilities();
        final MinecraftServer server = MinecraftServer.getServer();
        if(server == null)
            throw new RuntimeException("Server null for player");


        server.getPlayerList().sendPlayerPermissionLevel(serverPlayer);
        server.getPlayerList().sendLevelInfo(serverPlayer, serverLevel);
        server.getPlayerList().sendAllPlayerInfo(serverPlayer);
    }
}
