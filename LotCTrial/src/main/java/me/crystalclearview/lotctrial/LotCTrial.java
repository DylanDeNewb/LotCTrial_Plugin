package me.crystalclearview.lotctrial;

import me.crystalclearview.lotctrial.commands.FarmCommand;
import me.crystalclearview.lotctrial.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LotCTrial extends JavaPlugin {


    @Override
    public void onEnable() {
        getLogger().info("LotCTrial Plugin Enabled!");

        if(this.getServer().getPluginManager().getPlugin("Vault") == null){
            getLogger().warning("Vault not found! Disabling money surprises!");
        }
        //Loading the config
        getConfig().options().copyDefaults(true);
        saveConfig();
        plugin = this;
        //Registering Commands&Events.
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new HarvestListener(this), this);
        pm.registerEvents(new CropTramplingListener(this), this);
        pm.registerEvents(new CircumventPreventionListener(this), this);
        pm.registerEvents(new InventoryClickListener(this), this);
        pm.registerEvents(new ExplosionListener(this), this);

        this.getCommand("farm").setExecutor(new FarmCommand(this));
    }
    public void onDisable(){
        getLogger().info("LotCTrial Plugin Disabled!");
    }

    //Creating a static method to be able to access plugin in any class. (Getter and Setter)
    public static JavaPlugin plugin;

    public void setPlugin(Plugin plugin) {
        LotCTrial.plugin = (JavaPlugin) plugin;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
