package me.thefischizockt.syndicategames.tab;

import java.lang.reflect.Field;

import net.minecraft.server.v1_7_R4.Packet;
import net.minecraft.server.v1_7_R4.PacketPlayOutPlayerInfo;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TabPacketHandler {

	private static void sendPacket(Player p, Packet packet) {
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	}
	
	public static void setPlayerListName(Player p, String name) {
		p.setPlayerListName(name);
	}
	
	public static void updateTab(Player p) {
		sendPacket(p, getPacket(ChatColor.GRAY + ">>>>>>>>>>>>>"));
		sendPacket(p, getPacket(ChatColor.RED +  "SyndicateGames"));
		sendPacket(p, getPacket(ChatColor.GRAY + "<<<<<<<<<<<<<"));
		sendPacket(p, getPacket(ChatColor.AQUA + "Such nach"));
		sendPacket(p, getPacket(ChatColor.AQUA + "Kisten und"));
		sendPacket(p, getPacket(ChatColor.AQUA + "rüste dich aus"));
		sendPacket(p, getPacket(ChatColor.AQUA + "Besiege deinen"));
		sendPacket(p, getPacket(ChatColor.AQUA + "Gegner und"));
		sendPacket(p, getPacket(ChatColor.AQUA + "gewinne das"));
		sendPacket(p, getPacket(ChatColor.AQUA + "Spiel"));
		sendPacket(p, getPacket(ChatColor.AQUA + "Viel GLÜCK!"));
		sendPacket(p, getPacket(ChatColor.AQUA + "           "));
	}
	
	private static PacketPlayOutPlayerInfo getPacket(String text) {
		PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo();
		try{
			Field username = packet.getClass().getDeclaredField("username");
			Field ping = packet.getClass().getDeclaredField("ping");
			
			username.setAccessible(true);
			ping.setAccessible(true);
			
			username.set(packet, (String)text);
			ping.set(packet, (int)1);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return packet;
	}
	
}
