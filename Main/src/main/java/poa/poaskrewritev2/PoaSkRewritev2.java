package poa.poaskrewritev2;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import poa.poaskrewritev2.events.bukkitevents.KickEvent;
import poa.poaskrewritev2.effects.entity.EffSetPlayerNameAndSkin;
import poa.poaskrewritev2.events.bukkitevents.JoinLeave;
import poa.poaskrewritev2.events.bukkitevents.PlayerLoadEntity;
import poa.poaskrewritev2.expressions.ExprHostname;
import poa.util.PoaPlugin;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

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

        final FileConfiguration config = getConfig();
        if(config.getBoolean("PreventSelfInteractionKick"))
            pm.registerEvents(new KickEvent(), this);

        if(!config.isSet("SaveSkinCacheToDisk"))
            config.set("SaveSkinCacheToDisk", true);
        if(!config.isSet("AllPacketEvent"))
            config.set("AllPacketEvent", false);
        if(!config.isSet("DisableTabTeamHandling"))
            config.set("DisableTabTeamHandling", false);

        saveConfig();

        if(List.of("12110").contains(Bukkit.getVersion()))
            getLogger().log(Level.INFO, "If you see a [HorriblePlayerLoginEventHack] ignore this, this is for a version that you are not");




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
