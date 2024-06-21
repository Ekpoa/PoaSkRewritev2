package poa.packets.packetListener;

import com.google.common.base.Preconditions;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.SneakyThrows;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Registry;
import org.bukkit.craftbukkit.CraftParticle;
import org.bukkit.craftbukkit.CraftRegistry;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.util.CraftNamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import poa.packets.packetListener.events.ParticleEvent1206;

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

            if (packet instanceof ClientboundLevelParticlesPacket particlesPacket) {
                ParticleOptions particle = particlesPacket.getParticle();
                if (particleOptionsClass == null) {
                    particleOptionsClass = particle.getClass();
                    getTypeMethod = particleOptionsClass.getDeclaredMethod("getType");
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
            }


            super.write(ctx, msg, promise);
        }catch (Exception e){
            e.printStackTrace();
            super.write(ctx, msg, promise);
        }
    }


}
