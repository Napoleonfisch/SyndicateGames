package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.SyndicateGames.GameState;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingListener implements Listener{
	
	@EventHandler
	public void onServerListPing(ServerListPingEvent e) {
		if(SyndicateGames.getInstance().getState() == GameState.WAITING) {
			e.setMotd("§7[§2WAITING§7]");
		}else if(SyndicateGames.getInstance().getState() == GameState.INGAME) {
			e.setMotd(ChatColor.GRAY + "[" + ChatColor.RED + "INGAME" + ChatColor.GRAY + "]");
		}else if(SyndicateGames.getInstance().getState() == GameState.ENDING) {
			e.setMotd(ChatColor.GRAY + "[" + ChatColor.YELLOW + "ENDING" + ChatColor.GRAY + "]");
		}
	}

}
