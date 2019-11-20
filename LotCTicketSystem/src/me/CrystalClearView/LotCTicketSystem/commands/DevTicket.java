package me.CrystalClearView.LotCTicketSystem.commands;

import me.CrystalClearView.LotCTicketSystem.Ticket;
import me.CrystalClearView.LotCTicketSystem.TicketAPI;
import me.CrystalClearView.LotCTicketSystem.TicketFile;
import me.CrystalClearView.LotCTicketSystem.guis.DevGUI;
import net.minecraft.server.v1_13_R2.IChatBaseComponent;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;
import net.minecraft.server.v1_13_R2.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class DevTicket implements CommandExecutor {

    TicketFile tf = TicketFile.getInstance();
    Ticket plugin;

    public DevTicket(Ticket instance){
        plugin = instance;
    }

    private HashMap<Player, BukkitRunnable> cooldownTask = new HashMap<Player, BukkitRunnable>();
    private HashMap<Player, Integer> cooldownTime = new HashMap<Player, Integer>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("devreq")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(cooldownTime.containsKey(p)){
                    p.sendMessage(plugin.getConfig().getString("Messages.prefix") + ChatColor.DARK_GRAY + " You are on cooldown for %cooldowntime% seconds!".replace("%cooldowntime", tf.getData().getString("cooldowntime")) + cooldownTime.values());
                }else {
                    if(args.length == 0){
                        p.sendMessage(plugin.getConfig().getString("Messages.devprefix") + plugin.getConfig().getString("Messages.devticketusage"));
                    }
                    if(args.length >= 1){

                        StringBuilder sb = new StringBuilder();
                        for(int i=0; i < args.length; i++){
                            sb.append(args[i] + " ");
                        }
                        tf.getData().set("Tickets." + p.getUniqueId() + ".dev" + ".description", sb.toString());

                        tf.getData().set("Tickets." + p.getUniqueId() + ".dev" + ".creator", p.getName());

                        tf.saveData();

                        TicketAPI.addDevTicket(p, DevGUI.devgui, TicketAPI.slotmax);

                        tf.getData().set("Tickets."+p.getUniqueId()+".dev"+".world", p.getWorld().toString());
                        tf.getData().set("Tickets."+p.getUniqueId()+".dev"+".x", p.getLocation().getX());
                        tf.getData().set("Tickets."+p.getUniqueId()+".dev"+".y", p.getLocation().getY());
                        tf.getData().set("Tickets."+p.getUniqueId()+".dev"+".z", p.getLocation().getZ());
                        tf.getData().set("Tickets."+p.getUniqueId()+".dev"+".yaw", p.getLocation().getYaw());
                        tf.getData().set("Tickets."+p.getUniqueId()+".dev"+".pitch", p.getLocation().getPitch());
                        tf.saveData();

                        p.sendMessage(tf.getData().getString("Messages.devprefix") + ChatColor.DARK_GRAY + "Ticket created! You are now on a cooldown for 60 seconds!");

                        for(Player players : Bukkit.getOnlinePlayers()){
                            if(players.hasPermission("tickets.devtickets") || players.isOp()){

                                PlayerConnection connection = ((CraftPlayer) players).getHandle().playerConnection;
                                PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"NEW TICKET\",\"bold\":true,\"italic\":true,\"color\":\"dark_green\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/devtickets\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":[\"\",{\"text\":\"Click here to see Dev Tickets!\",\"bold\":true,\"italic\":true,\"color\":\"dark_gray\"}]}}]"));

                                players.sendMessage(ChatColor.DARK_GRAY +      "------------I------------");
                                connection.sendPacket(packet);
                                players.sendMessage(ChatColor.DARK_GRAY +      "------------I------------");
                                players.sendMessage(ChatColor.GOLD +           " Submitted by: %creator% ".replace("%creator%", tf.getData().getString("Tickets." + p.getUniqueId() + ".dev" + ".creator")));
                                players.sendMessage(ChatColor.DARK_GRAY +      "------------I------------");
                            } else {
                                p.sendMessage(tf.getData().getString("Messages.devprefix") + tf.getData().getString("Messages.no-perm") + "tickets.devtickets");
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
        } else if(cmd.getName().equalsIgnoreCase("devtickets")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(p.hasPermission("tickets.devtickets")){
                    DevGUI.openDevTickets(p);
                }else {
                    p.sendMessage(tf.getData().getString("Messages.devprefix") + tf.getData().getString("Messages.no-perm") + "tickets.devtickets");
                }
            }else {
                sender.sendMessage(plugin.getConfig().getString("Messages.prefix") + ChatColor.DARK_GRAY + " This is a player only command!");
            }
        }

        return false;
    }
}
