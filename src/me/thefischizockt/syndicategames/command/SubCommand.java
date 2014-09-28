package me.thefischizockt.syndicategames.command;

import org.bukkit.entity.Player;

public abstract class SubCommand {

	private String message, usage;
	private String[] aliases;
	
	public SubCommand(String message, String usage, String... aliases) {
		this.message = message;
		this.usage = usage;
		this.aliases = aliases;
	}
	
	public abstract void onCommand(Player p, String[] args);
	
	public final String getMessage() {
		return message;
	}
	
	public final String getUsage() {
		return usage;
	}
	
	public final String[] getAliases() {
		return aliases;
	}
	
}
