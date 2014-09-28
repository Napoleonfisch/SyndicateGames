package me.thefischizockt.syndicategames.sound;

import org.bukkit.entity.Player;

public class SoundManager {

	private SoundManager() {}
	private static SoundManager instance = new SoundManager();
	public static SoundManager getInstance() {
		return instance;
	}
	
	public void playSound(Player p, SoundType type) {
		p.playSound(p.getEyeLocation(), type.getSound(), 1, 1);
	}
	
}
