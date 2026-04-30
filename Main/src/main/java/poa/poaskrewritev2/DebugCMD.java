package poa.poaskrewritev2;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class DebugCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NonNull @NotNull String[] args) {
        sender.sendRichMessage("<gold>[PoaSK] <yellow>Version: " + PoaSkRewritev2.INSTANCE.getPluginMeta().getVersion());
        return false;
    }
}
