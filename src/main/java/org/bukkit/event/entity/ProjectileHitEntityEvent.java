package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;

public class ProjectileHitEntityEvent extends ProjectileHitEvent {
	
	protected Entity hit;

	public ProjectileHitEntityEvent(Projectile projectile, Entity hit) {
		super(projectile);
		this.hit=hit;
	}
	
	public Entity getHit(){
		return hit;
	}

}
