package poa.packetListener;

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
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;


public class PacketHandler1204 extends ChannelDuplexHandler {

    //private List<ClientboundSystemChatPacket> list = new ArrayList<>();


    public PacketHandler1204() {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        String name = ctx.name().replace("-PoaSK-", "");


        Player player = Bukkit.getPlayer(name);


        Packet<?> packet = (Packet<?>) msg;


        if (packet instanceof ClientboundSetEntityDataPacket metadata) {
            ServerPlayer serverPlayer = ((CraftPlayer) player).getHandle();

            ServerLevel level = serverPlayer.level().getMinecraftWorld();

            int entityId = metadata.id();
            Entity entity = level.getEntityLookup().get(entityId);

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

            List<Integer> ids = GlowMap1204.glowMap.get(player);

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


            List<SynchedEntityData.DataValue<?>> packedItems = metadata.packedItems();

            for (int i = 0; i < packedItems.size(); i++) {
                SynchedEntityData.DataValue<?> dataValue = packedItems.get(i);

                if (dataValue.id() != 0)
                    continue;

                byte value = (byte) dataValue.value();

                if (glow) {
                    if (((value & 0x40) == 0)) {
                        value |= 0x40;
                    }
                } else {
                    if (((value & 0x40) != 0)) {
                        value &= ~0x40;
                    }
                }

                packedItems.set(i, new SynchedEntityData.DataValue<>(0, EntityDataSerializers.BYTE, value));

                msg = new ClientboundSetEntityDataPacket(entityId, packedItems);

//                if (player.getName().equalsIgnoreCase("EllieeUwU")) {
//                    Bukkit.broadcastMessage("updated packet");
//                }
                break;
            }

        }


        super.write(ctx, msg, promise);
    }
}
