package me.crystalclearview.lotctrial;

import me.crystalclearview.lotctrial.commands.FarmCommand;
import me.crystalclearview.lotctrial.listeners.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class LotCTrial extends JavaPlugin {

    public Economy eco;

    @Override
    public void onEnable() {

        if(!setupEco()){
            getLogger().warning("Vault not found! Disabling money surprises!");
            return;
        }else{
            getLogger().info("Vault found! Enabling money surprises!");
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

    private boolean setupEco(){
        RegisteredServiceProvider<Economy> economy = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if(economy != null){
            eco = economy.getProvider();
        }
        return(eco != null);
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
