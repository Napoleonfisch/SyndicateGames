package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.SyndicateGames;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener{
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if(SyndicateGames.getInstance().isFreezed() && SyndicateGames.getInstance().finishedSetup()) {
			if(e.getFrom().getX() != e.getTo().getX() || e.getFrom().getY() != e.getTo().getY() || e.getFrom().getZ() != e.getTo().getZ()) {
				e.getPlayer().teleport(e.getFrom());
			}
		}
	}

}
