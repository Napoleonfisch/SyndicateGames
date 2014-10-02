package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.BuildManager;
import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.SyndicateGames.GameState;
import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		BuildManager.getInstance().removePlayer(e.getPlayer());
		try{
			if(SyndicateGames.getInstance().finishedSetup()) {
				if(SyndicateGames.getInstance().getOnlinePlayers() <=2 && SyndicateGames.getInstance().getOnlinePlayers() >= 0) {
					
					SyndicateGames.getInstance().setOnlinePlayers(SyndicateGames.getInstance().getOnlinePlayers()-1);
				}else {
					MessageManager.getInstance().sendMessageToConsole(MessageType.BAD, "onlinePlayers sollte nicht größer sein als 2 und kleiner als 0");
					Bukkit.shutdown();
				}
				if(SyndicateGames.getInstance().getState() == GameState.INGAME) {
					SyndicateGames.getInstance().sendWinner(Bukkit.getOnlinePlayers()[0], e.getPlayer(), true);
				}else {
					MessageManager.getInstance().broadcast(MessageType.INFO, "Der Spieler " + e.getPlayer().getName() + " hat das Spiel verlasen!");
				}
			}
		}catch(Exception ex) {
			return;
		}
	}

}
