package poa.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundSetCameraPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Spider;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import poa.util.RefreshPlayer121;

public class ChangeVision121 {

    public static void changeVision(Player player, String entityType) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        final ServerPlayer serverPlayer = craftPlayer.getHandle();

        LivingEntity entity = null;

        switch (entityType.toLowerCase()) {
            case "creeper" -> entity = new Creeper(EntityType.CREEPER, ((CraftWorld) player.getWorld()).getHandle());
            case "spider" -> entity = new Spider(EntityType.SPIDER, ((CraftWorld) player.getWorld()).getHandle());
            case "enderman" -> entity = new EnderMan(EntityType.ENDERMAN, ((CraftWorld) player.getWorld()).getHandle());
            case "cave_spider" ->
                    entity = new CaveSpider(EntityType.CAVE_SPIDER, ((CraftWorld) player.getWorld()).getHandle());
            case "player" -> serverPlayer.connection.send(new ClientboundSetCameraPacket(serverPlayer));
        }


        if (entity == null) {
            System.out.println("Entity must be a creeper, enderman, spider, cavespider or player for setting vision");
            return;
        }

        serverPlayer.connection.send(new ClientboundAddEntityPacket(entity, 0, BlockPos.ZERO));
        serverPlayer.connection.send(new ClientboundSetCameraPacket(entity));
        RefreshPlayer121.refreshPlayer(player);
    }
}
