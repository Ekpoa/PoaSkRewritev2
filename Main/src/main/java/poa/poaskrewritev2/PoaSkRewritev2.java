package poa.poaskrewritev2;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import poa.poaskrewritev2.events.JoinLeave;

import java.io.IOException;

public final class PoaSkRewritev2 extends JavaPlugin {

    @Getter
    public static PoaSkRewritev2 INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        getCommand("poasktest").setExecutor(new TestCommand());



        SkriptAddon skriptAddon = Skript.registerAddon(this);


        getServer().getPluginManager().registerEvents(new JoinLeave(), this);

        try {
            skriptAddon.loadClasses("poa.poaskrewritev2", "expressions", "effects", "effects.entity", "effects.packets", "effects.fun"); //todo add events back
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
