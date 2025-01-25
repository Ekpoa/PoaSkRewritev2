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
import org.bukkit.craftbukkit.v1_20_R2.CraftParticle;
import org.bukkit.craftbukkit.v1_20_R2.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import poa.packets.packetListener.events.*;
import poa.util.Components1202;

import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;


public class PacketHandler1202 extends ChannelDuplexHandler {

    //private List<ClientboundSystemChatPacket> list = new ArrayList<>();

    Player player;

    public PacketHandler1202(Player player) {
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
                final PlayerInputEvent1202 event = new PlayerInputEvent1202(player, true);
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
            craftParticleClass = Class.forName("org.bukkit.craftbukkit.v1_20_R3.CraftParticle");
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
                assert player != null;
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

                List<Integer> ids = GlowMap1202.glowMap.get(player);



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


            if (packet instanceof ClientboundLevelParticlesPacket particlesPacket) {
                ParticleOptions particle = particlesPacket.getParticle();
                if (particleOptionsClass == null) {
                    particleOptionsClass = particle.getClass();
                    getTypeMethod = particleOptionsClass.getDeclaredMethod("b");
                }

                Object type;

                try {
                    type = getTypeMethod.invoke(particle);
                }
                catch (Exception ignored){
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
                        Bukkit.getLogger().log(Level.WARNING, "ERROR, no method found. Report this :D");
                        return;
                    }
                }


                Object bukkitParticle = minecraftToBukkitMethod.invoke(craftParticleClass, type);

                // Particle bukkitParticle = CraftParticle.minecraftToBukkit((ParticleType<?>) type); //DOESN'T WORK :D


                ParticleEvent1202 particleEvent = new ParticleEvent1202(player, true);
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
            }

            else if (packet instanceof ClientboundSystemChatPacket chatPacket) {
                final Component component = chatPacket.adventure$content();




                final SystemChatPacketEvent1202 chatPacketEvent1206 = new SystemChatPacketEvent1202(player, true);

                chatPacketEvent1206.setOverlay(chatPacket.overlay());

                if(component == null){
                    super.write(ctx, msg, promise);
                    return;
                }

                chatPacketEvent1206.setString(MiniMessage.miniMessage().serialize(component));

                pluginManager.callEvent(chatPacketEvent1206);

                if (chatPacketEvent1206.isCancelled())
                    return;
            } else if (packet instanceof ClientboundPlayerChatPacket chatPacket) {
                String message;
                if (chatPacket.unsignedContent() != null)
                    message = MiniMessage.miniMessage().serialize(Components1202.componentActual(chatPacket.unsignedContent()));
                else
                    message = chatPacket.body().content();


                final PlayerChatPacketEvent1202 playerChatPacketEvent1206 = new PlayerChatPacketEvent1202(player, true);

                playerChatPacketEvent1206.setString(message);

                playerChatPacketEvent1206.setSender(chatPacket.sender());

                pluginManager.callEvent(playerChatPacketEvent1206);

                if (playerChatPacketEvent1206.isCancelled())
                    return;

            }
            else if (packet instanceof ClientboundBlockUpdatePacket blockUpdatePacket){
                final BlockUpdateEvent1202 blockUpdateEvent1202 = new BlockUpdateEvent1202(player, true);
                final BlockState blockState = blockUpdatePacket.getBlockState();
                final CraftBlockData blockData = CraftBlockData.fromData(blockState);
                blockUpdateEvent1202.setBlockData(blockData);
                final BlockPos pos = blockUpdatePacket.getPos();
                blockUpdateEvent1202.setX(pos.getX());
                blockUpdateEvent1202.setY(pos.getY());
                blockUpdateEvent1202.setZ(pos.getZ());
                blockUpdateEvent1202.setOriginalBlock(blockUpdateEvent1202.getLocation().getBlock());
                blockUpdateEvent1202.setMaterial(blockUpdatePacket.getBlockState().getBukkitMaterial());
                pluginManager.callEvent(blockUpdateEvent1202);
                if(blockUpdateEvent1202.isCancelled())
                    return;

                if (blockUpdateEvent1202.getBlockData() != blockData) {
                    ClientboundBlockUpdatePacket newPacket = new ClientboundBlockUpdatePacket(pos, ((CraftBlockData) blockUpdateEvent1202.getBlockData()).getState());

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
