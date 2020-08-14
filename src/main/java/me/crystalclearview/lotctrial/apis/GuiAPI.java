package me.crystalclearview.lotctrial.apis;

import me.crystalclearview.lotctrial.LotCTrial;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GuiAPI {

    public LotCTrial plugin;
    public GuiAPI(LotCTrial plugin) {
        this.plugin = plugin;
    }

    public String colour(String m){
        return ChatColor.translateAlternateColorCodes('&',m);
    }

    public void addItemToGUI(Inventory inv, Material material, int slot, String name, ArrayList<String> lore){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);

        item.setItemMeta(meta);

        inv.setItem(slot, item);
    }
    public void addPlaceholderToGUI(Inventory inv, int slot){
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("");
        item.setItemMeta(meta);
        inv.setItem(slot, item);
    }

}
