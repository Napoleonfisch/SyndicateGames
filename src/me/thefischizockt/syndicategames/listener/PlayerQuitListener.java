package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.BuildManager;
import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener{
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		try{
			if(SyndicateGames.getInstance().finishedSetup()) {
				BuildManager.getInstance().removePlayer(e.getPlayer());
				SyndicateGames.getInstance().sendWinner(Bukkit.getOnlinePlayers()[0], e.getPlayer(), true);
			}else {
				if(SyndicateGames.getInstance().getOnlinePlayers() <=2 && SyndicateGames.getInstance().getOnlinePlayers() >= 0) {
					SyndicateGames.getInstance().setOnlinePlayers(SyndicateGames.getInstance().getOnlinePlayers()-1);
				}else {
					MessageManager.getInstance().sendMessageToConsole(MessageType.BAD, "onlinePlayers sollte nicht größer sein als 2 und kleiner als 0");
					Bukkit.shutdown();
				}
			}
		}catch(Exception ex) {
			return;
		}
	}

}
