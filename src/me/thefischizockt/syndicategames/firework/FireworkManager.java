package me.thefischizockt.syndicategames.firework;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkManager {

	private FireworkManager() {}
	private static FireworkManager instance = new FireworkManager();
	public static FireworkManager getInstance() {
		return instance;
	}
	
	public void sendFirework(Player p) {
		Firework f = (Firework) p.getWorld().spawn(p.getLocation(), Firework.class);
		FireworkMeta fm = f.getFireworkMeta();
		fm.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE)
				.withColor(Color.fromRGB(255, 0, 0))
				.withColor(Color.fromRGB(255, 255, 255))
				.withFlicker()
				.build());
		fm.setPower(0);
		f.setFireworkMeta(fm);
	}
	
}
