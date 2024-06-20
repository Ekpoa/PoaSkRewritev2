package poa.poaskrewritev2;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.bstats.bukkit.Metrics;
import ch.njol.skript.bstats.charts.SimplePie;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import poa.poaskrewritev2.effects.entity.EffSetPlayerNameAndSkin;
import poa.poaskrewritev2.events.JoinLeave;
import poa.poaskrewritev2.expressions.ExprHostname;

import java.io.IOException;

public final class PoaSkRewritev2 extends JavaPlugin {

    @Getter
    public static PoaSkRewritev2 INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        getCommand("poasktest").setExecutor(new TestCommand());



        SkriptAddon skriptAddon = Skript.registerAddon(this);


        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinLeave(), this);
        pm.registerEvents(new ExprHostname(), this);
        pm.registerEvents(new EffSetPlayerNameAndSkin(), this);

        try {
            skriptAddon.loadClasses("poa.poaskrewritev2", "expressions", "effects", "effects.entity", "effects.packets", "effects.fun", "events");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Metrics metrics = new Metrics(this, 22275);

        metrics.addCustomChart(new SimplePie("skript_version", () -> Skript.getVersion().toString()));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
