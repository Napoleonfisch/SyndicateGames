package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.SyndicateGames.GameState;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamageListener implements Listener{
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if(SyndicateGames.getInstance().getState() == GameState.WAITING) e.setCancelled(true);
		if(SyndicateGames.getInstance().getState() == GameState.ENDING) e.setCancelled(true);
		if(SyndicateGames.getInstance().isFreezed()) e.setCancelled(true);
		if(e.getCause() == DamageCause.FALL) e.setCancelled(true);
	}

}
