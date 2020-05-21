package me.crystalclearview.lotctrial.Events;

import me.crystalclearview.lotctrial.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClickEvent implements Listener {

    //Linking class to the Main plugin, gives access to methods such as plugin.getConfig() due to the getter and setter.
    public Main plugin;
    public InventoryClickEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void inventoryClick(org.bukkit.event.inventory.InventoryClickEvent e){
        InventoryView view = e.getView();
        ItemStack item = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();

        if(view.getTitle().equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Hoe Creation")){
            if(item.getType() == Material.GREEN_WOOL){
                ItemMeta itemm = item.getItemMeta();
                if(itemm.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&b&2Craft"))){
                    if(p.getInventory().contains(Material.IRON_INGOT, 2)){
                        if(p.getInventory().contains(Material.STICK, 2)){
                            Inventory inv = p.getInventory();
                            ItemStack crafted = new ItemStack(Material.IRON_HOE);
                            ItemMeta craftedm = crafted.getItemMeta();
                            craftedm.setDisplayName(ChatColor.GREEN + "Lucky Hoe");

                            crafted.setItemMeta(craftedm);

                            inv.remove(Material.STICK);
                            inv.remove(Material.STICK);
                            inv.remove(Material.IRON_INGOT);
                            inv.remove(Material.IRON_INGOT);
                            inv.addItem(crafted);
                        }else {
                            p.sendMessage(ChatColor.RED + "You are missing some sticks!");
                        }
                    }else {
                        p.sendMessage(ChatColor.RED + "You are missing some iron ingots!");
                    }
                    e.setCancelled(true);
                }
            }
            if(item.getType() == Material.RED_WOOL){
                ItemMeta rmeta = item.getItemMeta();
                if(rmeta.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&b&4Close"))){
                    p.closeInventory();
                    p.sendMessage(ChatColor.RED + "Hoe Creation GUI closed!");
                    e.setCancelled(true);
                }
            }else{
                e.setCancelled(true);
            }
        }
    }

}
