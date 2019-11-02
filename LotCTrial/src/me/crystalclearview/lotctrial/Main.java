package me.crystalclearview.lotctrial;

import me.crystalclearview.lotctrial.Commands.CropTramplingCommand;
import me.crystalclearview.lotctrial.Events.CircumventPreventionEvent;
import me.crystalclearview.lotctrial.Events.CropTramplingEvent;
import me.crystalclearview.lotctrial.Events.HarvestEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        getLogger().info("LotCTrial Plugin Enabled!");
        //Loading the config
        getConfig().options().copyDefaults(true);
        saveConfig();
        plugin = this;
        //Registering Commands&Events.
        getServer().getPluginManager().registerEvents(new HarvestEvent(this), this);
        getServer().getPluginManager().registerEvents(new CropTramplingEvent(this), this);
        getServer().getPluginManager().registerEvents(new CircumventPreventionEvent(this), this);
        this.getCommand("croptrampling").setExecutor(new CropTramplingCommand(this));
    }
    public void onDisable(){
        getLogger().info("LotCTrial Plugin Disabled!");
    }
    //Creating a static method to be able to access plugin in any class. (Getter and Setter)
    public static JavaPlugin plugin;

    public void setPlugin(Plugin plugin) {
        Main.plugin = (JavaPlugin) plugin;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
