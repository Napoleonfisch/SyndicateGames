package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.BuildManager;
import me.thefischizockt.syndicategames.SyndicateGames;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener{
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(SyndicateGames.getInstance().finishedSetup()) {
			if(BuildManager.getInstance().containsPlayer(e.getPlayer())) {
				e.setCancelled(false);
			}else {
				e.setCancelled(true);
			}
		}
	}

}
