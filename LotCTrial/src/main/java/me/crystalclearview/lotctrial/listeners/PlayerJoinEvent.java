package me.crystalclearview.lotctrial.listeners;

import me.crystalclearview.lotctrial.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinEvent implements Listener {

    //Linking class to the Main plugin, gives access to methods such as plugin.getConfig() due to the getter and setter.
    public Main plugin;
    public PlayerJoinEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(org.bukkit.event.player.PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!p.hasPlayedBefore()){
            plugin.getConfig().set(p.getName() + ".crop-trampling", false);
            plugin.saveConfig();
        }
    }
}
