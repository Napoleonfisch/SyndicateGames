package me.thefischizockt.syndicategames.command.executor;

import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;
import me.thefischizockt.syndicategames.util.BungeeUtil;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyExecutor implements CommandExecutor{
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			MessageManager.getInstance().sendMessageToConsole(MessageType.BAD, "Nur Spieler können das Spiel verlassen!");
			return true;
		}
		
		Player p = (Player)sender;
		
		if(cmd.getName().equalsIgnoreCase("lobby")) {
			BungeeUtil.sendPlayerToServer(p, SyndicateGames.getInstance().getConfig().getString("Fallback-Server"));
			return true;
		}
		return true;
	}

}
