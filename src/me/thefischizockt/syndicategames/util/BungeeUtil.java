package me.thefischizockt.syndicategames.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import me.thefischizockt.syndicategames.SyndicateGames;
import me.thefischizockt.syndicategames.message.MessageManager;
import me.thefischizockt.syndicategames.message.MessageType;

import org.bukkit.entity.Player;

public class BungeeUtil {

	public static void sendPlayerToServer(Player p, String server) {
		try{
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			
			out.writeUTF("Connect");
			out.writeUTF(server);
			
			p.sendPluginMessage(SyndicateGames.getInstance(), "BungeeCord", b.toByteArray());
		}catch(Exception e) {
			MessageManager.getInstance().sendMessageToConsole(MessageType.BAD, "Der Spieler " + p.getName() + " konnte nicht auf den Server " + server + " gesendet werden");
		}
	}
	
}
