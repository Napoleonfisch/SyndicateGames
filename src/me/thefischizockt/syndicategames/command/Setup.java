package me.thefischizockt.syndicategames.command;

import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;

import org.bukkit.entity.Player;

public class Setup extends SubCommand{
	
	public Setup() {
		super("Beende das Setup", "", "setup");
	}
	
	public void onCommand(Player p, String[] args) {
		if(p.hasPermission("sg.setup")) {
			SyndicateGames.getInstance().getConfig().set("Finished-Setup", true);
			SyndicateGames.getInstance().saveConfig();
			MessageManager.getInstance().sendMessageToPlayer(p, MessageType.GOOD, "Du hast das Setup beendet!",
					"Um etwas zu verändern, ändere den Wert von 'Setup-Finished' in der Config auf 'false'");
			SyndicateGames.getInstance().restart();
		}
	}

}
