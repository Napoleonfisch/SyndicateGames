package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.sound.SoundManager;
import me.thefischizockt.syndicategames.sound.SoundType;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ExpBottleListener implements Listener{
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getItem() == null) return;
			if(e.getItem().getType() == Material.EXP_BOTTLE) {
				e.setCancelled(true);
				if(e.getItem().getAmount() > 1) {
					e.getItem().setAmount(e.getItem().getAmount()-1);
				}else {
					e.getPlayer().getInventory().remove(Material.EXP_BOTTLE);
				}
				e.getPlayer().setLevel(e.getPlayer().getLevel()+1);
				SoundManager.getInstance().playSound(e.getPlayer(), SoundType.BOTTLE);
				SoundManager.getInstance().playSound(e.getPlayer(), SoundType.EXP);
			}else {
				return;
			}
		}
	}

}
