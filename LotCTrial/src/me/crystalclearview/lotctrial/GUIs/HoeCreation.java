package me.crystalclearview.lotctrial.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class HoeCreation implements Listener {

    public static void openHoeMenu(Player p){
        Inventory craft = Bukkit.createInventory(null,36, ChatColor.GOLD + "" + ChatColor.BOLD + "Hoe Creation");

        ItemStack recipe = new ItemStack(Material.BOOK);
        ItemMeta rmeta = recipe.getItemMeta();
        ArrayList<String> rlore= new ArrayList<String>();

        ItemStack crafti = new ItemStack(Material.GREEN_WOOL);
        ItemMeta cmeta = crafti.getItemMeta();
        ArrayList<String> clore= new ArrayList<String>();

        rmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&lRecipe:"));
        rlore.add(ChatColor.translateAlternateColorCodes('&', "&7- &82 Iron"));
        rlore.add(ChatColor.translateAlternateColorCodes('&', "&7- &82 Sticks"));

        cmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&b&2Craft"));
        clore.add(ChatColor.translateAlternateColorCodes('&', "&7What does this hoe do? Give you a chance for surpises of course!"));
        clore.add(ChatColor.translateAlternateColorCodes('&', "&7Got the resources required? Simply click!"));

        rmeta.setLore(rlore);
        recipe.setItemMeta(rmeta);

        cmeta.setLore(clore);
        crafti.setItemMeta(cmeta);

        craft.setItem( 13, recipe);
        craft.setItem(20, crafti);

        p.openInventory(craft);
    }

}
