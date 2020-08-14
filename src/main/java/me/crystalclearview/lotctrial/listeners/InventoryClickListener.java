package me.crystalclearview.lotctrial.listeners;

import me.crystalclearview.lotctrial.apis.GuiAPI;
import me.crystalclearview.lotctrial.LotCTrial;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.ChatColor.*;

public class InventoryClickListener implements Listener {

    //Linking class to the Main plugin, gives access to methods such as plugin.getConfig() due to the getter and setter.
    public LotCTrial plugin;
    public InventoryClickListener(LotCTrial plugin) {
        this.plugin = plugin;
    }
    GuiAPI guiApi = new GuiAPI(plugin);

    @EventHandler
    public void inventoryClick(org.bukkit.event.inventory.InventoryClickEvent e){
        InventoryView view = e.getView();
        ItemStack item = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();

        if(view.getTitle().equals(GOLD + "" + BOLD + "Hoe Creation")){
            if(item.getType() == Material.GREEN_STAINED_GLASS_PANE){
                ItemMeta itemm = item.getItemMeta();
                if(itemm.getDisplayName().equals(GREEN + "" + BOLD + "Craft")){
                    if(p.getInventory().contains(Material.IRON_INGOT, 2)){
                        if(p.getInventory().contains(Material.STICK, 2)){
                            Inventory inv = p.getInventory();
                            ItemStack crafted = new ItemStack(Material.IRON_HOE);
                            ItemMeta craftedm = crafted.getItemMeta();
                            craftedm.setDisplayName(GREEN + "Lucky Hoe");

                            crafted.setItemMeta(craftedm);

                            inv.remove(Material.STICK);
                            inv.remove(Material.STICK);
                            inv.remove(Material.IRON_INGOT);
                            inv.remove(Material.IRON_INGOT);
                            inv.addItem(crafted);
                        }else {
                            p.sendMessage(guiApi.colour(plugin.getConfig().getString("Messages.prefix")) + RED + "You are missing some sticks!");
                        }
                    }else {
                        p.sendMessage(guiApi.colour(plugin.getConfig().getString("Messages.prefix")) + RED + "You are missing some iron ingots!");
                    }
                    e.setCancelled(true);
                }
            }
            if(item.getType() == Material.RED_STAINED_GLASS_PANE){
                ItemMeta rmeta = item.getItemMeta();
                if(rmeta.getDisplayName().equals(RED + "" + BOLD + "Close")){
                    p.closeInventory();
                    p.sendMessage(guiApi.colour(plugin.getConfig().getString("Messages.prefix")) + RED + "Hoe Creation GUI closed!");
                    e.setCancelled(true);
                }
            }else{
                e.setCancelled(true);
            }
        }
    }

}
