package poa;

import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundSetCameraPacket;

import java.lang.reflect.Constructor;

public class CameraPacket1206 {

    @SneakyThrows
    public static Object cameraPacket(int id){
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());

        buf.writeVarInt(id);

        Class<ClientboundSetCameraPacket> clazz = ClientboundSetCameraPacket.class;
        Constructor<ClientboundSetCameraPacket> constructor = clazz.getDeclaredConstructor(FriendlyByteBuf.class);

        constructor.setAccessible(true);

        return constructor.newInstance(buf);
    }

}
