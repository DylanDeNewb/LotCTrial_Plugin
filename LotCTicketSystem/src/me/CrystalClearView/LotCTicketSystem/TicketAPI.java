package me.CrystalClearView.LotCTicketSystem;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TicketAPI {

    static TicketFile tf = TicketFile.getInstance();

    public static int slotmax = 0;

    public static ItemStack newdevticket = new ItemStack(Material.PAPER);

    public static Date ticketdate = new Date();
    public static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static boolean isdevopen = false;
    public static boolean isworldopen = false;
    public static boolean ismodopen = false;
    public static boolean iscommunityopen = false;
    public static boolean isstoryopen = false;

    public static boolean getDevTicket(Player p){

        if(tf.getData().getString("Tickets." + p.getUniqueId() + ".dev") == null){
            isdevopen = false;
        }else {
            isdevopen = true;
        }
        return isdevopen;

    }
    public static boolean getWorldTicket(Player p){

        if(tf.getData().getString("Tickets." + p.getUniqueId() + ".world") == null){
            isworldopen = false;
        }else {
            isworldopen = true;
        }
        return isworldopen;

    }
    public static boolean getModTicket(Player p){

        if(tf.getData().getString("Tickets." + p.getUniqueId() + ".mod") == null){
            isdevopen = false;
        }else {
            isdevopen = true;
        }
        return isdevopen;

    }
    public static boolean getCommunityTicket(Player p){

        if(tf.getData().getString("Tickets." + p.getUniqueId() + ".community") == null){
            iscommunityopen = false;
        }else {
            iscommunityopen = true;
        }
        return iscommunityopen;

    }
    public static boolean getStoryTicket(Player p){

        if(tf.getData().getString("Tickets." + p.getUniqueId() + ".story") == null){
            isstoryopen = false;
        }else {
            isstoryopen = true;
        }
        return isstoryopen;

    }
    public static String getDevOwner(Player p){
        String creator = tf.getData().getString("Tickets." + p.getUniqueId() + ".dev" + ".creator");
        return creator;
    }
    public static String getWorldOwner(Player p){
        String creator = tf.getData().getString("Tickets." + p.getUniqueId() + ".dev" + ".creator");
        return creator.toString();
    }
    public static String getModOwner(Player p){
        String creator = tf.getData().getString("Tickets." + p.getUniqueId() + ".dev" + ".creator");
        return creator.toString();
    }
    public static String getCommunityOwner(Player p){
        String creator = tf.getData().getString("Tickets." + p.getUniqueId() + ".dev" + ".creator");
        return creator.toString();
    }
    public static String getStoryOwner(Player p){
        String creator = tf.getData().getString("Tickets." + p.getUniqueId() + ".dev" + ".creator");
        return creator.toString();
    }
    public static void clearDevTickets(Player p){
        if(getDevTicket(p) == false){
            return;
        } else {
            tf.getData().set("Tickets." + p.getUniqueId() + ".dev", null);
            tf.saveData();
        }
    }
    public static void clearWorldTickets(Player p){
        if(getWorldTicket(p) == false){
            return;
        } else {
            tf.getData().set("Tickets." + p.getUniqueId() + ".world", null);
            tf.saveData();
        }
    }
    public static void clearModTickets(Player p){
        if(getModTicket(p) == false){
            return;
        } else {
            tf.getData().set("Tickets." + p.getUniqueId() + ".mod", null);
            tf.saveData();
        }
    }
    public static void clearCommunityTickets(Player p){
        if(getCommunityTicket(p) == false){
            return;
        } else {
            tf.getData().set("Tickets." + p.getUniqueId() + ".community", null);
            tf.saveData();
        }
    }
    public static void clearStoryTickets(Player p){
        if(getStoryTicket(p) == false){
            return;
        } else {
            tf.getData().set("Tickets." + p.getUniqueId() + ".story", null);
            tf.saveData();
        }
    }
    public static void addDevTicket(Player p, Inventory inv, int slot){
        if(getDevTicket(p) == false){
            return;
        }else{
            ItemStack newticket = new ItemStack(Material.PAPER);
            ItemMeta newticketmeta = newticket.getItemMeta();
            List<String> newticketlore = new ArrayList<String>();
            newticketmeta.setDisplayName(p.getName());
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketlore.add(ChatColor.DARK_GREEN +     "Description:             ");
            newticketlore.add(ChatColor.GOLD+            "%description%            ".replace("%description%", tf.getData().getString("Tickets." + p.getUniqueId() + ".dev" + ".description")));
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketlore.add(ChatColor.GOLD +           "Claimee: %claimee%       ".replace("%claimee%", TicketAPI.claimee(newticket, p)));
            newticketlore.add(ChatColor.GOLD +           "Date Created: %date      ".replace("%date%","Tickets." + p.getUniqueId() + ".dev" + ".datecreated"));
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketlore.add(ChatColor.GOLD +           "    Click to open menu   ");
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketmeta.setLore(newticketlore);
            newticket.setItemMeta(newticketmeta);
            if(slotmax > slot){
                inv.setItem(slot++, newticket);
            }else {
                inv.setItem(slot, newticket);
            }
            slotmax++;
            tf.getData().set("Tickets." + p.getUniqueId() + ".dev" + ".datecreated", format.format(ticketdate));


        }
    }
    public static String claimee(ItemStack item, Player p){
        if(tf.getData().getString("Tickets." + getUuid(item.getItemMeta().getDisplayName()) + ".dev" + ".claimee") == null){
            return "Nobody";
        }else {
            return tf.getData().getString("Tickets." + getUuid(item.getItemMeta().getDisplayName()) + ".dev" + ".claimee");
        }
    }

    public static void addWorldTicket(Player p, Inventory inv, int slot){
        if(getWorldTicket(p) == false){
            return;
        }else{

            ItemMeta newticketmeta = newdevticket.getItemMeta();
            List<String> newticketlore = new ArrayList<String>();
            newticketmeta.setDisplayName(p.getName());
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketlore.add(ChatColor.DARK_GREEN +     "Description:             ");
            newticketlore.add(ChatColor.GOLD+            "%description%            ".replace("%description%", tf.getData().getString("Tickets." + p.getUniqueId() + ".world" + ".description")));
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketlore.add(ChatColor.GOLD +           "    Click to open menu   ");
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketmeta.setLore(newticketlore);
            newdevticket.setItemMeta(newticketmeta);
            if(slotmax > slot){
                inv.setItem(slot++, newdevticket);
            }else {
                inv.setItem(slot, newdevticket);
            }
            slotmax++;

        }
    }
    public static void addModTicket(Player p, Inventory inv, int slot){
        if(getModTicket(p) == false){
            return;
        }else{
            ItemStack newticket = new ItemStack(Material.PAPER);
            ItemMeta newticketmeta = newticket.getItemMeta();
            List<String> newticketlore = new ArrayList<String>();
            newticketmeta.setDisplayName(p.getName());
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketlore.add(ChatColor.DARK_GREEN +     "Description:             ");
            newticketlore.add(ChatColor.GOLD+            "%description%            ".replace("%description%", tf.getData().getString("Tickets." + p.getUniqueId() + ".mod" + ".description")));
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketlore.add(ChatColor.GOLD +           "    Click to open menu   ");
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketmeta.setLore(newticketlore);
            newticket.setItemMeta(newticketmeta);
            if(slotmax > slot){
                inv.setItem(slot++, newticket);
            }else {
                inv.setItem(slot, newticket);
            }
            slotmax++;

        }
    }
    public static void addCommunityTicket(Player p, Inventory inv, int slot){
        if(getCommunityTicket(p) == false){
            return;
        }else{
            ItemStack newticket = new ItemStack(Material.PAPER);
            ItemMeta newticketmeta = newticket.getItemMeta();
            List<String> newticketlore = new ArrayList<String>();
            newticketmeta.setDisplayName(p.getName());
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketlore.add(ChatColor.DARK_GREEN +     "Description:             ");
            newticketlore.add(ChatColor.GOLD+            "%description%            ".replace("%description%", tf.getData().getString("Tickets." + p.getUniqueId() + ".community" + ".description")));
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketlore.add(ChatColor.GOLD +           "    Click to open menu   ");
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketmeta.setLore(newticketlore);
            newticket.setItemMeta(newticketmeta);
            if(slotmax > slot){
                inv.setItem(slot++, newticket);
            }else {
                inv.setItem(slot, newticket);
            }
            slotmax++;

        }
    }
    public static void addStoryTicket(Player p, Inventory inv, int slot){
        if(getStoryTicket(p) == false){
            return;
        }else{
            ItemStack newticket = new ItemStack(Material.PAPER);
            ItemMeta newticketmeta = newticket.getItemMeta();
            List<String> newticketlore = new ArrayList<String>();
            newticketmeta.setDisplayName(p.getName());
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketlore.add(ChatColor.DARK_GREEN +     "Description:             ");
            newticketlore.add(ChatColor.GOLD+            "%description%            ".replace("%description%", tf.getData().getString("Tickets." + p.getUniqueId() + ".story" + ".description")));
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketlore.add(ChatColor.GOLD +           "    Click to open menu   ");
            newticketlore.add(ChatColor.DARK_GRAY +      "------------I------------");
            newticketmeta.setLore(newticketlore);
            newticket.setItemMeta(newticketmeta);
            if(slotmax > slot){
                inv.setItem(slot++, newticket);
            }else {
                inv.setItem(slot, newticket);
            }
            slotmax++;

        }
    }

    public static void removeDevTicket(Player clickedreport, Inventory inv, int slot){

        ItemStack air = new ItemStack(Material.AIR);
        inv.setItem(slot, air);
        clearDevTickets(clickedreport);
        slotmax = slot;

    }
    public static void removeWorldTicket(Player clickedreport, Inventory inv, int slot){

        ItemStack air = new ItemStack(Material.AIR);
        inv.setItem(slot, air);
        clearWorldTickets(clickedreport);
        slotmax = slot;

    }
    public static void removeModTicket(Player clickedreport, Inventory inv, int slot){

        ItemStack air = new ItemStack(Material.AIR);
        inv.setItem(slot, air);
        clearModTickets(clickedreport);
        slotmax = slot;

    }
    public static void removeCommunityTicket(Player clickedreport, Inventory inv, int slot){

        ItemStack air = new ItemStack(Material.AIR);
        inv.setItem(slot, air);
        clearCommunityTickets(clickedreport);
        slotmax = slot;

    }
    public static void removeStoryTicket(Player clickedreport, Inventory inv, int slot){

        ItemStack air = new ItemStack(Material.AIR);
        inv.setItem(slot, air);
        clearStoryTickets(clickedreport);
        slotmax = slot;

    }
    public static UUID getUuid(String name) {

        return Bukkit.getPlayer(name).getUniqueId();
    }
    public static String getName(String uuid) {

        return Bukkit.getPlayer(UUID.fromString(uuid)).getDisplayName();
    }




}
