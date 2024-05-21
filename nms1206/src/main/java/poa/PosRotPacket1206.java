package poa;

import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;

public class PosRotPacket1206 {



    public static Object posPacket(int id, short deltaX, short deltaY, short deltaZ){
        return new ClientboundMoveEntityPacket.Pos(id, deltaX, deltaY, deltaZ, true);
    }


    public static Object rotPacket(int id, int yaw, int pitch){
        return new ClientboundMoveEntityPacket.Rot(id, (byte) (yaw * 255F / 360F), (byte) (pitch * 255F / 360F), true);
    }

}
