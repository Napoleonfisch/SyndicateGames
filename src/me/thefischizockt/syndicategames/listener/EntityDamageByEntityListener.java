package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.SyndicateGames.GameState;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener{
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		if(SyndicateGames.getInstance().getState() == GameState.WAITING || SyndicateGames.getInstance().getState() == GameState.ENDING || SyndicateGames.getInstance().isFreezed() || e.getDamager().getType() == EntityType.ENDER_PEARL) {
			e.setCancelled(true);
		}else {
			e.setCancelled(false);
		}
	}

}
