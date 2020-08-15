package me.crystalclearview.lotctrial.levelling;

import me.crystalclearview.lotctrial.LotCTrial;
import org.bukkit.entity.Player;
import static org.bukkit.ChatColor.*;

public class XPCheck {

    public LotCTrial plugin;
    public XPCheck(LotCTrial plugin) {
        this.plugin = plugin;
    }

    public void xpCheck(Player p, PlayerLevel levelManager){

        int xp = levelManager.getXp();
        int level = levelManager.getLevel() + 1;
        int requiredXP = plugin.getConfig().getInt("Levels." + (levelManager.getLevel() + 1) + ".xp");

        if(requiredXP >= xp){
            levelManager.setLevel(level);
            p.sendTitle(GOLD + "Level " + AQUA + "UP!", GRAY + "(" + levelManager.getLevel() + ")", 2, 5, 2);
        }
    }
}
