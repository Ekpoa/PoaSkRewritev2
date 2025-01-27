package poa.packets;

import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundEntityPositionSyncPacket;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.world.entity.PositionMoveRotation;
import net.minecraft.world.phys.Vec3;

import java.lang.reflect.Constructor;

public class TeleportPacket1213 {


    @SneakyThrows
    public static Object teleportPacket(int id, double x, double y, double z, float yaw, float pitch, boolean onGround) {
        yaw = (yaw * 256.0F / 360.0F);
        pitch = (pitch * 256.0F / 360.0F);

        return new ClientboundEntityPositionSyncPacket(id, new PositionMoveRotation(new Vec3(x,y,z), new Vec3(0,0,0), yaw, pitch), onGround);
    }

}
