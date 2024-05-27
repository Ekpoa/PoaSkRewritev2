package poa.packets;

import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundSetCameraPacket;

public class CameraPacket1202 {

    @SneakyThrows
    public static Object cameraPacket(int id){
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());

        buf.writeVarInt(id);

        return new ClientboundSetCameraPacket(buf);
    }

}
