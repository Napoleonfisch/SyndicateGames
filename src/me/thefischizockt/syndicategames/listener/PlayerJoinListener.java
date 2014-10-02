package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.hologram.Hologram;
import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;
import me.thefischizockt.syndicategames.mysql.Deaths;
import me.thefischizockt.syndicategames.mysql.Kills;
import me.thefischizockt.syndicategames.mysql.Points;
import me.thefischizockt.syndicategames.tab.TabPacketHandler;
import me.thefischizockt.syndicategames.util.LocationUtil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		SyndicateGames.getInstance().getConfig().set("UUID."+e.getPlayer().getName(), e.getPlayer().getUniqueId().toString());
		SyndicateGames.getInstance().saveConfig();
		if(SyndicateGames.getInstance().finishedSetup()) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				TabPacketHandler.updateTab(p);
			}
			TabPacketHandler.setPlayerListName(e.getPlayer(), ChatColor.GRAY + e.getPlayer().getName());
			e.getPlayer().getInventory().clear();
			e.getPlayer().teleport(LocationUtil.getLocation(SyndicateGames.getInstance().getConfig().getConfigurationSection("Spawn.Lobby"), true));
			e.setJoinMessage(MessageManager.getInstance().prefix + "§bDer Spieler §6" + e.getPlayer().getName() + " §bhat das Spiel betreten.");
			Location s = LocationUtil.getLocation(SyndicateGames.getInstance().getConfig().getConfigurationSection("Spawn.Stats"), false);
			Hologram h = new Hologram(e.getPlayer(), s, ChatColor.GOLD + "Willkommen §b" + e.getPlayer().getName(),
					ChatColor.GOLD + "Hier sind deine Statistiken:",
					ChatColor.GOLD + "Punkte: §3" + Points.getPoints(e.getPlayer().getUniqueId().toString()),
					ChatColor.GOLD + "Kills: §3" + Kills.getKills(e.getPlayer().getUniqueId().toString()),
					ChatColor.GOLD + "Tode: §3" + Deaths.getDeaths(e.getPlayer().getUniqueId().toString()));
			h.show();
			if(SyndicateGames.getInstance().getOnlinePlayers() < 2) {
				SyndicateGames.getInstance().setOnlinePlayers(SyndicateGames.getInstance().getOnlinePlayers()+1);
			}else if(SyndicateGames.getInstance().getOnlinePlayers() > 2) {
				MessageManager.getInstance().sendMessageToConsole(MessageType.BAD, "onlinePlayers sollte nicht größer sein als 2!");
				Bukkit.shutdown();
			}
		}
	}

}
