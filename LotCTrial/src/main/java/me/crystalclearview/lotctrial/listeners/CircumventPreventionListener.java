package me.crystalclearview.lotctrial.listeners;

import me.crystalclearview.lotctrial.LotCTrial;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.inventory.ItemStack;

public class CircumventPreventionListener implements Listener {

    public LotCTrial plugin;
    public CircumventPreventionListener(LotCTrial plugin) {
        this.plugin = plugin;
    }

    @EventHandler (ignoreCancelled = true)
    public void onPistonFarming(BlockPistonExtendEvent event) {
        for (Block block : event.getBlocks().toArray(new Block[0])) {
            Material type = block.getType();
            if (type.equals(Material.WHEAT) || type.equals(Material.POTATOES) || type.equals(Material.CARROTS) || type.equals(Material.BEETROOTS)) {
                event.setCancelled(true);
                event.getBlock().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(event.getBlock().getType()));
                event.getBlock().setType(Material.AIR);
                //No produce given for trying to bypass crop values
                return;
            }
        }
    }
    @EventHandler (ignoreCancelled = true)
    public void onWaterBreakingWheat(BlockFromToEvent event) {
        Material type = event.getToBlock().getType();

        if (event.getBlock().getType().equals(Material.WATER)) {
            if(type.equals(Material.WHEAT) || type.equals(Material.POTATOES)  || type.equals(Material.CARROTS)  || type.equals(Material.BEETROOTS) ){
                event.setCancelled(true);
                event.getToBlock().setType(Material.AIR);
                //No produce given for trying to bypass crop values
            }
        }
    }

}
