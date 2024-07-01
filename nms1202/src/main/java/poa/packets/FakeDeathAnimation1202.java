package poa.packets;

import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket;
import net.minecraft.network.protocol.game.ServerboundClientCommandPacket;

public class FakeDeathAnimation1202 {

    @SneakyThrows
    public static Object packet(int id) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeInt(id);
        buf.writeByte((byte) 3);

        return new ClientboundEntityEventPacket(buf);
    }

}
