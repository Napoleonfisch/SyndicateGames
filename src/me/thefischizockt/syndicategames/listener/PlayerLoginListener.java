package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.SyndicateGames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener{
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		if(SyndicateGames.getInstance().finishedSetup()) {
			if(Bukkit.getOnlinePlayers().length == 1) {
				e.setKickMessage(ChatColor.RED + "Die SyndicateGames haben schon 2 Spieler");
			}
		}
	}

}
