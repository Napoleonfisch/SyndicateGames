package me.thefischizockt.syndicategames.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener{
	
	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent e) {
		if(e.getEntityType() == EntityType.ZOMBIE) e.setCancelled(true);
		if(e.getEntityType() == EntityType.BLAZE) e.setCancelled(true);
		if(e.getEntityType() == EntityType.CAVE_SPIDER) e.setCancelled(true);
		if(e.getEntityType() == EntityType.CREEPER) e.setCancelled(true);
		if(e.getEntityType() == EntityType.ENDER_DRAGON) e.setCancelled(true);
		if(e.getEntityType() == EntityType.ENDERMAN) e.setCancelled(true);
		if(e.getEntityType() == EntityType.GHAST) e.setCancelled(true);
		if(e.getEntityType() == EntityType.GIANT) e.setCancelled(true);
		if(e.getEntityType() == EntityType.MAGMA_CUBE) e.setCancelled(true);
		if(e.getEntityType() == EntityType.PIG_ZOMBIE) e.setCancelled(true);
		if(e.getEntityType() == EntityType.SILVERFISH) e.setCancelled(true);
		if(e.getEntityType() == EntityType.SKELETON) e.setCancelled(true);
		if(e.getEntityType() == EntityType.SLIME) e.setCancelled(true);
		if(e.getEntityType() == EntityType.SPIDER) e.setCancelled(true);
		if(e.getEntityType() == EntityType.WITCH) e.setCancelled(true);
		if(e.getEntityType() == EntityType.WITHER) e.setCancelled(true);
		if(e.getEntityType() == EntityType.WITHER_SKULL) e.setCancelled(true);
	}

}
