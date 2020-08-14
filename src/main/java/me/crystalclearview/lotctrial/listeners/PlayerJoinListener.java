package me.crystalclearview.lotctrial.listeners;

import me.crystalclearview.lotctrial.LotCTrial;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinListener implements Listener {

    //Linking class to the Main plugin, gives access to methods such as plugin.getConfig() due to the getter and setter.
    public LotCTrial plugin;
    public PlayerJoinListener(LotCTrial plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(org.bukkit.event.player.PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!p.hasPlayedBefore()){
            plugin.getConfig().set(p.getUniqueId() + ".crop-trampling", false);
            plugin.saveConfig();
        }
    }
}
