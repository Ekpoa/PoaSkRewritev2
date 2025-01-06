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
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.craftbukkit.block.CraftBlockState;
import org.bukkit.craftbukkit.block.CraftBlockStates;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import poa.packets.packetListener.events.*;
import poa.util.Components121;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class PacketHandler121 extends ChannelDuplexHandler {

    //private List<ClientboundSystemChatPacket> list = new ArrayList<>();

    Player player;


    public PacketHandler121(Player player) {
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
                final PlayerInputEvent121 event = new PlayerInputEvent121(player, true);
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


    private static Class<?> particleOptionsClass; //Some reason it's not mapping correctly :D
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
                Entity entity = level.moonrise$getEntityLookup().get(entityId);

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

                List<Integer> ids = GlowMap121.glowMap.get(player);



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
                } catch (Exception e) {
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


                ParticleEvent121 particleEvent = new ParticleEvent121(player, true);
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
                final Component component = Components121.componentActual(chatPacket.content());


                final SystemChatPacketEvent121 chatPacketEvent1206 = new SystemChatPacketEvent121(player, true);

                chatPacketEvent1206.setOverlay(chatPacket.overlay());

                chatPacketEvent1206.setString(MiniMessage.miniMessage().serialize(component));

                pluginManager.callEvent(chatPacketEvent1206);

                if (chatPacketEvent1206.isCancelled())
                    return;
            } else if (packet instanceof ClientboundPlayerChatPacket chatPacket) {
                String message;
                if (chatPacket.unsignedContent() != null)
                    message = MiniMessage.miniMessage().serialize(Components121.componentActual(chatPacket.unsignedContent()));
                else
                    message = chatPacket.body().content();


                final PlayerChatPacketEvent121 playerChatPacketEvent121 = new PlayerChatPacketEvent121(player, true);

                playerChatPacketEvent121.setString(message);

                playerChatPacketEvent121.setSender(chatPacket.sender());

                pluginManager.callEvent(playerChatPacketEvent121);

                if (playerChatPacketEvent121.isCancelled())
                    return;

            }

            else if (packet instanceof ClientboundBlockUpdatePacket blockUpdatePacket){
                final BlockUpdateEvent121 blockUpdateEvent121 = new BlockUpdateEvent121(player, true);
                final BlockState blockState = blockUpdatePacket.getBlockState();
                final CraftBlockData blockData = CraftBlockData.fromData(blockState);
                blockUpdateEvent121.setBlockData(blockData);
                final BlockPos pos = blockUpdatePacket.getPos();
                blockUpdateEvent121.setX(pos.getX());
                blockUpdateEvent121.setY(pos.getY());
                blockUpdateEvent121.setZ(pos.getZ());
                blockUpdateEvent121.setOriginalBlock(blockUpdateEvent121.getLocation().getBlock());
                blockUpdateEvent121.setMaterial(blockUpdatePacket.getBlockState().getBukkitMaterial());

                pluginManager.callEvent(blockUpdateEvent121);
                if(blockUpdateEvent121.isCancelled())
                    return;

                if (blockUpdateEvent121.getBlockData() != blockData) {
                    ClientboundBlockUpdatePacket newPacket = new ClientboundBlockUpdatePacket(pos, ((CraftBlockData) blockUpdateEvent121.getBlockData()).getState());

                    super.write(ctx, newPacket, promise);
                    return;
                }
            }
            else if(packet instanceof ClientboundSoundPacket soundPacket){
                final SoundEvent121 soundEvent1211 = new SoundEvent121(player, true);
                soundEvent1211.setX(soundPacket.getX());
                soundEvent1211.setY(soundPacket.getY());
                soundEvent1211.setZ(soundPacket.getZ());
                soundEvent1211.setVolume(soundPacket.getVolume());
                soundEvent1211.setPitch(soundPacket.getPitch());
                soundEvent1211.setSeed(soundPacket.getSeed());
                soundEvent1211.setId(soundPacket.getSound().value().getLocation().toString());
                soundEvent1211.setSource(soundPacket.getSource().getName());
                pluginManager.callEvent(soundEvent1211);

                if(soundEvent1211.isCancelled())
                    return;

            }
            else if(packet instanceof ClientboundSoundEntityPacket soundPacket){
                final SoundEvent121 soundEvent1211 = new SoundEvent121(player, true);
                soundEvent1211.setEntity(true);
                soundEvent1211.setVolume(soundPacket.getVolume());
                soundEvent1211.setPitch(soundPacket.getPitch());
                soundEvent1211.setSeed(soundPacket.getSeed());
                soundEvent1211.setId(soundPacket.getSound().value().getLocation().toString());;
                soundEvent1211.setSource(soundPacket.getSource().getName());
                pluginManager.callEvent(soundEvent1211);

                if(soundEvent1211.isCancelled())
                    return;
            }



            super.write(ctx, msg, promise);
        } catch (Exception e) {
            e.printStackTrace();
            super.write(ctx, msg, promise);
        }
    }


}
