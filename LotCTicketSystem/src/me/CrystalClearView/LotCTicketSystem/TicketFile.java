package me.CrystalClearView.LotCTicketSystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class TicketFile {

    static TicketFile instance = new TicketFile();
    Plugin p;
    FileConfiguration data;
    public static File tfile;

    public static TicketFile getInstance(){
        return instance;
    }

    public void setup(Plugin p){
        if(!p.getDataFolder().exists()){
            p.getDataFolder().mkdirs();
        }
        File path = new File(p.getDataFolder() + File.separator + "/data");
        tfile = new File(path, File.separator + "tickets.yml");

        if(!tfile.exists()){
            try {
                path.mkdirs();
                tfile.createNewFile();
            }catch(IOException o){
                Bukkit.getServer().getLogger().warning("Could not generate file 'tickets.yml'");
            }
        }
        this.data = YamlConfiguration.loadConfiguration(tfile);
    }

    public FileConfiguration getData(){
        return this.data;
    }

    public void saveData(){
        try{
            this.data.save(tfile);
        }catch(IOException o){
            Bukkit.getServer().getLogger().warning(ChatColor.DARK_RED + "Could not save file 'tickets.yml'");
        }
    }

    public void reloadData(){

    }
}
