package poa.packets;

import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;

import java.lang.reflect.Constructor;

public class FakeDeathAnimation1218 {

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
