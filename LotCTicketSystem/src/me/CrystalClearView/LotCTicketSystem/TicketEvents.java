package me.CrystalClearView.LotCTicketSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketEvents implements Listener {

    static TicketFile tf = TicketFile.getInstance();
    public static Inventory ticketinteraction = Bukkit.createInventory(null, 36, "Ticket Interaction");
    public static String tu =  Bukkit.getPlayer(String.valueOf(tf.getData().getConfigurationSection("Tickets."))).getUniqueId().toString();

    public static void openTicketsInteraction(Player p){

        ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta placeholderm = placeholder.getItemMeta();
        placeholderm.setDisplayName("");
        placeholder.setItemMeta(placeholderm);

        ItemStack exit = new ItemStack(Material.BARRIER, 1);
        ItemMeta exitm = exit.getItemMeta();
        exitm.setDisplayName(ChatColor.RED + "Exit");
        exit.setItemMeta(exitm);

        ItemStack newticket = new ItemStack(Material.PAPER);
        ItemMeta newticketmeta = newticket.getItemMeta();
        List<String> newticketlore = new ArrayList<String>();
        newticketmeta.setDisplayName(p.getName());
        newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
        newticketlore.add(ChatColor.DARK_GREEN +     "Description:             ");
        newticketlore.add(ChatColor.GOLD+            "%description%            ".replace("%description%", tf.getData().getString("Tickets." + p.getUniqueId() + ".dev" + ".description")));
        newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");

        ItemStack teleport = new ItemStack(Material.COMPASS);
        ItemMeta teleportm = teleport.getItemMeta();
        teleportm.setDisplayName(ChatColor.DARK_GREEN + "Teleport");

        newticketmeta.setLore(newticketlore);
        newticket.setItemMeta(newticketmeta);

        ticketinteraction.setItem(11, teleport);
        ticketinteraction.setItem(9, newticket);
        ticketinteraction.setItem(27, placeholder);
        ticketinteraction.setItem(28, placeholder);
        ticketinteraction.setItem(29, placeholder);
        ticketinteraction.setItem(30, placeholder);
        ticketinteraction.setItem(31, placeholder);
        ticketinteraction.setItem(32, placeholder);
        ticketinteraction.setItem(33, placeholder);
        ticketinteraction.setItem(34, exit);
        ticketinteraction.setItem(35, placeholder);

        p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 2F, 1F);

        p.openInventory(ticketinteraction);

    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        InventoryView view = e.getView();
        ClickType click = e.getClick();

        Player p = (Player) e.getWhoClicked();

        if(view.getTitle().equals("Dev Tickets")){
            if(TicketAPI.newdevticket.getItemMeta().getDisplayName()==e.getCurrentItem().getItemMeta().getDisplayName()){
                tf.getData().set(tu + ".dev" + ".claimee", p.getName());
                p.sendMessage("claimed ticket");

                openTicketsInteraction(p);

                e.setCancelled(true);
            }else {
                e.setCancelled(true);
            }
        }
    }
}
