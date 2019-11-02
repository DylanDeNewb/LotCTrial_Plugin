package me.crystalclearview.lotctrial.Commands;

import me.crystalclearview.lotctrial.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CropTramplingCommand implements CommandExecutor {

    //Linking class to the Main plugin, gives access to methods such as plugin.getConfig() due to the getter and setter.
    public Main plugin;
    public CropTramplingCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //Stopping console from running the command
        if(label.equalsIgnoreCase("croptrampling")){
            if(!(sender instanceof Player)){
                sender.sendMessage("Error: Moderator only command.");
            }
            Player p = (Player) sender;
            //Checking whether or not the player has a certain permission
            if(p.hasPermission("farming.croptrampling")){
                if(plugin.getConfig().getBoolean("crop-trampling") == false){ //Checking if the Boolean is false
                    plugin.getConfig().set("crop-trampling", true); //Changing the String to be true
                    plugin.saveConfig();
                    p.sendMessage(ChatColor.GOLD + "Crop-Trampling has been " + ChatColor.GREEN + "ENABLED");
                } else if(plugin.getConfig().getBoolean("crop-trampling") == true){ //Checking if the String is true
                    plugin.getConfig().set("crop-trampling", false); //Changing the boolean to be false
                    plugin.saveConfig();
                    p.sendMessage(ChatColor.GOLD + "Crop-Trampling has been " + ChatColor.RED + "DISABLED");
                }
            }else{
                //Return message if the player doesn't have the right permission.
                p.sendMessage(ChatColor.RED + "Error:" + ChatColor.WHITE + " Insufficient Permissions");
            }
        }

        return false;
    }
}