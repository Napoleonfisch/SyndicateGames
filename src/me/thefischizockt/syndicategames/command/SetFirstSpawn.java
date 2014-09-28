package me.thefischizockt.syndicategames.command;

import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;
import me.thefischizockt.syndicategames.util.LocationUtil;

import org.bukkit.entity.Player;

public class SetFirstSpawn extends SubCommand{
	
	public SetFirstSpawn() {
		super("Setze den Spawnpunkt für den ersten Spieler", "", "setfirstspawn");
	}
	
	public void onCommand(Player p, String[] args) {
		if(p.hasPermission("sg.setfirstspawn")) {
			LocationUtil.saveLocation("Spawn.First", p.getLocation());
			MessageManager.getInstance().sendMessageToPlayer(p, MessageType.GOOD, "Du hast den ersten Spawnpunkt gesetzt!");
			return;
		}
	}

}
