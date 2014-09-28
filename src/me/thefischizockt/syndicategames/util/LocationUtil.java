package me.thefischizockt.syndicategames.util;

import me.thefischizockt.syndicategames.SyndicateGames;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class LocationUtil {

	public static void saveLocation(String path, Location loc) {
		FileConfiguration cfg = SyndicateGames.getInstance().getConfig();
		cfg.set(path + ".World", loc.getWorld().getName());
		cfg.set(path + ".X", loc.getX());
		cfg.set(path + ".Y", loc.getY());
		cfg.set(path + ".Z", loc.getZ());
		cfg.set(path + ".Yaw", loc.getYaw());
		cfg.set(path + ".Pitch", loc.getPitch());
		SyndicateGames.getInstance().saveConfig();
	}
	
	public static Location getLocation(ConfigurationSection s, boolean pitchAndYaw) {
		try{
			Location loc = new Location(
					Bukkit.getServer().getWorld(s.getString("World")),
					s.getDouble("X"),
					s.getDouble("Y"),
					s.getDouble("Z")
					);
			if(pitchAndYaw) {
				loc.setPitch((float) s.getDouble("Pitch"));
				loc.setYaw((float) s.getDouble("Yaw"));
			}
			return loc;
		}catch (Exception e) {
			return null;
		}
	}
	
}
