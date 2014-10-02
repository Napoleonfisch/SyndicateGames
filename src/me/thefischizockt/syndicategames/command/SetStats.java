package me.thefischizockt.syndicategames.command;

import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;
import me.thefischizockt.syndicategames.util.LocationUtil;

import org.bukkit.entity.Player;

public class SetStats extends SubCommand{
	
	public SetStats() {
		super("Setzte den Spawnpunkt der Statistiken", "", "setstats");
	}
	
	public void onCommand(Player p, String[] args) {
		if(p.hasPermission("sg.setstats")) {
			LocationUtil.saveLocation("Spawn.Stats", p.getLocation());
			MessageManager.getInstance().sendMessageToPlayer(p, MessageType.GOOD, "Du hast den Spawnpunkt der Statistiken erfolgreich gesetzt.");
		}
	}

}
