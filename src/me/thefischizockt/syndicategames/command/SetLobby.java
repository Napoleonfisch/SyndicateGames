package me.thefischizockt.syndicategames.command;

import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;
import me.thefischizockt.syndicategames.util.LocationUtil;

import org.bukkit.entity.Player;

public class SetLobby extends SubCommand{
	
	public SetLobby() {
		super("Setze den Lobbyspawn", "", "setlobby");
	}
	
	public void onCommand(Player p, String[] args) {
		if(p.hasPermission("sg.setlobby")) {
			LocationUtil.saveLocation("Spawn.Lobby", p.getLocation());
			MessageManager.getInstance().sendMessageToPlayer(p, MessageType.GOOD, "Du hast den Lobbyspawn gesetzt!");
			return;
		}
	}

}
