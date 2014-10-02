package me.thefischizockt.syndicategames.util;

import java.util.ArrayList;

import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable{
	
	private int timer;
	private String msg, finMsg, mode;
	private ArrayList<Integer> countingNums;
	
	public Countdown(int start, String msg, String finMsg, String mode, int... countingNums) {
		this.timer = start;
		this.msg = msg;
		this.finMsg = finMsg;
		this.mode = mode;
		this.countingNums = new ArrayList<Integer>();
		for(int i : countingNums) this.countingNums.add(i);
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		if(timer == 0) {
			MessageManager.getInstance().broadcast(MessageType.INFO, finMsg);
			if(mode == "restart") {
				for(Player p : Bukkit.getOnlinePlayers()) {
					String server = SyndicateGames.getInstance().getConfig().getString("Fallback-Server");
					BungeeUtil.sendPlayerToServer(p, server);
					Bukkit.shutdown();
				}
			}else if(mode == "start") {
				SyndicateGames.getInstance().setFreezed(false);
				for(Player p : Bukkit.getOnlinePlayers()) {
					p.setGameMode(GameMode.SURVIVAL);
					p.setAllowFlight(false);
					p.setFireTicks(0);
					p.setExp(0);
					p.setLevel(0);
					p.getInventory().clear();
					p.getInventory().setBoots(new ItemStack(Material.AIR));
					p.getInventory().setLeggings(new ItemStack(Material.AIR));
					p.getInventory().setChestplate(new ItemStack(Material.AIR));
					p.getInventory().setHelmet(new ItemStack(Material.AIR));
					p.setFoodLevel(20);
					p.setHealth(20D);
					for(Entity e : p.getNearbyEntities(100, 100, 100)) {
						if(e instanceof Item) {
							Item i = (Item)e;
							i.remove();
						}
					}
					p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
				}
				SyndicateGames.getInstance().getChests().clear();
			}else if(mode == "lobby") {
				if(Bukkit.getOnlinePlayers().length == 1) {
					SyndicateGames.getInstance().start();
				}else {
					SyndicateGames.getInstance().startedLobby = true;
					new Countdown(30, "Die Lobby endet in %t Sekunden", "Die Lobby wurde beendet", "lobby", 30, 20, 10, 5, 4, 3, 2, 1).runTaskTimer(SyndicateGames.getInstance(), 0, 20);
				}
			}
			cancel();
		}
		
		if(countingNums.contains(timer)) {
			MessageManager.getInstance().broadcast(MessageType.INFO, msg.replace("%t", timer + ""));
		}
		
		timer--;
	}
}
