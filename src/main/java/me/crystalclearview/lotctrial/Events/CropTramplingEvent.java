package me.crystalclearview.lotctrial.Events;

import me.crystalclearview.lotctrial.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CropTramplingEvent implements Listener {

    //Linking class to the Main plugin, gives access to methods such as plugin.getConfig() due to the getter and setter.
    public Main plugin;
    public CropTramplingEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        Boolean ct = plugin.getConfig().getBoolean(p.getName() + ".crop-trampling");

        if (!ct) { //Checking to see if Boolean is false

            Block b = e.getClickedBlock(); //Getting the farmland which has been jumped on.

            if(e.getAction() == Action.PHYSICAL){ //Seeing if the action was done with the players body.
                if(b.getType() == Material.FARMLAND){ //Seeing if the block is Farmland
                    e.setCancelled(true); //Stops the Farmland from being uprooted.
                }
            }

        }else if(ct){ //Checking to see if Boolean is true

            Block b = e.getClickedBlock(); //Getting the farmland which has been jumped on.

            if(e.getAction() == Action.PHYSICAL){ //Seeing if the action was done with the players body.
                if(b.getType() == Material.FARMLAND){ //Seeing if the block is Farmland
                    e.setCancelled(false); //Lets the Farmland be destroyed
                    b.setType(Material.DIRT);
                    p.sendMessage(ChatColor.RED + "You have uprooted some farmland! Please mind your step!");
                }
            }

        }
    }
}
