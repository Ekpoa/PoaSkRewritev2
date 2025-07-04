package poa.packets;

import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket;

import java.lang.reflect.Constructor;

public class HeadRotPacket1217 {

    @SneakyThrows
    public static Object packet(int id, int yRot){
        Class<ClientboundRotateHeadPacket> clazz = ClientboundRotateHeadPacket.class;

        Constructor<ClientboundRotateHeadPacket> constructor = clazz.getDeclaredConstructor(FriendlyByteBuf.class);
        constructor.setAccessible(true);

        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());

        buf.writeVarInt(id);
        buf.writeByte(yRot * 255 / 360);

        return constructor.newInstance(buf);

    }

}
