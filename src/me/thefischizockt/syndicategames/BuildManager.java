package me.thefischizockt.syndicategames;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class BuildManager {

	private BuildManager() {}
	private static BuildManager instance = new BuildManager();
	public static BuildManager getInstance() {
		return instance;
	}
	
	private ArrayList<Player> builder = new ArrayList<Player>();
	
	public boolean containsPlayer(Player p) {
		return builder.contains(p);
	}
	
	public void addPlayer(Player p) {
		if(!containsPlayer(p)) {
			builder.add(p);
		}
	}
	
	public void removePlayer(Player p) {
		if(containsPlayer(p)) {
			builder.remove(p);
		}
	}
	
}
