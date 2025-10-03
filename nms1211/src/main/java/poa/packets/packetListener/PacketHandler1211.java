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
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.CraftParticle;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import poa.packets.packetListener.events.*;
import poa.util.Components1211;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class PacketHandler1211 extends ChannelDuplexHandler {

    //private List<ClientboundSystemChatPacket> list = new ArrayList<>();

    Player player;


    public PacketHandler1211(Player player) {
        this.player = player;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (!(msg instanceof Packet<?> packet)) {
                super.channelRead(ctx, msg);
                return;
            }
            if(packet instanceof ServerboundPlayerActionPacket actionPacket){
                final BlockPos pos = actionPacket.getPos();
                final ServerboundPlayerActionPacket.Action action = actionPacket.getAction();
                final PlayerActionEvent1211 event = new PlayerActionEvent1211(player, true);


                final Location location = new Location(player.getWorld(), pos.getX(), pos.getY(), pos.getZ());
                event.setLocation(location);
                event.setSequence(actionPacket.getSequence());
                event.setBlock(location.getBlock());
                event.setAction(action);
                event.setActionString(action.toString());

                Bukkit.getPluginManager().callEvent(event);

                if(event.isCancelled())
                    return;

            }

            if(packet instanceof ServerboundPlayerInputPacket inputPacket){
                final PlayerInputEvent1211 event = new PlayerInputEvent1211(player, true);
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

                List<Integer> ids = GlowMap1211.glowMap.get(player);



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

                else if (!glow) dataValues.removeIf(data ->
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
                        Bukkit.getLogger().log(Level.WARNING, "ERROR, no method found. Report this :D");
                        return;
                    }
                }


                Object bukkitParticle = minecraftToBukkitMethod.invoke(craftParticleClass, type);

                // Particle bukkitParticle = CraftParticle.minecraftToBukkit((ParticleType<?>) type); //DOESN'T WORK :D


                ParticleEvent1211 particleEvent = new ParticleEvent1211(player, true);
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
                final Component component = Components1211.componentActual(chatPacket.content());


                final SystemChatPacketEvent1211 chatPacketEvent1206 = new SystemChatPacketEvent1211(player, true);

                chatPacketEvent1206.setOverlay(chatPacket.overlay());

                chatPacketEvent1206.setString(MiniMessage.miniMessage().serialize(component));

                pluginManager.callEvent(chatPacketEvent1206);

                if (chatPacketEvent1206.isCancelled())
                    return;
            } else if (packet instanceof ClientboundPlayerChatPacket chatPacket) {
                String message;
                if (chatPacket.unsignedContent() != null)
                    message = MiniMessage.miniMessage().serialize(Components1211.componentActual(chatPacket.unsignedContent()));
                else
                    message = chatPacket.body().content();


                final PlayerChatPacketEvent1211 playerChatPacketEvent121 = new PlayerChatPacketEvent1211(player, true);

                playerChatPacketEvent121.setString(message);

                playerChatPacketEvent121.setSender(chatPacket.sender());

                pluginManager.callEvent(playerChatPacketEvent121);

                if (playerChatPacketEvent121.isCancelled())
                    return;

            }

            else if (packet instanceof ClientboundBlockUpdatePacket blockUpdatePacket){
                final BlockUpdateEvent1211 blockUpdateEvent1211 = new BlockUpdateEvent1211(player, true);
                final BlockState blockState = blockUpdatePacket.getBlockState();
                final CraftBlockData blockData = CraftBlockData.fromData(blockState);
                blockUpdateEvent1211.setBlockData(blockData);
                final BlockPos pos = blockUpdatePacket.getPos();
                blockUpdateEvent1211.setX(pos.getX());
                blockUpdateEvent1211.setY(pos.getY());
                blockUpdateEvent1211.setZ(pos.getZ());
                blockUpdateEvent1211.setMaterial(blockUpdatePacket.getBlockState().getBukkitMaterial());
                blockUpdateEvent1211.setOriginalBlock(blockUpdateEvent1211.getLocation().getBlock());

                pluginManager.callEvent(blockUpdateEvent1211);
                if(blockUpdateEvent1211.isCancelled())
                    return;

                if (blockUpdateEvent1211.getBlockData() != blockData) {
                    ClientboundBlockUpdatePacket newPacket = new ClientboundBlockUpdatePacket(pos, ((CraftBlockData) blockUpdateEvent1211.getBlockData()).getState());

                    super.write(ctx, newPacket, promise);
                    return;
                }
            }

            else if(packet instanceof ClientboundSoundPacket soundPacket){
                final SoundEvent1211 soundEvent1211 = new SoundEvent1211(player, true);
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
                final SoundEvent1211 soundEvent1211 = new SoundEvent1211(player, true);
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
