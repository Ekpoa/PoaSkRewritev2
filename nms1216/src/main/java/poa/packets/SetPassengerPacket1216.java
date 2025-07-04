package poa.packets;

import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket;

import java.lang.reflect.Constructor;

public class SetPassengerPacket1216 {

    @SneakyThrows
    public static Object packet(int id, int[] ids){
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeVarInt(id);
        buf.writeVarIntArray(ids);

        Class<ClientboundSetPassengersPacket> clazz = ClientboundSetPassengersPacket.class;

        Constructor<ClientboundSetPassengersPacket> declaredConstructor = clazz.getDeclaredConstructor(FriendlyByteBuf.class);
        declaredConstructor.setAccessible(true);

        return declaredConstructor.newInstance(buf);
    }

}
