package poa.packets;

import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;

import java.lang.reflect.Constructor;

public class AnimationPacket12111 {

    private static final Class<?> clazz = ClientboundAnimatePacket.class;
    private static final Constructor<?> constructor;

    static {
        try {
            constructor = clazz.getDeclaredConstructor(FriendlyByteBuf.class);
            constructor.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public static Object packet(int id, int animation){
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeVarInt(id);
        buf.writeByte(animation);

        return constructor.newInstance(buf);
    }
}
