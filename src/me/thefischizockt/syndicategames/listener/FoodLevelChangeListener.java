package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.SyndicateGames.GameState;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener{
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		if((SyndicateGames.getInstance().isFreezed() && SyndicateGames.getInstance().finishedSetup()) || SyndicateGames.getInstance().getState() == GameState.WAITING || SyndicateGames.getInstance().getState() == GameState.ENDING) {
			e.setCancelled(true);
		}
	}

}
