package me.crystalclearview.lotctrial.listeners;

import me.crystalclearview.lotctrial.LotCTrial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.RED;

public class ExplosionListener implements Listener {
    public LotCTrial plugin;
    public ExplosionListener(LotCTrial plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onInteractEvent(EntityExplodeEvent e) {

        for(Block b : e.blockList()){
            if(b.getType() == Material.WHEAT || b.getType() == Material.BEETROOTS || b.getType() == Material.CARROTS || b.getType() == Material.POTATOES){
                e.setCancelled(true);
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.hasPermission("farming.alert")){
                        p.sendMessage(RED + "Fireball blocked! Reason: Contained a crop.");
                        p.sendMessage(GOLD + "X: " + (int) e.getLocation().getX());
                        p.sendMessage(GOLD + "Y: " + (int) e.getLocation().getY());
                        p.sendMessage(GOLD + "Z: " + (int) e.getLocation().getZ());
                    }
                }
            }
        }
    }
}
