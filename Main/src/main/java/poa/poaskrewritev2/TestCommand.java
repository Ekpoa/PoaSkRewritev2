package poa.poaskrewritev2;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import poa.blocks.SetFast1214;
import poa.packets.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            final Object packet = FakeEntity.fakeEntityPacket(123, player.getLocation(), "block_display");

            final Metadata1214 metadata = new Metadata1214(123);
            metadata.setDisplayBlock(Material.DIRT.createBlockData());
            metadata.setRotationLeft(0, 1, 0, 0);

            SendPacket.sendPacket(player, packet);
            SendPacket.sendPacket(player, metadata.build());

            Bukkit.getScheduler().runTaskLater(PoaSkRewritev2.getINSTANCE(), () -> {
                final Metadata1214 m = new Metadata1214(123);
                m.setRotationLeft(0, 0.91, 0, -0.42);
                m.setTransformationDuration(20);
                m.setInterpolationDelay(0);
                SendPacket.sendPacket(player, m.build());
            }, 20L);


        }
        return false;
    }




}
