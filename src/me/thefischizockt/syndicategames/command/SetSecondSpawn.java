package me.thefischizockt.syndicategames.command;

import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;
import me.thefischizockt.syndicategames.util.LocationUtil;

import org.bukkit.entity.Player;

public class SetSecondSpawn extends SubCommand{
	
	public SetSecondSpawn() {
		super("Setze den Spawnpunkt für den zweiten Spieler", "", "setsecondspawn");
	}
	
	public void onCommand(Player p, String[] args) {
		if(p.hasPermission("sg.setsecondspawn")) {
			LocationUtil.saveLocation("Spawn.Second", p.getLocation());
			MessageManager.getInstance().sendMessageToPlayer(p, MessageType.GOOD, "Du hast den zweiten Spawnpunkt gesetzt!");
			return;
		}
	}

}
