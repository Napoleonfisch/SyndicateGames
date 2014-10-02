package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.SyndicateGames;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if(SyndicateGames.getInstance().finishedSetup()) {
			e.setDeathMessage("");
			if(e.getEntity() == Bukkit.getOnlinePlayers()[0]) {
				SyndicateGames.getInstance().sendWinner(Bukkit.getOnlinePlayers()[1], e.getEntity(), false);
				Bukkit.getOnlinePlayers()[0].setAllowFlight(true);
				Bukkit.getOnlinePlayers()[0].setFlying(true);
				Bukkit.getOnlinePlayers()[1].hidePlayer(Bukkit.getOnlinePlayers()[0]);
				return;
			}else {
				SyndicateGames.getInstance().sendWinner(Bukkit.getOnlinePlayers()[0], e.getEntity(), false);
				Bukkit.getOnlinePlayers()[1].setAllowFlight(true);
				Bukkit.getOnlinePlayers()[1].setFlying(true);
				Bukkit.getOnlinePlayers()[0].hidePlayer(Bukkit.getOnlinePlayers()[1]);
			}
		}
	}

}
