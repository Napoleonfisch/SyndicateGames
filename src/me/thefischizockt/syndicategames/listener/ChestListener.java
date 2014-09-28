package me.thefischizockt.syndicategames.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.SyndicateGames.GameState;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChestListener implements Listener{
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(!SyndicateGames.getInstance().isFreezed() && SyndicateGames.getInstance().getState() != GameState.WAITING) {
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(e.getClickedBlock().getType() == Material.CHEST) {
					e.setCancelled(true);
					if(SyndicateGames.getInstance().getChests().containsKey(e.getClickedBlock().getLocation())) {
						e.getPlayer().openInventory(SyndicateGames.getInstance().getChests().get(e.getClickedBlock().getLocation()));
					}else {
						Random rand = new Random();
						int n = 1;
						n = (rand.nextInt(7)+1);
						Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST);
						List<ItemStack> items = new ArrayList<>();
						items.add(new ItemStack(Material.WEB, 2));
						items.add(new ItemStack(Material.WEB, 2));
						items.add(new ItemStack(Material.BAKED_POTATO, 4));
						items.add(new ItemStack(Material.APPLE, 3));
						items.add(new ItemStack(Material.BOW));
						items.add(new ItemStack(Material.ARROW, 7));
						items.add(new ItemStack(Material.IRON_INGOT));
						items.add(new ItemStack(Material.WOOD_SWORD));
						items.add(new ItemStack(Material.WOOD_AXE));
						items.add(new ItemStack(Material.WOOD_AXE));
						items.add(new ItemStack(Material.STICK, 2));
						items.add(new ItemStack(Material.STICK, 2));
						items.add(new ItemStack(Material.MUSHROOM_SOUP));
						items.add(new ItemStack(Material.MUSHROOM_SOUP));
						items.add(new ItemStack(Material.STRING));
						items.add(new ItemStack(Material.STRING));
						items.add(new ItemStack(Material.WHEAT, 3));
						items.add(new ItemStack(Material.WHEAT, 3));
						items.add(new ItemStack(Material.BREAD, 2));
						items.add(new ItemStack(Material.BREAD, 2));
						items.add(new ItemStack(Material.LEATHER_BOOTS));
						items.add(new ItemStack(Material.LEATHER_BOOTS));
						items.add(new ItemStack(Material.LEATHER_CHESTPLATE));
						items.add(new ItemStack(Material.LEATHER_CHESTPLATE));
						items.add(new ItemStack(Material.LEATHER_HELMET));
						items.add(new ItemStack(Material.LEATHER_HELMET));
						items.add(new ItemStack(Material.LEATHER_LEGGINGS));
						items.add(new ItemStack(Material.LEATHER_LEGGINGS));
						items.add(new ItemStack(Material.CHAINMAIL_BOOTS));
						items.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
						items.add(new ItemStack(Material.CHAINMAIL_HELMET));
						items.add(new ItemStack(Material.CHAINMAIL_LEGGINGS));
						items.add(new ItemStack(Material.RAW_BEEF, 2));
						items.add(new ItemStack(Material.RAW_BEEF, 2));
						items.add(new ItemStack(Material.COOKED_BEEF, 2));
						items.add(new ItemStack(Material.COOKED_BEEF, 2));
						items.add(new ItemStack(Material.GOLDEN_APPLE));
						items.add(new ItemStack(Material.SNOW_BALL, 8));
						items.add(new ItemStack(Material.SNOW_BALL, 8));
						items.add(new ItemStack(Material.FISHING_ROD));
						items.add(new ItemStack(Material.FISHING_ROD));
						items.add(new ItemStack(Material.RAW_FISH, 2));
						items.add(new ItemStack(Material.RAW_FISH, 2));
						items.add(new ItemStack(Material.COOKED_FISH, 2));
						items.add(new ItemStack(Material.COOKED_FISH, 2));
						items.add(new ItemStack(Material.COOKIE, 4));
						items.add(new ItemStack(Material.COOKIE, 4));
						items.add(new ItemStack(Material.RAW_CHICKEN, 2));
						items.add(new ItemStack(Material.RAW_CHICKEN, 2));
						items.add(new ItemStack(Material.COOKED_CHICKEN, 2));
						items.add(new ItemStack(Material.COOKED_CHICKEN, 2));
						items.add(new ItemStack(Material.EXP_BOTTLE, 2));
						items.add(new ItemStack(Material.PUMPKIN_PIE, 3));
						items.add(new ItemStack(Material.PUMPKIN_PIE, 3));
						ItemStack ef = new ItemStack(Material.EYE_OF_ENDER, 1);
						ItemMeta efm = ef.getItemMeta();
						efm.setDisplayName("Ender Flash");
						ef.setItemMeta(efm);
						items.add(ef);
						while(n != 0) {
							n--;
							Random rand2 = new Random();
							Random rand3 = new Random();
							int n3 = rand3.nextInt(27);
							int n2 = rand2.nextInt(items.size());
							inv.setItem(n3, items.get(n2));
						}
						
						SyndicateGames.getInstance().getChests().put(e.getClickedBlock().getLocation(), inv);
						e.getPlayer().openInventory(inv);
						return;
					}
				}
			}
		}
	}

}
