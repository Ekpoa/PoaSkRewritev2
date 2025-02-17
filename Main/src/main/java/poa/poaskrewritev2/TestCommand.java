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

import java.util.*;

public class TestCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){
           FakePlayer1202.spawnFakePlayer(List.of(player), "Bob", "Notch", player.getLocation(), false, 1, 22, UUID.randomUUID(), 127);

        }
        return false;
    }




}
