package poa.poaskrewritev2;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import poa.packets.*;
import poa.packets.packetListener.events.BlockUpdateEvent1214;
import poa.util.Messages;

import java.util.List;
import java.util.UUID;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
        FakePlayer1214.spawnTablistOnly(List.of(player), "bob", MiniMessage.miniMessage().deserialize(args[0]), "Ekpoa", UUID.randomUUID(), 34);



        }
        return false;
    }

}
