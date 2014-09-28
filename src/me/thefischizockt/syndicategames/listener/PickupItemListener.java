package me.thefischizockt.syndicategames.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickupItemListener implements Listener{
	
	@EventHandler
	public void onPickupItem(PlayerPickupItemEvent e) {
		if(e.getItem().getItemStack().getType() == Material.EYE_OF_ENDER) {
			e.setCancelled(true);
		}else {
			e.setCancelled(false);
		}
	}

}
