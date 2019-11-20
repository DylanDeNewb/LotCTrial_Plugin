package me.CrystalClearView.LotCTicketSystem.commands;

import me.CrystalClearView.LotCTicketSystem.Ticket;
import me.CrystalClearView.LotCTicketSystem.TicketAPI;
import me.CrystalClearView.LotCTicketSystem.TicketFile;
import me.CrystalClearView.LotCTicketSystem.guis.StoryGUI;
import me.CrystalClearView.LotCTicketSystem.guis.ModGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class StoryTicket implements CommandExecutor {

    TicketFile tf = TicketFile.getInstance();
    Ticket plugin;

    public StoryTicket(Ticket instance){
        plugin = instance;
    }

    private HashMap<Player, BukkitRunnable> cooldownTask = new HashMap<Player, BukkitRunnable>();
    private HashMap<Player, Integer> cooldownTime = new HashMap<Player, Integer>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("sreq")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(cooldownTime.containsKey(p)){
                    p.sendMessage(plugin.getConfig().getString("Messages.prefix") + ChatColor.DARK_GRAY + " You are on cooldown for %cooldowntime% seconds!".replace("%cooldowntime", tf.getData().getString("cooldowntime")) + cooldownTime.values());
                }else {
                    if(args.length == 0){
                        p.sendMessage(plugin.getConfig().getString("Messages.storyprefix") + plugin.getConfig().getString("Messages.storyticketusage"));
                    }
                    if(args.length >= 1){

                        StringBuilder sb = new StringBuilder();
                        for(int i=0; i < args.length; i++){
                            sb.append(args[i] + " ");
                        }
                        tf.getData().set("Tickets." + p.getUniqueId() + ".story" + ".description", sb.toString());

                        tf.getData().set("Tickets." + p.getUniqueId() + ".story" + ".creator", p.getName());

                        tf.saveData();

                        TicketAPI.addStoryTicket(p, StoryGUI.storygui, TicketAPI.slotmax);

                        tf.getData().set("Tickets."+p.getUniqueId()+".story"+".world", p.getWorld().toString());
                        tf.getData().set("Tickets."+p.getUniqueId()+".story"+".x", p.getLocation().getX());
                        tf.getData().set("Tickets."+p.getUniqueId()+".story"+".y", p.getLocation().getY());
                        tf.getData().set("Tickets."+p.getUniqueId()+".story"+".z", p.getLocation().getZ());
                        tf.getData().set("Tickets."+p.getUniqueId()+".story"+".yaw", p.getLocation().getYaw());
                        tf.getData().set("Tickets."+p.getUniqueId()+".story"+".pitch", p.getLocation().getPitch());
                        tf.saveData();

                        p.sendMessage(tf.getData().getString("Messages.storyprefix") + ChatColor.DARK_GRAY + "Ticket created! You are now on a cooldown for 60 seconds!");

                        for(Player players : Bukkit.getOnlinePlayers()){
                            if(players.hasPermission("tickets.storytickets") || players.isOp()){
                                players.sendMessage(ChatColor.DARK_GRAY +      "------------I------------");
                                players.sendMessage(ChatColor.DARK_GREEN +     "        NEW TICKET       ");
                                players.sendMessage(ChatColor.DARK_GRAY +      "------------I------------");
                                players.sendMessage(ChatColor.GOLD +           " Submitted by: %creator% ".replace("%creator%", tf.getData().getString("Tickets." + p.getUniqueId() + ".story" + ".creator")));
                                players.sendMessage(ChatColor.DARK_GRAY +      "------------I------------");
                            } else {
                                p.sendMessage(tf.getData().getString("Messages.storyprefix") + tf.getData().getString("Messages.no-perm") + "tickets.storytickets");
                            }
                        }
                        cooldownTime.put(p, tf.getData().getInt("cooldowntime"));
                        cooldownTask.put(p, new BukkitRunnable() {
                            public void run() {
                                cooldownTime.put(p, cooldownTime.get(p) - 1);
                                if(cooldownTime.get(p) == 0){
                                    cooldownTask.remove(p);
                                    cancel();
                                }
                            }
                        });
                        cooldownTask.get(p).runTaskTimer(plugin, 20, 20);

                    }
                }
            }else {
                sender.sendMessage(plugin.getConfig().getString("Messages.prefix") + ChatColor.DARK_GRAY + " This is a player only command!");
            }
        } else if(cmd.getName().equalsIgnoreCase("storytickets")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(p.hasPermission("tickets.storytickets")){
                    ModGUI.openModTickets(p);
                }else {
                    p.sendMessage(tf.getData().getString("Messages.storyprefix") + tf.getData().getString("Messages.no-perm") + "tickets.storytickets");
                }
            }else {
                sender.sendMessage(plugin.getConfig().getString("Messages.prefix") + ChatColor.DARK_GRAY + " This is a player only command!");
            }
        }

        return false;
    }}