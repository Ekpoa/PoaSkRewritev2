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
import poa.util.Components1214;

import java.awt.event.FocusEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;


public class PacketHandler1214 extends ChannelDuplexHandler {

    //private List<ClientboundSystemChatPacket> list = new ArrayList<>();

    Player player;


    public PacketHandler1214(Player player) {
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
                final PlayerActionEvent1214 event = new PlayerActionEvent1214(player, true);


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


        } catch (Exception e) {
            e.printStackTrace();
            super.channelRead(ctx, msg);
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

                List<Integer> ids = GlowMap1214.glowMap.get(player);

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


                ParticleEvent1214 particleEvent = new ParticleEvent1214(player, true);
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
                final Component component = Components1214.componentActual(chatPacket.content());


                final SystemChatPacketEvent1214 chatPacketEvent1206 = new SystemChatPacketEvent1214(player, true);

                chatPacketEvent1206.setOverlay(chatPacket.overlay());

                chatPacketEvent1206.setString(MiniMessage.miniMessage().serialize(component));

                pluginManager.callEvent(chatPacketEvent1206);

                if (chatPacketEvent1206.isCancelled())
                    return;
            } else if (packet instanceof ClientboundPlayerChatPacket chatPacket) {
                String message;
                if (chatPacket.unsignedContent() != null)
                    message = MiniMessage.miniMessage().serialize(Components1214.componentActual(chatPacket.unsignedContent()));
                else
                    message = chatPacket.body().content();


                final PlayerChatPacketEvent1214 playerChatPacketEvent121 = new PlayerChatPacketEvent1214(player, true);

                playerChatPacketEvent121.setString(message);

                playerChatPacketEvent121.setSender(chatPacket.sender());

                pluginManager.callEvent(playerChatPacketEvent121);

                if (playerChatPacketEvent121.isCancelled())
                    return;

            } else if (packet instanceof ClientboundBlockUpdatePacket blockUpdatePacket) {
                final BlockUpdateEvent1214 blockUpdateEvent1214 = new BlockUpdateEvent1214(player, true);
                final BlockState blockState = blockUpdatePacket.getBlockState();
                final CraftBlockData blockData = CraftBlockData.fromData(blockState);
                blockUpdateEvent1214.setBlockData(blockData);
                final BlockPos pos = blockUpdatePacket.getPos();
                blockUpdateEvent1214.setX(pos.getX());
                blockUpdateEvent1214.setMaterial(blockUpdatePacket.blockState.getBukkitMaterial());
                blockUpdateEvent1214.setY(pos.getY());
                blockUpdateEvent1214.setZ(pos.getZ());
                blockUpdateEvent1214.setOriginalBlock(blockUpdateEvent1214.getLocation().getBlock());

                pluginManager.callEvent(blockUpdateEvent1214);
                if (blockUpdateEvent1214.isCancelled())
                    return;

                if (blockUpdateEvent1214.getBlockData() != blockData) {
                    ClientboundBlockUpdatePacket newPacket = new ClientboundBlockUpdatePacket(pos, ((CraftBlockData) blockUpdateEvent1214.getBlockData()).getState());

                    super.write(ctx, newPacket, promise);
                    return;
                }
            }
            else if(packet instanceof ClientboundSoundPacket soundPacket){
                final SoundEvent1214 soundEvent1214 = new SoundEvent1214(player, true);
                soundEvent1214.setX(soundPacket.getX());
                soundEvent1214.setY(soundPacket.getY());
                soundEvent1214.setZ(soundPacket.getZ());
                soundEvent1214.setVolume(soundPacket.getVolume());
                soundEvent1214.setPitch(soundPacket.getPitch());
                soundEvent1214.setSeed(soundPacket.getSeed());
                soundEvent1214.setId(soundPacket.getSound().value().location().toString());;
                soundEvent1214.setSource(soundPacket.getSource().getName());
                pluginManager.callEvent(soundEvent1214);

                if(soundEvent1214.isCancelled())
                    return;

            }
            else if(packet instanceof ClientboundSoundEntityPacket soundPacket){
                final SoundEvent1214 soundEvent1214 = new SoundEvent1214(player, true);
                soundEvent1214.setEntity(true);
                soundEvent1214.setVolume(soundPacket.getVolume());
                soundEvent1214.setPitch(soundPacket.getPitch());
                soundEvent1214.setSeed(soundPacket.getSeed());
                soundEvent1214.setId(soundPacket.getSound().value().location().toString());
                soundEvent1214.setSource(soundPacket.getSource().getName());
                pluginManager.callEvent(soundEvent1214);

                if(soundEvent1214.isCancelled())
                    return;
            }


            super.write(ctx, msg, promise);
        } catch (Exception e) {
            e.printStackTrace();
            super.write(ctx, msg, promise);
        }
    }


}
