package poa;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.BrandPayload;
import net.minecraft.network.protocol.common.custom.GameTestAddMarkerDebugPayload;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R3.block.CraftBlock;
import org.bukkit.entity.Player;

public class SendPayloadPacket1204 {

    public static void sendGameTestMarker(Player player, Location location, String text, Color color, int durationMs) {
        BlockPos pos = ((CraftBlock) location.getBlock()).getPosition();

        GameTestAddMarkerDebugPayload marker = new GameTestAddMarkerDebugPayload(pos, color.asARGB(), text, durationMs);

        SendPacket1204.sendPacket(player, new ClientboundCustomPayloadPacket(marker));

    }

    public static void sendBrandPayload(Player player, String brand){
        SendPacket1204.sendPacket(player, new BrandPayload(brand));
    }

}
