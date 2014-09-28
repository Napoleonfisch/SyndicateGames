package me.thefischizockt.syndicategames.sound;

import org.bukkit.Sound;

public enum SoundType {

	COUNTDOWN(Sound.CLICK),
	START(Sound.NOTE_PLING),
	DEATH(Sound.BLAZE_DEATH),
	WIN(Sound.FIREWORK_LARGE_BLAST),
	ENDERFLASH(Sound.FIZZ),
	BOTTLE(Sound.GLASS),
	EXP(Sound.ORB_PICKUP);
	
	private Sound sound;
	
	SoundType(Sound sound) {
		this.sound = sound;
	}
	
	public Sound getSound() {
		return sound;
	}
	
}
