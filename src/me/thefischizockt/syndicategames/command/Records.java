package me.thefischizockt.syndicategames.command;

import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.message.MessageType;
import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.mysql.Deaths;
import me.thefischizockt.syndicategames.mysql.Kills;
import me.thefischizockt.syndicategames.mysql.Points;

import org.bukkit.entity.Player;

public class Records extends SubCommand{
	
	public Records() {
		super("Zeigt die Statistiken eines Spielers an", "<SpielerName>", "records");
	}
	
	public void onCommand(Player p, String[] args) {
		if(p.hasPermission("sg.records")) {
			if(args.length == 0) {
				MessageManager.getInstance().sendMessageToPlayer(p, MessageType.BAD, "Nutze den Befehl so: /sg records <SpielerName>");
				return;
			}
			if(SyndicateGames.getInstance().getConfig().getString("UUID."+args[0]) == null) {
				MessageManager.getInstance().sendMessageToPlayer(p, MessageType.INFO, "Dieser Spieler ist nicht in der Datenbank registriert!");
				return;
			}else {
				String uuid = SyndicateGames.getInstance().getConfig().getString("UUID."+args[0]);
				int points = Points.getPoints(uuid);
				int kills = Kills.getKills(uuid);
				int deaths = Deaths.getDeaths(uuid);
				MessageManager.getInstance().sendMessageToPlayer(p, MessageType.GOOD, args[0]+"'s Statistiken:", "Punkte: " + points, "Kills: " + kills, "Deaths: " + deaths);
			}
		}
	}

}
