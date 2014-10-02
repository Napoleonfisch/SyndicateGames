package me.thefischizockt.syndicategames.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor{

	private ArrayList<SubCommand> cmds = new ArrayList<SubCommand>();
	
	public void setup() {
		cmds.add(new SetFirstSpawn());
		cmds.add(new SetSecondSpawn());
		cmds.add(new Setup());
		cmds.add(new Build());
		cmds.add(new SetLobby());
		cmds.add(new Records());
		cmds.add(new SetStats());
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			MessageManager.getInstance().sendMessageToConsole(MessageType.BAD, "Bitte benutze die SyndicateGames Befehle als Spieler");
			return true;
		}
		
		Player p = (Player)sender;
		
		if(cmd.getName().equalsIgnoreCase("syndicategames") || cmd.getName().equalsIgnoreCase("sg")) {
			if(args.length == 0) {
				MessageManager.getInstance().sendMessageToPlayer(p, MessageType.GOOD, ChatColor.STRIKETHROUGH + "================================");
				for(SubCommand c : cmds) MessageManager.getInstance().sendMessageToPlayer(p, MessageType.INFO, "/sg " +  aliases(c) + " " + c.getUsage() + " - " + c.getMessage());
				MessageManager.getInstance().sendMessageToPlayer(p, MessageType.GOOD, "SyndicateGames wurde von TheFischIZockt programmiert", "http://www.youtube.com/user/TheFischIZockt");
				return true;
			}
			
			SubCommand c = getCommand(args[0]);
			
			if(c == null) {
				MessageManager.getInstance().sendMessageToPlayer(p, MessageType.BAD, "Dieser Befehl existiert nicht");
				return true;
			}
			
			Vector<String> a = new Vector<String>(Arrays.asList(args));
			a.remove(0);
			args = a.toArray(new String[a.size()]);
			
			c.onCommand(p, args);
			return true;
		}
		return true;
	}
	
	private String aliases(SubCommand c) {
		String fin = "";
		
		for(String a : c.getAliases()) {
			fin += a + " | ";
		}
		
		return fin.substring(0, fin.lastIndexOf(" | "));
	}
	
	private SubCommand getCommand(String name) {
		for(SubCommand c : cmds) {
			if(c.getClass().getSimpleName().equalsIgnoreCase(name)) return c;
			for(String alias : c.getAliases()) if(alias.equalsIgnoreCase(name)) return c;
		}
		return null;
	}
	
}
