package me.thefischizockt.syndicategames.message;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageManager {

	private MessageManager() {}
	private static MessageManager instance = new MessageManager();
	public static MessageManager getInstance() {
		return instance;
	}
	
	public String prefix = ChatColor.YELLOW + "[" + ChatColor.AQUA + "SyndicateGames" + ChatColor.YELLOW + "]: " + ChatColor.RESET;
	
	public void sendMessageToPlayer(Player p, MessageType type, String... messages) {
		for(String msg : messages) {
			p.sendMessage(prefix + type.getColor() + msg);
		}
	}
	
	public void sendMessageToConsole(MessageType type, String... messages) {
		for(String msg : messages) {
			Bukkit.getConsoleSender().sendMessage(prefix + type.getColor() + msg);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void broadcast(MessageType type, String... messages) {
		for(String msg : messages) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(prefix + type.getColor() + msg);
			}
		}
	}
	
}
