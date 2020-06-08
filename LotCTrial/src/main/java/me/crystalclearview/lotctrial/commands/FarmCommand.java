package me.crystalclearview.lotctrial.commands;

import me.crystalclearview.lotctrial.GuiAPI;
import me.crystalclearview.lotctrial.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

import static org.bukkit.ChatColor.*;
import static org.bukkit.Material.*;

public class FarmCommand implements CommandExecutor {
    //Linking class to the Main plugin, gives access to methods such as plugin.getConfig() due to the getter and setter.
    public Main plugin;
    public FarmCommand(Main plugin) {
        this.plugin = plugin;
    }
    GuiAPI guiApi = new GuiAPI(plugin);


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(RED + "Error: Player only command.");
            return true;
        }

        Player p = (Player) sender;
        Configuration config = plugin.getConfig();

        if(cmd.getName().equalsIgnoreCase("farm")){

            if(args.length > 0){
                String s = args[0];
                switch(s) {
                    case "croptrampling":

                        if(args.length >1){
                            Player target = Bukkit.getPlayer(args[1]);
                            if (p.hasPermission("farming.croptrampling.others") || p.isOp()) {
                                if(target == null){
                                    p.sendMessage(ChatColor.RED + "Invalid player name!");
                                }else{
                                    if(!plugin.getConfig().getBoolean(target.getUniqueId() + ".crop-trampling")){
                                        config.set(target.getUniqueId() + ".crop-trampling", true); //Changing the Boolean to be true
                                        plugin.saveConfig();
                                        plugin.reloadConfig();
                                        p.sendMessage(ChatColor.GOLD + "Crop-Trampling has been " + ChatColor.GREEN + "" + ChatColor.BOLD + "ENABLED for " + target.getName());
                                    }else if (plugin.getConfig().getBoolean(target.getUniqueId() + ".crop-trampling")) { //Checking if the String is true
                                        config.set(target.getUniqueId() + ".crop-trampling", false); //Changing the boolean to be false
                                        plugin.saveConfig();
                                        plugin.reloadConfig();
                                        p.sendMessage(ChatColor.GOLD + "Crop-Trampling has been " + ChatColor.RED + "" + ChatColor.BOLD + "DISABLED for " + target.getName());
                                    }
                                }

                            }else {
                                //Return message if the player doesn't have the right permission.
                                p.sendMessage(ChatColor.RED + "Error:" + ChatColor.WHITE + " Insufficient Permissions");
                            }

                        }
                        if (p.hasPermission("farming.croptrampling") || p.isOp()) {
                            if (!plugin.getConfig().getBoolean(p.getUniqueId() + ".crop-trampling")) { //Checking if the Boolean is false
                                plugin.getConfig().set(p.getUniqueId() + ".crop-trampling", true); //Changing the Boolean to be true
                                plugin.saveConfig();
                                plugin.reloadConfig();
                                p.sendMessage(ChatColor.GOLD + "Crop-Trampling has been " + ChatColor.GREEN + "" + ChatColor.BOLD + "ENABLED");
                            } else if (plugin.getConfig().getBoolean(p.getUniqueId() + ".crop-trampling")) { //Checking if the String is true
                                plugin.getConfig().set(p.getUniqueId() + ".crop-trampling", false); //Changing the boolean to be false
                                plugin.saveConfig();
                                plugin.reloadConfig();
                                p.sendMessage(ChatColor.GOLD + "Crop-Trampling has been " + ChatColor.RED + "" + ChatColor.BOLD + "DISABLED");
                            }
                        } else {
                            //Return message if the player doesn't have the right permission.
                            p.sendMessage(ChatColor.RED + "Error:" + ChatColor.WHITE + " Insufficient Permissions");
                        }

                        break;

                    case "hoe":

                        if(!config.getBoolean("Modules.hoecreation")){
                            p.sendMessage(guiApi.colour(plugin.getConfig().getString("Messages.prefix")) + RED + "This module is disabled!");
                        }

                        if (p.hasPermission("farming.gui.hoe") || p.isOp()) {

                            Inventory craft = Bukkit.createInventory(null,36, GOLD + "" + BOLD + "Hoe Creation");

                            guiApi.addPlaceholderToGUI(craft, 0);
                            guiApi.addPlaceholderToGUI(craft, 9);
                            guiApi.addPlaceholderToGUI(craft, 18);
                            guiApi.addPlaceholderToGUI(craft, 27);
                            guiApi.addPlaceholderToGUI(craft, 8);
                            guiApi.addPlaceholderToGUI(craft, 17);
                            guiApi.addPlaceholderToGUI(craft, 26);
                            guiApi.addPlaceholderToGUI(craft, 35);

                            ArrayList<String> rlore= new ArrayList<String>();
                            rlore.add(GRAY + "- 2 Iron");
                            rlore.add(GRAY + "- 2 Sticks");

                            ArrayList<String> clore= new ArrayList<String>();
                            clore.add(GRAY + "What does this hoe do? Give you a chance for surprises of course!");
                            clore.add(GRAY + "Click to Craft!");

                            ArrayList<String> elore= new ArrayList<String>();
                            elore.add(GRAY + "Dont feel like getting a cool hoe?");
                            elore.add(GRAY + "Click to close!");

                            guiApi.addItemToGUI(craft, PAPER,13, AQUA + "" + BOLD + "Recipe:", rlore);
                            guiApi.addItemToGUI(craft, GREEN_STAINED_GLASS_PANE,20, GREEN + "" + BOLD + "Craft", clore);
                            guiApi.addItemToGUI(craft, RED_STAINED_GLASS_PANE,24, RED + "" + BOLD + "Close", elore);

                            p.openInventory(craft);
                        }
                        break;
                    case "admin":
                        if(!p.hasPermission("farming.admin")){
                            p.sendMessage(RED + "Insufficient permissions!");
                        }
                        if(args.length > 1){
                            if(args[1].equalsIgnoreCase("reload")){
                                p.sendMessage(guiApi.colour(plugin.getConfig().getString("Messages.prefix")) + RED + "Reloading config...");
                                plugin.saveConfig();
                                plugin.reloadConfig();
                                p.sendMessage(guiApi.colour(plugin.getConfig().getString("Messages.prefix")) + GREEN + "Reloaded config!");
                            }else if(args[1].equalsIgnoreCase("setprefix")){
                                StringBuilder sb = new StringBuilder();
                                for(int i=2; i < args.length; i++){
                                    sb.append(args[i] + " ");
                                }
                                plugin.getConfig().set("Messages.prefix", sb.toString());
                                plugin.saveConfig();
                                plugin.reloadConfig();
                                p.sendMessage(guiApi.colour(plugin.getConfig().getString("Messages.prefix")) + GREEN + "Prefix set!");
                            }
                        }else{
                            p.sendMessage(AQUA + "-== Sub-commands for " + GRAY + "admin" + AQUA + " ==-");
                            p.sendMessage(GOLD + "setprefix" + AQUA + ": " + GRAY + "Set plugin prefix");
                            p.sendMessage(GOLD + "reload" + AQUA + ": " + GRAY + "Reload plugin config");
                        }
                }
            }else{

                p.sendMessage(AQUA + "-== Sub-commands for " + GRAY + "/farm" + AQUA + " ==-");
                p.sendMessage(GOLD + "hoe" + AQUA + ": " + GRAY + "Open up the Hoe Creation Menu");
                p.sendMessage(GOLD + "croptrampling" + AQUA + ": " + GRAY + "Toggle Crop-Trampling locally");
                p.sendMessage(GOLD + "admin" + AQUA + ": " + GRAY + "See admin commands");
                if(p.hasPermission("farming.croptrampling.others") || p.isOp()){
                    p.sendMessage(DARK_GRAY + "" + ITALIC + "You have the farming.croptrampling.others permission, \nMeaning you can toggle others' crop trampling by parsing player");
                }

            }


        }

        return false;
    }
}
