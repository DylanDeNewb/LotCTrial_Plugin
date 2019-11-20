package me.CrystalClearView.LotCTicketSystem.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommunityGUI {
    public static Inventory communitygui = Bukkit.createInventory(null, 54, "Community Tickets");

    public static void openCommunityTickets(Player p){

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

        communitygui.setItem(53, placeholder);
        communitygui.setItem(52, exit);
        communitygui.setItem(51, placeholder);
        communitygui.setItem(50, placeholder);
        communitygui.setItem(49, placeholder);
        communitygui.setItem(48, placeholder);
        communitygui.setItem(47, placeholder);
        communitygui.setItem(46, closealltickets);
        communitygui.setItem(45, placeholder);

        p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 2F, 1F);

        p.openInventory(communitygui);

    }
}