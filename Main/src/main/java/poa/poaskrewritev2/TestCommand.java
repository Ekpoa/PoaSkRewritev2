package poa.poaskrewritev2;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import poa.ChangeIP;
import poa.guardian.GuardianBeam1206;
import poa.packets.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player){

            final Location location = player.getLocation();

            Bukkit.getScheduler().runTaskLater(PoaSkRewritev2.getINSTANCE(), () -> {
                final GuardianBeam1206 beam = new GuardianBeam1206(List.of(player), "bob" + ThreadLocalRandom.current().nextInt(999), location, player.getLocation(), args[0], PoaSkRewritev2.getINSTANCE());
                beam.loop();
            }, 40L);




        }
        return false;
    }
}
