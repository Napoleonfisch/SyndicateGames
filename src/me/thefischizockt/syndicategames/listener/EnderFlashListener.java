package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.sound.SoundManager;
import me.thefischizockt.syndicategames.sound.SoundType;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EnderFlashListener implements Listener{
	
	@EventHandler
	public void onPlayerInteract(final PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR) {
			if(e.getItem().getType() == Material.EYE_OF_ENDER) {
				e.setCancelled(true);
				e.getPlayer().getInventory().remove(Material.EYE_OF_ENDER);
				final Item ef = e.getPlayer().getWorld().dropItem(e.getPlayer().getEyeLocation(), new ItemStack(Material.EYE_OF_ENDER));
				ef.setVelocity(e.getPlayer().getLocation().getDirection().multiply(0.8D));
				Bukkit.getScheduler().scheduleSyncDelayedTask(SyndicateGames.getInstance(), new Runnable() {
					public void run()  {
						ef.remove();
						for(Entity e : ef.getNearbyEntities(3.0D, 3.0D, 3.0D)) {
							if(e instanceof Player) {
								Player p = (Player)e;
								p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*5, 1));
								p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20*5, 1));
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*5, 1));
								SoundManager.getInstance().playSound(p, SoundType.ENDERFLASH);
							}
						}
					}
				}, 40);
			}
		}
	}

}
