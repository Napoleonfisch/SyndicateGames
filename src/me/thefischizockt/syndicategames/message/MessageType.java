package me.thefischizockt.syndicategames.message;

import org.bukkit.ChatColor;

public enum MessageType {

	GOOD(ChatColor.AQUA),
	INFO(ChatColor.YELLOW),
	BAD(ChatColor.RED);
	
	private ChatColor color;
	
	MessageType(ChatColor color) {
		this.color = color;
	}
	
	public ChatColor getColor() {
		return color;
	}
	
}
