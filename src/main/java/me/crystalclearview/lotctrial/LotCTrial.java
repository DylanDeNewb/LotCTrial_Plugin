package me.crystalclearview.lotctrial;

import me.crystalclearview.lotctrial.commands.FarmCommand;
import me.crystalclearview.lotctrial.commands.FarmTabCompletion;
import me.crystalclearview.lotctrial.levelling.PlayerLevel;
import me.crystalclearview.lotctrial.listeners.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class LotCTrial extends JavaPlugin {

    public Economy eco;
    public HashMap<UUID, PlayerLevel> levelManager;

    @Override
    public void onEnable() {

        if(!setupEco()){
            getLogger().warning("Vault Economy plugin not found! Disabling money surprises!");
        }else{
            getLogger().info("Vault Economy plugin found! Enabling money surprises!");
        }
        //Loading the config
        getConfig().options().copyDefaults(true);
        saveConfig();
        //Registering Commands&Events.
        PluginManager pm = Bukkit.getPluginManager();
        //Init
        this.levelManager = new HashMap<>();

        pm.registerEvents(new HarvestListener(this), this);
        pm.registerEvents(new CropTramplingListener(this), this);
        pm.registerEvents(new CircumventPreventionListener(this), this);
        pm.registerEvents(new InventoryClickListener(this), this);
        pm.registerEvents(new ExplosionListener(this), this);
        pm.registerEvents(new PlayerJoinListener(this), this);
        pm.registerEvents(new PlayerQuitListener(this), this);

        this.getCommand("farm").setExecutor(new FarmCommand(this));
        this.getCommand("farm").setTabCompleter(new FarmTabCompletion());
    }
    public void onDisable(){
        this.levelManager.clear();
        getLogger().info("LotCTrial Plugin Disabled! + Cleared HashMap");
    }

    private boolean setupEco(){
        if(Bukkit.getPluginManager().getPlugin("Vault") != null){
            RegisteredServiceProvider<Economy> economy = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);

            if(economy != null){
                eco = economy.getProvider();
            }
            return(eco != null);
        }else{
            return false;
        }


    }

}
