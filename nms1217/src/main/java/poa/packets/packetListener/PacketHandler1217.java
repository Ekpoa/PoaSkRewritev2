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
import poa.util.Components1217;
import poa.util.PoaPlugin1217;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class PacketHandler1217 extends ChannelDuplexHandler {

    //private List<ClientboundSystemChatPacket> list = new ArrayList<>();

    Player player;

    private static final boolean allPackets = PoaPlugin1217.getPlugin().getConfig().getBoolean("AllPacketEvent");



    public PacketHandler1217(Player player) {
        this.player = player;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (!(msg instanceof Packet<?> packet)) {
                super.channelRead(ctx, msg);
                return;
            }

            if(allPackets) {
                final AllPacketEvent1217 allPacketEvent = new AllPacketEvent1217(player, true);
                allPacketEvent.setPacket(packet);
                allPacketEvent.setClientbound(false);
                pluginManager.callEvent(allPacketEvent);
                if (allPacketEvent.isCancelled())
                    return;
            }

            if(packet instanceof ServerboundPlayerActionPacket actionPacket){
                final BlockPos pos = actionPacket.getPos();
                final ServerboundPlayerActionPacket.Action action = actionPacket.getAction();
                final PlayerActionEvent1217 event = new PlayerActionEvent1217(player, true);


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

            if(allPackets && !(packet instanceof ClientboundSystemChatPacket)) {
                final AllPacketEvent1217 allPacketEvent = new AllPacketEvent1217(player, true);
                allPacketEvent.setPacket(packet);
                pluginManager.callEvent(allPacketEvent);
                if (allPacketEvent.isCancelled())
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

                List<Integer> ids = GlowMap1217.glowMap.get(player);

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


                ParticleEvent1217 particleEvent = new ParticleEvent1217(player, true);
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
                final Component component = Components1217.componentActual(chatPacket.content());


                final SystemChatPacketEvent1217 chatPacketEvent1206 = new SystemChatPacketEvent1217(player, true);

                chatPacketEvent1206.setOverlay(chatPacket.overlay());

                chatPacketEvent1206.setString(MiniMessage.miniMessage().serialize(component));

                pluginManager.callEvent(chatPacketEvent1206);

                if (chatPacketEvent1206.isCancelled())
                    return;
            } else if (packet instanceof ClientboundPlayerChatPacket chatPacket) {
                String message;
                if (chatPacket.unsignedContent() != null)
                    message = MiniMessage.miniMessage().serialize(Components1217.componentActual(chatPacket.unsignedContent()));
                else
                    message = chatPacket.body().content();


                final PlayerChatPacketEvent1217 playerChatPacketEvent121 = new PlayerChatPacketEvent1217(player, true);

                playerChatPacketEvent121.setString(message);

                playerChatPacketEvent121.setSender(chatPacket.sender());

                pluginManager.callEvent(playerChatPacketEvent121);

                if (playerChatPacketEvent121.isCancelled())
                    return;

            } else if (packet instanceof ClientboundBlockUpdatePacket blockUpdatePacket) {
                final BlockUpdateEvent1217 blockUpdateEvent1217 = new BlockUpdateEvent1217(player, true);
                final BlockState blockState = blockUpdatePacket.getBlockState();
                final CraftBlockData blockData = CraftBlockData.fromData(blockState);
                blockUpdateEvent1217.setBlockData(blockData);
                final BlockPos pos = blockUpdatePacket.getPos();
                blockUpdateEvent1217.setX(pos.getX());
                blockUpdateEvent1217.setMaterial(blockUpdatePacket.blockState.getBukkitMaterial());
                blockUpdateEvent1217.setY(pos.getY());
                blockUpdateEvent1217.setZ(pos.getZ());
                blockUpdateEvent1217.setOriginalBlock(blockUpdateEvent1217.getLocation().getBlock());

                pluginManager.callEvent(blockUpdateEvent1217);
                if (blockUpdateEvent1217.isCancelled())
                    return;

                if (blockUpdateEvent1217.getBlockData() != blockData) {
                    ClientboundBlockUpdatePacket newPacket = new ClientboundBlockUpdatePacket(pos, ((CraftBlockData) blockUpdateEvent1217.getBlockData()).getState());

                    super.write(ctx, newPacket, promise);
                    return;
                }
            }
            else if(packet instanceof ClientboundSoundPacket soundPacket){
                final SoundEvent1217 soundEvent1215 = new SoundEvent1217(player, true);
                soundEvent1215.setX(soundPacket.getX());
                soundEvent1215.setY(soundPacket.getY());
                soundEvent1215.setZ(soundPacket.getZ());
                soundEvent1215.setVolume(soundPacket.getVolume());
                soundEvent1215.setPitch(soundPacket.getPitch());
                soundEvent1215.setSeed(soundPacket.getSeed());
                soundEvent1215.setId(soundPacket.getSound().value().location().toString());;
                soundEvent1215.setSource(soundPacket.getSource().getName());
                pluginManager.callEvent(soundEvent1215);

                if(soundEvent1215.isCancelled())
                    return;

            }
            else if(packet instanceof ClientboundSoundEntityPacket soundPacket){
                final SoundEvent1217 soundEvent1215 = new SoundEvent1217(player, true);
                soundEvent1215.setEntity(true);
                soundEvent1215.setVolume(soundPacket.getVolume());
                soundEvent1215.setPitch(soundPacket.getPitch());
                soundEvent1215.setSeed(soundPacket.getSeed());
                soundEvent1215.setId(soundPacket.getSound().value().location().toString());
                soundEvent1215.setSource(soundPacket.getSource().getName());
                pluginManager.callEvent(soundEvent1215);

                if(soundEvent1215.isCancelled())
                    return;
            }
            else if (packet instanceof ClientboundLevelChunkWithLightPacket chunkPacket) {
                final int chunkX = chunkPacket.getX();
                final int chunkZ = chunkPacket.getZ();

                ChunkDataPacketEvent1217 event =
                        new ChunkDataPacketEvent1217(player, true, chunkX, chunkZ);

                pluginManager.callEvent(event);

                if (event.isCancelled()) {
                    return;
                }

                super.write(ctx, msg, promise);

                if (event.getFakeBlocks().isEmpty()) {
                    return;
                }

                for (ChunkDataPacketEvent1217.FakeBlock fb : event.getFakeBlocks()) {

                    BlockPos pos = new BlockPos(fb.x(), fb.y(), fb.z());
                    net.minecraft.world.level.block.state.BlockState nmsState =
                            ((CraftBlockData) fb.blockData()).getState();

                    ClientboundBlockUpdatePacket fakePacket =
                            new ClientboundBlockUpdatePacket(pos, nmsState);

                    super.write(ctx, fakePacket, ctx.voidPromise());
                }

                return;
            }

            super.write(ctx, msg, promise);
        } catch (Exception e) {
            e.printStackTrace();
            super.write(ctx, msg, promise);
        }
    }


}
