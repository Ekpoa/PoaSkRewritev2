package poa.packets;

import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;

import java.lang.reflect.Constructor;

public class TeleportPacket1206 {

    private static Constructor<?> packetConstructor;

    @SneakyThrows
    public static Object teleportPacket(int id, double x, double y, double z, float yaw, float pitch, boolean onGround) {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeVarInt(id);
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeByte((byte) ((int) (yaw * 256.0F / 360.0F)));
        buf.writeByte((byte) ((int) (pitch * 256.0F / 360.0F)));
        buf.writeBoolean(onGround);

        if(packetConstructor == null) {
            packetConstructor = ClientboundTeleportEntityPacket.class.getDeclaredConstructor(FriendlyByteBuf.class);
            packetConstructor.setAccessible(true);
        }

        return packetConstructor.newInstance(buf);
    }

}
