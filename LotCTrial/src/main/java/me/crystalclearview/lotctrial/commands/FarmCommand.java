package me.crystalclearview.lotctrial.commands;

import me.crystalclearview.lotctrial.API;
import me.crystalclearview.lotctrial.Main;
import me.crystalclearview.lotctrial.guis.HoeCreation;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class FarmCommand implements CommandExecutor {
    //Linking class to the Main plugin, gives access to methods such as plugin.getConfig() due to the getter and setter.
    public Main plugin;
    public FarmCommand(Main plugin) {
        this.plugin = plugin;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;
        Configuration config = plugin.getConfig();
        API api = new API(plugin);

        if(cmd.getName().equalsIgnoreCase("farm")){

            if(args.length > 0){
                String s = args[0];
                switch(s) {
                    case "croptrampling":

                        api.toggleCTramp(p, args);

                        break;

                    case "hoe":
                        if (!(sender instanceof Player)) {
                            sender.sendMessage("Error: Player only command.");
                        }
                        if(!config.getBoolean("Modules.hoecreation")){
                            p.sendMessage(ChatColor.RED + "This module is disabled!");
                        }

                        if (p.hasPermission("farming.gui.hoe") || p.isOp()) {

                            HoeCreation.openHoeMenu(p);

                        }
                        break;
                    case "admin":
                        if(!p.hasPermission("farming.admin")){
                            p.sendMessage(ChatColor.RED + "Insufficient permissions!");
                        }
                        p.sendMessage(ChatColor.AQUA + "-== Sub-commands for " + ChatColor.GRAY + "admin" + ChatColor.AQUA + " ==-");
                        p.sendMessage(ChatColor.GOLD + "reload" + ChatColor.AQUA + ": " + ChatColor.GRAY + "Reload plugin config");
                }
            }else{
                if(args.length > 1){

                }
                p.sendMessage(ChatColor.AQUA + "-== Sub-commands for " + ChatColor.GRAY + "/farm" + ChatColor.AQUA + " ==-");
                p.sendMessage(ChatColor.GOLD + "hoe" + ChatColor.AQUA + ": " + ChatColor.GRAY + "Open up the Hoe Creation Menu");
                p.sendMessage(ChatColor.GOLD + "croptrampling" + ChatColor.AQUA + ": " + ChatColor.GRAY + "Toggle Crop-Trampling locally");
                p.sendMessage(ChatColor.GOLD + "admin" + ChatColor.AQUA + ": " + ChatColor.GRAY + "See admin commands");
                if(p.hasPermission("farming.croptrampling.others") || p.isOp()){
                    p.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "You have the farming.croptrampling.others permission, \nMeaning you can toggle others' crop trampling by parsing player");
                }

            }


        }

        return false;
    }
}
