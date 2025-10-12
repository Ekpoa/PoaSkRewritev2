package poa.poaskrewritev2;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import poa.poaskrewritev2.events.bukkitevents.KickEvent;
import poa.poaskrewritev2.effects.entity.EffSetPlayerNameAndSkin;
import poa.poaskrewritev2.events.bukkitevents.JoinLeave;
import poa.poaskrewritev2.events.bukkitevents.PlayerLoadEntity;
import poa.poaskrewritev2.expressions.ExprHostname;
import poa.util.PoaPlugin;

import java.io.IOException;

public final class PoaSkRewritev2 extends JavaPlugin {

    @Getter
    public static PoaSkRewritev2 INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        saveDefaultConfig();

        getCommand("poasktest").setExecutor(new TestCommand());



        SkriptAddon skriptAddon = Skript.registerAddon(this);


        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinLeave(), this);
        pm.registerEvents(new ExprHostname(), this);
        pm.registerEvents(new EffSetPlayerNameAndSkin(), this);
        pm.registerEvents(new PlayerLoadEntity(), this);

        if(getConfig().getBoolean("PreventSelfInteractionKick"))
            pm.registerEvents(new KickEvent(), this);


        PoaPlugin.setPlugin(this);

        try {
            skriptAddon.loadClasses("poa.poaskrewritev2", "expressions", "effects", "effects.entity", "effects.packets", "effects.fun", "events", "expressions.metadatasec");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
