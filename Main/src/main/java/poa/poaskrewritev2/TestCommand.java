package poa.poaskrewritev2;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import poa.ChangeIP;
import poa.packets.Metadata;
import poa.packets.SendPacket;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
            Metadata metadata = new Metadata(13);
            metadata.setText("<red>testing this");

            SendPacket.sendPacket(player, metadata.build());
        }
        return false;
    }
}
