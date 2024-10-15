package poa.packets.packetListener;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.CraftParticle;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import poa.packets.packetListener.events.*;
import poa.util.Components1206;

import java.lang.reflect.Method;
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
        try {
            if (!(msg instanceof Packet<?> packet)) {
                super.channelRead(ctx, msg);
                return;
            }

            if(packet instanceof ServerboundPlayerInputPacket inputPacket){
                final PlayerInputEvent1206 event = new PlayerInputEvent1206(player, true);
                event.setXxa(inputPacket.getXxa());
                event.setZza(inputPacket.getZza());
                event.setJumping(inputPacket.isJumping());
                event.setShiftKey(inputPacket.isShiftKeyDown());

                pluginManager.callEvent(event);
                if(event.isCancelled())
                    return;

            }



        }
        catch (Exception e){
            e.printStackTrace();
            super.channelRead(ctx,msg);
        }


        super.channelRead(ctx, msg);
    }


    private static Class<?> particleOptionsClass; //Some reason its not mapping correctly :D
    private static Method getTypeMethod;
    private static final Class<?> craftParticleClass;
    private static Method minecraftToBukkitMethod;

    static {
        try {
            craftParticleClass = Class.forName("org.bukkit.craftbukkit.CraftParticle");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static final PluginManager pluginManager = Bukkit.getPluginManager();


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        try {

            if (!(msg instanceof Packet<?> packet)) {
                super.write(ctx, msg, promise);
                return;
            }


            if (packet instanceof ClientboundSetEntityDataPacket metadata) {
                ServerPlayer serverPlayer = ((CraftPlayer) player).getHandle();

                ServerLevel level = serverPlayer.level().getMinecraftWorld();

                int entityId = metadata.id();
                Entity entity = level.getEntityLookup().get(entityId);

                if (entity == null) {
                    super.write(ctx, msg, promise);
                    return;
                }

                if (!(entity.getBukkitEntity() instanceof Player target)) {
                    super.write(ctx, msg, promise);
                    return;
                }


                if (target.isGlowing()) {
                    super.write(ctx, msg, promise);
                    return;

                }


                boolean glow = true;

                List<Integer> ids = GlowMap1206.glowMap.get(player);



                if (entityId != target.getEntityId()) {
                    super.write(ctx, msg, promise);
                    return;
                }



                if (ids == null)
                    glow = false;
                else if (ids.isEmpty())
                    glow = false;
                else if (!ids.contains(entityId))
                    glow = false;





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

            } else if (packet instanceof ClientboundLevelParticlesPacket particlesPacket) {
                ParticleOptions particle = particlesPacket.getParticle();
                if (particleOptionsClass == null) {
                    particleOptionsClass = particle.getClass();
                    getTypeMethod = particleOptionsClass.getDeclaredMethod("getType");
                }

                Object type;

                try {
                    type = getTypeMethod.invoke(particle);
                } catch (Exception ignored) {
                    super.write(ctx, msg, promise);
                    return;
                }


                if (minecraftToBukkitMethod == null) {
                    for (Method m : CraftParticle.class.getMethods()) {
                        if (m.getName().contains("minecraftToBukkit"))
                            minecraftToBukkitMethod = m;
                    }

                    if (minecraftToBukkitMethod == null) {
                        super.write(ctx, msg, promise);
                        System.out.println("ERROR, no method found. Report this :D");
                        return;
                    }
                }


                Object bukkitParticle = minecraftToBukkitMethod.invoke(craftParticleClass, type);

                // Particle bukkitParticle = CraftParticle.minecraftToBukkit((ParticleType<?>) type); //DOESN'T WORK :D


                ParticleEvent1206 particleEvent = new ParticleEvent1206(player, true);
                particleEvent.setParticle((Particle) bukkitParticle);

                particleEvent.setCount(particlesPacket.getCount());

                particleEvent.setWorld(player.getWorld());
                particleEvent.setX(particlesPacket.getX());
                particleEvent.setY(particlesPacket.getY());
                particleEvent.setY(particlesPacket.getY());

                particleEvent.setXOffset(particlesPacket.getXDist());
                particleEvent.setYOffset(particlesPacket.getYDist());
                particleEvent.setYOffset(particlesPacket.getYDist());

                particleEvent.setMaxSpeed(particlesPacket.getMaxSpeed());


                pluginManager.callEvent(particleEvent);


                if (particleEvent.isCancelled())
                    return;
            } else if (packet instanceof ClientboundSystemChatPacket chatPacket) {
                final Component component = Components1206.componentActual(chatPacket.content());


                final SystemChatPacketEvent1206 chatPacketEvent1206 = new SystemChatPacketEvent1206(player, true);

                chatPacketEvent1206.setOverlay(chatPacket.overlay());

                chatPacketEvent1206.setString(MiniMessage.miniMessage().serialize(component));

                pluginManager.callEvent(chatPacketEvent1206);

                if (chatPacketEvent1206.isCancelled())
                    return;
            } else if (packet instanceof ClientboundPlayerChatPacket chatPacket) {
                String message;
                if (chatPacket.unsignedContent() != null)
                    message = MiniMessage.miniMessage().serialize(Components1206.componentActual(chatPacket.unsignedContent()));
                else
                    message = chatPacket.body().content();


                final PlayerChatPacketEvent1206 playerChatPacketEvent1206 = new PlayerChatPacketEvent1206(player, true);

                playerChatPacketEvent1206.setString(message);

                playerChatPacketEvent1206.setSender(chatPacket.sender());

                pluginManager.callEvent(playerChatPacketEvent1206);

                if (playerChatPacketEvent1206.isCancelled())
                    return;

            }
            else if (packet instanceof ClientboundBlockUpdatePacket blockUpdatePacket){
                final BlockUpdateEvent1206 blockUpdateEvent1206 = new BlockUpdateEvent1206(player, true);
                final BlockState blockState = blockUpdatePacket.getBlockState();
                final CraftBlockData blockData = CraftBlockData.fromData(blockState);
                blockUpdateEvent1206.setBlockData(blockData);
                final BlockPos pos = blockUpdatePacket.getPos();
                blockUpdateEvent1206.setX(pos.getX());
                blockUpdateEvent1206.setY(pos.getY());
                blockUpdateEvent1206.setZ(pos.getZ());
                blockUpdateEvent1206.setOriginalBlock(blockUpdateEvent1206.getLocation().getBlock());

                pluginManager.callEvent(blockUpdateEvent1206);
                if(blockUpdateEvent1206.isCancelled())
                    return;

                if (blockUpdateEvent1206.getBlockData() != blockData) {
                    ClientboundBlockUpdatePacket newPacket = new ClientboundBlockUpdatePacket(pos, ((CraftBlockData) blockUpdateEvent1206.getBlockData()).getState());

                    super.write(ctx, newPacket, promise);
                    return;
                }

            }

            super.write(ctx, msg, promise);
        } catch (Exception e) {
            e.printStackTrace();
            super.write(ctx, msg, promise);
        }
    }


}
