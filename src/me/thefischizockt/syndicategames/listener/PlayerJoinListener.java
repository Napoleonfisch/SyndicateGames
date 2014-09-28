package me.thefischizockt.syndicategames.listener;

import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;
import me.thefischizockt.syndicategames.util.LocationUtil;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		SyndicateGames.getInstance().getConfig().set("UUID."+e.getPlayer().getName(), e.getPlayer().getUniqueId().toString());
		SyndicateGames.getInstance().saveConfig();
		if(SyndicateGames.getInstance().finishedSetup()) {
			e.getPlayer().teleport(LocationUtil.getLocation(SyndicateGames.getInstance().getConfig().getConfigurationSection("Spawn.Lobby"), true));
			if(SyndicateGames.getInstance().getOnlinePlayers() < 2) {
				SyndicateGames.getInstance().setOnlinePlayers(SyndicateGames.getInstance().getOnlinePlayers()+1);
			}else if(SyndicateGames.getInstance().getOnlinePlayers() > 2) {
				MessageManager.getInstance().sendMessageToConsole(MessageType.BAD, "onlinePlayers sollte nicht größer sein als 2!");
				Bukkit.shutdown();
			}
		}
	}

}
