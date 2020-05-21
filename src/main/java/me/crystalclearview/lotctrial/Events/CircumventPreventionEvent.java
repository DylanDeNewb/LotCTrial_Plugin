package me.crystalclearview.lotctrial.Events;

import me.crystalclearview.lotctrial.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.inventory.ItemStack;

public class CircumventPreventionEvent implements Listener {

    public Main plugin;
    public CircumventPreventionEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler (ignoreCancelled = true)
    public void onPistonFarming(BlockPistonExtendEvent event) {
        for (Block block : event.getBlocks().toArray(new Block[0])) {
            Material type = block.getType();
            if (type.equals(Material.WHEAT) || type.equals(Material.POTATOES) || type.equals(Material.CARROTS) || type.equals(Material.BEETROOTS)) {
                event.setCancelled(true);
                event.getBlock().setType(Material.AIR);
                return;
            }
        }
    }
    @EventHandler (ignoreCancelled = true)
    public void onWaterBreakingWheat(BlockFromToEvent event) {
        if (event.getBlock().getType().equals(Material.WATER) && event.getToBlock().getType().equals(Material.WHEAT)) {
            event.getToBlock().setType(Material.AIR);
        }
    }

}
