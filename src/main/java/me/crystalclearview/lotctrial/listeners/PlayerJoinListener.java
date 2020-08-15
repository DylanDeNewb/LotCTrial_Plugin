package me.crystalclearview.lotctrial.listeners;

import me.crystalclearview.lotctrial.LotCTrial;
import me.crystalclearview.lotctrial.levelling.PlayerLevel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    //Linking class to the Main plugin, gives access to methods such as plugin.getConfig() due to the getter and setter.
    public LotCTrial plugin;
    public PlayerJoinListener(LotCTrial plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!p.hasPlayedBefore()){
            plugin.levelManager.put(p.getUniqueId(), new PlayerLevel(0, 0));
            plugin.getConfig().set("FarmLevels." + p.getUniqueId() + ".level", 0);
            plugin.getConfig().set("FarmLevels." + p.getUniqueId() + ".xp", 0);

            plugin.saveConfig();
            plugin.reloadConfig();
        }else{
            int level = plugin.getConfig().getInt("FarmLevels." + p.getUniqueId() + ".level");
            int xp = plugin.getConfig().getInt("FarmLevels." + p.getUniqueId() + ".xp");

            plugin.levelManager.put(p.getUniqueId(), new PlayerLevel(level, xp));
        }
    }
}
