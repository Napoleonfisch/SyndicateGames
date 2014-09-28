package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.mysql.Points;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener{
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if(SyndicateGames.getInstance().finishedSetup()) {
			e.setFormat(ChatColor.GRAY+"["+ChatColor.GOLD+Points.getPoints(e.getPlayer().getUniqueId().toString())+""+ChatColor.GRAY+"]"+ChatColor.GREEN+e.getPlayer().getName()+ChatColor.GRAY+": "+ChatColor.AQUA+""+ChatColor.translateAlternateColorCodes('&', e.getMessage()));
		}
	}

}
