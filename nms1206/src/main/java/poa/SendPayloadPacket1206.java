package poa;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.BrandPayload;
import net.minecraft.network.protocol.common.custom.GameTestAddMarkerDebugPayload;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.entity.Player;

public class SendPayloadPacket1206 {

    public static void sendGameTestMarker(Player player, Location location, String text, Color color, int durationMs) {
        BlockPos pos = ((CraftBlock) location.getBlock()).getPosition();

        GameTestAddMarkerDebugPayload marker = new GameTestAddMarkerDebugPayload(pos, color.asARGB(), text, durationMs);

        SendPacket1206.sendPacket(player, new ClientboundCustomPayloadPacket(marker));

    }

    public static void sendBrandPayload(Player player, String brand){
        SendPacket1206.sendPacket(player, new BrandPayload(brand));
    }

}
