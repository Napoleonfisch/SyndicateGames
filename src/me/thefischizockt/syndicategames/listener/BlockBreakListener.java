package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.BuildManager;
import me.thefischizockt.syndicategames.SyndicateGames;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener{
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(SyndicateGames.getInstance().finishedSetup()) {
			if(BuildManager.getInstance().containsPlayer(e.getPlayer())) {
				e.setCancelled(false);
			}else {
				e.setCancelled(true);
			}
		}
	}

}
