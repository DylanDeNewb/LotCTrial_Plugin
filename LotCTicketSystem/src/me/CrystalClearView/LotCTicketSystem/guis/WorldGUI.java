package me.CrystalClearView.LotCTicketSystem.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WorldGUI {
    public static Inventory worldgui = Bukkit.createInventory(null, 54, "World Tickets");

    public static void openWorldTickets(Player p){

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

        worldgui.setItem(53, placeholder);
        worldgui.setItem(52, exit);
        worldgui.setItem(51, placeholder);
        worldgui.setItem(50, placeholder);
        worldgui.setItem(49, placeholder);
        worldgui.setItem(48, placeholder);
        worldgui.setItem(47, placeholder);
        worldgui.setItem(46, closealltickets);
        worldgui.setItem(45, placeholder);

        p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 2F, 1F);

        p.openInventory(worldgui);

    }
}