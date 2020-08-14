package me.crystalclearview.lotctrial.apis;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class MessageAPI {

    public void sendActionbar(String message, Player p){

        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));

    }

    public void sendTitle(String title, String subtitle, int fadein, int stay, int fadeout, Player p){

        p.sendTitle(title,subtitle,fadein,stay,fadeout);

    }
}
