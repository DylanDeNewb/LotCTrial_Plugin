package me.crystalclearview.lotctrial.listeners;

import me.crystalclearview.lotctrial.LotCTrial;
import me.crystalclearview.lotctrial.levelling.PlayerLevel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    public LotCTrial plugin;
    public PlayerQuitListener(LotCTrial plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        PlayerLevel levelManager = plugin.levelManager.get(p.getUniqueId());

        if(plugin.levelManager.containsKey(p.getUniqueId())){
            plugin.levelManager.remove(p.getUniqueId());
            plugin.getConfig().set("FarmLevels." + p.getUniqueId() + ".level", levelManager.getLevel());
            plugin.getConfig().set("FarmLevels." + p.getUniqueId() + ".xp", levelManager.getXp());
            plugin.saveConfig();
        }
    }
}
