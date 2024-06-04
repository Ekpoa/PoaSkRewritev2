package poa.packets.packetListener;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class PacketHandler1206 extends ChannelDuplexHandler {

    //private List<ClientboundSystemChatPacket> list = new ArrayList<>();

    Player player;

    public PacketHandler1206(Player player) {
        this.player = player;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

            if(!(msg instanceof Packet<?> packet)){
                super.write(ctx, msg, promise);
                return;
            }


            if (packet instanceof ClientboundSetEntityDataPacket metadata) {
                ServerPlayer serverPlayer = ((CraftPlayer) player).getHandle();

                ServerLevel level = serverPlayer.level().getMinecraftWorld();

                int entityId = metadata.id();
                Entity entity = level.getEntityLookup().get(entityId);

                if(entity == null){
                    super.write(ctx, msg, promise);
                    return;
                }

                if (!(entity.getBukkitEntity() instanceof Player target)) {
                    super.write(ctx, msg, promise);
                    return;
                }

//            if (player.getName().equalsIgnoreCase("EllieeUwU")) {
//                Bukkit.broadcastMessage("is player");
//            }

                if (target.isGlowing()) {
                    super.write(ctx, msg, promise);
                    return;

                }


//            if (player.getName().equalsIgnoreCase("EllieeUwU")) {
//                Bukkit.broadcastMessage("is not glowing already");
//            }

                boolean glow = true;

                List<Integer> ids = GlowMap1206.glowMap.get(player);

//            if (player.getName().equalsIgnoreCase("EllieeUwU")) {
//                Bukkit.broadcastMessage(ids + "");
//            }

                if (entityId != target.getEntityId()) {
                    super.write(ctx, msg, promise);
                    return;
                }

//            if (player.getName().equalsIgnoreCase("EllieeUwU")) {
//                Bukkit.broadcastMessage("not target");
//            }

                if (ids == null)
                    glow = false;
                else if (ids.isEmpty())
                    glow = false;
                else if (!ids.contains(entityId))
                    glow = false;


//            if (player.getName().equalsIgnoreCase("EllieeUwU")) {
//                Bukkit.broadcastMessage(glow + "");
//            }


                var dataValues = new ArrayList<>(metadata.packedItems());
                if (glow && dataValues.stream()
                        .map(SynchedEntityData.DataValue::value)
                        .filter(Byte.class::isInstance)
                        .map(Byte.class::cast)
                        .noneMatch(aByte -> aByte == (byte) 0x40))

                    dataValues.add(new SynchedEntityData.DataValue<>(
                            0, EntityDataSerializers.BYTE, (byte) 0x40
                    ));

                else if (glow) dataValues.removeIf(data ->
                        data.value() instanceof Byte aByte && aByte == (byte) 0x40);

                ClientboundSetEntityDataPacket newPacket = new ClientboundSetEntityDataPacket(entityId, dataValues);

                super.write(ctx, newPacket, promise);
                return;

        }



        super.write(ctx, msg, promise);

    }
}
