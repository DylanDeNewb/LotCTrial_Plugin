package me.crystalclearview.lotctrial.Events;

import me.crystalclearview.lotctrial.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

public class CircumventPreventionEvent implements Listener {

    public Main plugin;
    public CircumventPreventionEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void pistonRetract(BlockPistonRetractEvent e){
        Block b = e.getBlock(); //Getting block (crop)

        if(b.getType() == Material.WHEAT || b.getType() == Material.POTATOES || b.getType() == Material.CARROTS || b.getType() == Material.BEETROOTS){ //Checking if block type is a crop
            e.setCancelled(true); //Stops the action
        }
    }
    @EventHandler
    public void pistonExtend(BlockPistonExtendEvent e){
        Block b = e.getBlock(); //Getting block (crop)

        if(b.getType() == Material.WHEAT || b.getType() == Material.POTATOES || b.getType() == Material.CARROTS || b.getType() == Material.BEETROOTS){ //Checking if block type is a crop
            e.setCancelled(true); //Stops the action
        }
    }

}
