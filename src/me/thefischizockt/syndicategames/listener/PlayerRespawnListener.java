package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.util.LocationUtil;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener{
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		if(SyndicateGames.getInstance().finishedSetup()) {
			e.setRespawnLocation(LocationUtil.getLocation(SyndicateGames.getInstance().getConfig().getConfigurationSection("Spawn.First"), true));
		}
	}

}
