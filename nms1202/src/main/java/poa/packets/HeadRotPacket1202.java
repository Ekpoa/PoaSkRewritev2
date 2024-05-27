package poa.packets;

import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket;

public class HeadRotPacket1202 {

    @SneakyThrows
    public static Object packet(int id, int yRot){
        Class<ClientboundRotateHeadPacket> clazz = ClientboundRotateHeadPacket.class;


        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());

        buf.writeVarInt(id);
        buf.writeByte(yRot * 255 / 360);

        return new ClientboundRotateHeadPacket(buf);


    }

}
