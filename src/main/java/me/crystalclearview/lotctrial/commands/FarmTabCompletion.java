package me.crystalclearview.lotctrial.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FarmTabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> one = new ArrayList<String>();

        if(args.length == 1){
            one.add("croptrampling");
            one.add("hoe");
            one.add("admin");
        }
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("admin")){
                one.add("setprefix");
                one.add("reload");
            }
            if(args[0].equalsIgnoreCase("croptrampling")){
                for(Player p : Bukkit.getOnlinePlayers()){
                    one.add(p.getName());
                }
            }
        }


        return one;
    }
}
