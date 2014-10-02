package me.thefischizockt.syndicategames.hologram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.server.v1_7_R4.EntityHorse;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.EntityWitherSkull;
import net.minecraft.server.v1_7_R4.PacketPlayOutAttachEntity;
import net.minecraft.server.v1_7_R4.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_7_R4.PacketPlayOutSpawnEntity;
import net.minecraft.server.v1_7_R4.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_7_R4.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Hologram {

	private static final double distance = 0.23;
	private List<String> lines = new ArrayList<String>();
	private List<Integer> ids = new ArrayList<Integer>();
	private boolean showing = false;
	private Location location;
	private Player player;
	
	public Hologram(Player player, Location location, String... lines) {
		this.location = location;
		this.player = player;
		this.lines.addAll(Arrays.asList(lines));
	}
	
	public void change(String... lines) {
		destroy();
		this.lines = Arrays.asList(lines);
		show();
	}
	
	public void show() {
		if(showing == true) {
			try{
				throw new Exception("Is already showing!");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		Location first = this.location.clone().add(0, (this.lines.size() / 2) * distance, 0);
		
		for(int i = 0; i < this.lines.size(); i++) {
			ids.addAll(showLine(first.clone(), this.lines.get(i)));
			first.subtract(0, distance, 0);
		}
		showing = true;
	}
	
	@SuppressWarnings("deprecation")
	public void destroy() {
		if(showing == false) {
			try{
				throw new Exception("Isn't showing!");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		int[] ints = new int[this.ids.size()];
		for(int j = 0; j < ints.length; j++) {
			ints[j] = ids.get(j);
		}
		PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(ints);
		for(Player p : Bukkit.getOnlinePlayers()) {
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
		}
		showing = false;
		this.location = null;
	}
	
	private List<Integer> showLine(Location loc, String text) {
		WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
		EntityWitherSkull skull = new EntityWitherSkull(world);
		skull.setLocation(loc.getX(), loc.getY() + 1 + 55, loc.getZ(), 0, 0);
		PacketPlayOutSpawnEntity skullPacket = new PacketPlayOutSpawnEntity(skull, 64);
		
		EntityHorse horse = new EntityHorse(world);
		horse.setLocation(loc.getX(), loc.getY() + 55, loc.getZ(), 0, 0);
		horse.setAge(-1700000);
		horse.setCustomName(text);
		horse.setCustomNameVisible(true);
		PacketPlayOutSpawnEntityLiving horsePacket = new PacketPlayOutSpawnEntityLiving(horse);
		EntityPlayer nmsPlayer = ((CraftPlayer)player).getHandle();
		nmsPlayer.playerConnection.sendPacket(horsePacket);
		nmsPlayer.playerConnection.sendPacket(skullPacket);
		
		PacketPlayOutAttachEntity pa = new PacketPlayOutAttachEntity(0, horse, skull);
		nmsPlayer.playerConnection.sendPacket(pa);
		return Arrays.asList(skull.getId(), horse.getId());
	}
	
}
