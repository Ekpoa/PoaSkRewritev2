package poa.packets;

import ca.spottedleaf.moonrise.patches.chunk_system.level.entity.EntityLookup;
import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;

import java.lang.reflect.Constructor;

public class FakeDeathAnimation12110 {

    public static Class<?> packetClass;
    public static Constructor<?> constructor;

    @SneakyThrows
    public static Object packet(int id) {
        if (packetClass == null) {
            packetClass = ClientboundEntityEventPacket.class;
            constructor = packetClass.getDeclaredConstructor(FriendlyByteBuf.class);
            constructor.setAccessible(true);
        }

        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeInt(id);
        buf.writeByte((byte) 3);



        return constructor.newInstance(buf);
    }

}
