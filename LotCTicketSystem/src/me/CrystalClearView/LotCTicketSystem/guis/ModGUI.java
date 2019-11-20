package me.CrystalClearView.LotCTicketSystem.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ModGUI {
    public static Inventory modgui = Bukkit.createInventory(null, 54, "Mod Tickets");

    public static void openModTickets(Player p){

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

        modgui.setItem(53, placeholder);
        modgui.setItem(52, exit);
        modgui.setItem(51, placeholder);
        modgui.setItem(50, placeholder);
        modgui.setItem(49, placeholder);
        modgui.setItem(48, placeholder);
        modgui.setItem(47, placeholder);
        modgui.setItem(46, closealltickets);
        modgui.setItem(45, placeholder);

        p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 2F, 1F);

        p.openInventory(modgui);

    }
}