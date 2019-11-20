package me.CrystalClearView.LotCTicketSystem.commands;

import me.CrystalClearView.LotCTicketSystem.Ticket;
import me.CrystalClearView.LotCTicketSystem.TicketAPI;
import me.CrystalClearView.LotCTicketSystem.TicketFile;
import me.CrystalClearView.LotCTicketSystem.guis.DevGUI;
import me.CrystalClearView.LotCTicketSystem.guis.WorldGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class WorldTicket implements CommandExecutor {

    TicketFile tf = TicketFile.getInstance();
    Ticket plugin;

    public WorldTicket(Ticket instance){
        plugin = instance;
    }

    private HashMap<Player, BukkitRunnable> cooldownTask = new HashMap<Player, BukkitRunnable>();
    private HashMap<Player, Integer> cooldownTime = new HashMap<Player, Integer>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("wreq")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(cooldownTime.containsKey(p)){
                    p.sendMessage(plugin.getConfig().getString("Messages.prefix") + ChatColor.DARK_GRAY + " You are on cooldown for %cooldowntime% seconds!".replace("%cooldowntime", tf.getData().getString("cooldowntime")) + cooldownTime.values());
                }else {
                    if(args.length == 0){
                        p.sendMessage(plugin.getConfig().getString("Messages.worldprefix") + plugin.getConfig().getString("Messages.worldticketusage"));
                    }
                    if(args.length >= 1){

                        StringBuilder sb = new StringBuilder();
                        for(int i=0; i < args.length; i++){
                            sb.append(args[i] + " ");
                        }
                        tf.getData().set("Tickets." + p.getUniqueId() + ".world" + ".description", sb.toString());

                        tf.getData().set("Tickets." + p.getUniqueId() + ".world" + ".creator", p.getName());

                        tf.saveData();

                        TicketAPI.addWorldTicket(p, WorldGUI.worldgui, TicketAPI.slotmax);

                        tf.getData().set("Tickets."+p.getUniqueId()+".world"+".world", p.getWorld().toString());
                        tf.getData().set("Tickets."+p.getUniqueId()+".world"+".x", p.getLocation().getX());
                        tf.getData().set("Tickets."+p.getUniqueId()+".world"+".y", p.getLocation().getY());
                        tf.getData().set("Tickets."+p.getUniqueId()+".world"+".z", p.getLocation().getZ());
                        tf.getData().set("Tickets."+p.getUniqueId()+".world"+".yaw", p.getLocation().getYaw());
                        tf.getData().set("Tickets."+p.getUniqueId()+".world"+".pitch", p.getLocation().getPitch());
                        tf.saveData();

                        p.sendMessage(tf.getData().getString("Messages.worldprefix") + ChatColor.DARK_GRAY + "Ticket created! You are now on a cooldown for 60 seconds!");

                        for(Player players : Bukkit.getOnlinePlayers()){
                            if(players.hasPermission("tickets.worldtickets") || players.isOp()){
                                players.sendMessage(ChatColor.DARK_GRAY +      "------------I------------");
                                players.sendMessage(ChatColor.DARK_GREEN +     "        NEW TICKET       ");
                                players.sendMessage(ChatColor.DARK_GRAY +      "------------I------------");
                                players.sendMessage(ChatColor.GOLD +           " Submitted by: %creator% ".replace("%creator%", tf.getData().getString("Tickets." + p.getUniqueId() + ".world" + ".creator")));
                                players.sendMessage(ChatColor.DARK_GRAY +      "------------I------------");
                            } else {
                                p.sendMessage(tf.getData().getString("Messages.worldprefix") + tf.getData().getString("Messages.no-perm") + "tickets.worldtickets");
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
        } else if(cmd.getName().equalsIgnoreCase("worldtickets")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(p.hasPermission("tickets.worldtickets")){
                    WorldGUI.openWorldTickets(p);
                }else {
                    p.sendMessage(tf.getData().getString("Messages.worldprefix") + tf.getData().getString("Messages.no-perm") + "tickets.worldtickets");
                }
            }else {
                sender.sendMessage(plugin.getConfig().getString("Messages.prefix") + ChatColor.DARK_GRAY + " This is a player only command!");
            }
        }

        return false;
    }
}

