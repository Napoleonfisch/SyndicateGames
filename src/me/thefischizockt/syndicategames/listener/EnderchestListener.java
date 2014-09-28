package me.thefischizockt.syndicategames.listener;

import java.util.Random;

import me.thefischizockt.syndicategames.SyndicateGames;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EnderchestListener implements Listener{
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.ENDER_CHEST) {
				e.setCancelled(true);
				if(SyndicateGames.getInstance().getChests().containsKey(e.getClickedBlock().getLocation())) {
					e.getPlayer().openInventory(SyndicateGames.getInstance().getChests().get(e.getClickedBlock().getLocation()));
				}else {
					Inventory inv = Bukkit.createInventory(null, InventoryType.ENDER_CHEST);
					Random r = new Random();
					ItemStack ep = new ItemStack(Material.ENDER_PEARL, r.nextInt(6)+1);
					inv.addItem(ep);
					e.getPlayer().openInventory(inv);
					SyndicateGames.getInstance().getChests().put(e.getClickedBlock().getLocation(), inv);
				}
			}
		}
	}

}
