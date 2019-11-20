package me.CrystalClearView.LotCTicketSystem.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StoryGUI {
    public static Inventory storygui = Bukkit.createInventory(null, 54, "Story Tickets");

    public static void openStoryTickets(Player p){

        ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta placeholderm = placeholder.getItemMeta();
        placeholderm.setDisplayName("");
        placeholder.setItemMeta(placeholderm);

        ItemStack exit = new ItemStack(Material.BARRIER, 1);
        ItemMeta exitm = exit.getItemMeta();
        exitm.setDisplayName(ChatColor.RED + "Exit");
        exit.setItemMeta(exitm);

        ItemStack closealltickets = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
        ItemMeta closeallticketsm = closealltickets.getItemMeta();
        closeallticketsm.setDisplayName(ChatColor.RED + "Clear All Tickets");
        closealltickets.setItemMeta(closeallticketsm);

        storygui.setItem(53, placeholder);
        storygui.setItem(52, exit);
        storygui.setItem(51, placeholder);
        storygui.setItem(50, placeholder);
        storygui.setItem(49, placeholder);
        storygui.setItem(48, placeholder);
        storygui.setItem(47, placeholder);
        storygui.setItem(46, closealltickets);
        storygui.setItem(45, placeholder);

        p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 2F, 1F);

        p.openInventory(storygui);

    }
}