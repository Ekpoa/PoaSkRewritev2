package poa.packets;

import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;

public class FakeDeathAnimation1204 {

    @SneakyThrows
    public static Object packet(int id) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeInt(id);
        buf.writeByte((byte) 3);

        return new ClientboundEntityEventPacket(buf);
    }

}
