package me.thefischizockt.syndicategames.command;

import me.thefischizockt.syndicategames.BuildManager;
import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;

import org.bukkit.entity.Player;

public class Build extends SubCommand{

	public Build() {
		super("Gebe dir die Kraft zum bauen", "", "build");
	}
	
	public void onCommand(Player p, String[] args) {
		if(p.hasPermission("sg.build")) {
			if(BuildManager.getInstance().containsPlayer(p)) {
				BuildManager.getInstance().removePlayer(p);
				MessageManager.getInstance().sendMessageToPlayer(p, MessageType.GOOD, "Du kannst jetzt nicht mehr bauen!");
				return;
			}else {
				BuildManager.getInstance().addPlayer(p);
				MessageManager.getInstance().sendMessageToPlayer(p, MessageType.GOOD, "Du kannst jetzt bauen!");
				return;
			}
		}
	}
	
}
