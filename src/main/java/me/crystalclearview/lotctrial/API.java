package me.crystalclearview.lotctrial;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class API {

    public Main plugin;
    public API(Main plugin) {
        this.plugin = plugin;
    }

    public String colour(String m){
        return ChatColor.translateAlternateColorCodes('&',m);
    }

    public void toggleCTramp(Player p, String[] args){

        Configuration config = plugin.getConfig();

        if(args.length >1){
            Player target = Bukkit.getPlayer(args[1]);
            if (p.hasPermission("farming.croptrampling.others") || p.isOp()) {
                if(target == null){
                    p.sendMessage(ChatColor.RED + "Invalid player name!");
                }else{
                    if(!plugin.getConfig().getBoolean(target.getName() + ".crop-trampling")){
                        config.set(target.getName() + ".crop-trampling", true); //Changing the Boolean to be true
                        plugin.saveConfig();
                        p.sendMessage(ChatColor.GOLD + "Crop-Trampling has been " + ChatColor.GREEN + "" + ChatColor.BOLD + "ENABLED for " + target.getName());
                    }else if (plugin.getConfig().getBoolean(target.getName() + ".crop-trampling")) { //Checking if the String is true
                        config.set(p.getName() + ".crop-trampling", false); //Changing the boolean to be false
                        plugin.saveConfig();
                        p.sendMessage(ChatColor.GOLD + "Crop-Trampling has been " + ChatColor.RED + "" + ChatColor.BOLD + "DISABLED for " + target.getName());
                    }
                }
                return;

            }else {
                //Return message if the player doesn't have the right permission.
                p.sendMessage(ChatColor.RED + "Error:" + ChatColor.WHITE + " Insufficient Permissions");
            }

        }
        if (p.hasPermission("farming.croptrampling") || p.isOp()) {
            if (!plugin.getConfig().getBoolean(p.getName() + ".crop-trampling")) { //Checking if the Boolean is false
                plugin.getConfig().set(p.getName() + ".crop-trampling", true); //Changing the Boolean to be true
                plugin.saveConfig();
                p.sendMessage(ChatColor.GOLD + "Crop-Trampling has been " + ChatColor.GREEN + "" + ChatColor.BOLD + "ENABLED");
                return;
            } else if (plugin.getConfig().getBoolean(p.getName() + ".crop-trampling")) { //Checking if the String is true
                plugin.getConfig().set(p.getName() + ".crop-trampling", false); //Changing the boolean to be false
                plugin.saveConfig();
                p.sendMessage(ChatColor.GOLD + "Crop-Trampling has been " + ChatColor.RED + "" + ChatColor.BOLD + "DISABLED");
                return;
            }
        } else {
            //Return message if the player doesn't have the right permission.
            p.sendMessage(ChatColor.RED + "Error:" + ChatColor.WHITE + " Insufficient Permissions");
            return;
        }
    }
}
