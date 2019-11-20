package me.CrystalClearView.LotCTicketSystem;

import me.CrystalClearView.LotCTicketSystem.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static org.spigotmc.SpigotConfig.registerCommands;

public class Ticket extends JavaPlugin {

    TicketFile tf = TicketFile.getInstance();
    public static CommandSender cmds = Bukkit.getConsoleSender();

    public void onEnable(){

        cmds.sendMessage(ChatColor.DARK_GRAY + "------------I------------");
        cmds.sendMessage(ChatColor.GOLD +      "    LotC Ticket System   ");
        cmds.sendMessage(ChatColor.DARK_GREEN +"         Enabled         ");
        cmds.sendMessage(ChatColor.DARK_GRAY + "------------I------------");
        cmds.sendMessage(ChatColor.GOLD +      "    Version: %version%   ".replace("%version", this.getDescription().getVersion()));
        cmds.sendMessage(ChatColor.DARK_GRAY + "------------I------------");

        tf.setup(this);
        registerConfig();
        registerEvents();
        registerCommands();

    }

    private void registerConfig() {

        getConfig().options().copyDefaults(true);
        reloadConfig();
        saveConfig();

    }
    private void registerEvents() {

        PluginManager pm = Bukkit.getServer().getPluginManager();

        pm.registerEvents(new TicketEvents(), this);

    }
    private void registerCommands() {
        getCommand("devreq").setExecutor(new DevTicket(this));
        getCommand("wreq").setExecutor(new WorldTicket(this));
        getCommand("modreq").setExecutor(new ModTicket(this));
        getCommand("creq").setExecutor(new CommunityTicket(this));
        getCommand("sreq").setExecutor(new StoryTicket(this));
        getCommand("devtickets").setExecutor(new DevTicket(this));
        getCommand("worldtickets").setExecutor(new WorldTicket(this));
        getCommand("modtickets").setExecutor(new ModTicket(this));
        getCommand("communitytickets").setExecutor(new CommunityTicket(this));
        getCommand("storytickets").setExecutor(new StoryTicket(this));

    }

    public void onDisable(){
        cmds.sendMessage(ChatColor.DARK_GRAY + "------------I------------");
        cmds.sendMessage(ChatColor.GOLD +      "    LotC Ticket System   ");
        cmds.sendMessage(ChatColor.DARK_RED +  "        Disabled         ");
        cmds.sendMessage(ChatColor.DARK_GRAY + "------------I------------");
        cmds.sendMessage(ChatColor.GOLD +      "    Version: %version%   ".replace("%version", this.getDescription().getVersion()));
        cmds.sendMessage(ChatColor.DARK_GRAY + "------------I------------");
    }

}
