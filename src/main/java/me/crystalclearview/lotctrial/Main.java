package me.crystalclearview.lotctrial;

import me.crystalclearview.lotctrial.Commands.FarmCommand;
import me.crystalclearview.lotctrial.Events.CircumventPreventionEvent;
import me.crystalclearview.lotctrial.Events.CropTramplingEvent;
import me.crystalclearview.lotctrial.Events.HarvestEvent;
import me.crystalclearview.lotctrial.Events.InventoryClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {


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

        pm.registerEvents(new HarvestEvent(this), this);
        pm.registerEvents(new CropTramplingEvent(this), this);
        pm.registerEvents(new CircumventPreventionEvent(this), this);
        pm.registerEvents(new InventoryClickEvent(this), this);

        this.getCommand("farm").setExecutor(new FarmCommand(this));
    }
    public void onDisable(){
        getLogger().info("LotCTrial Plugin Disabled!");
        saveConfig();
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
