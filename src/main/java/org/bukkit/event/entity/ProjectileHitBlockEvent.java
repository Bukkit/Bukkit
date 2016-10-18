package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.Projectile;

public class ProjectileHitBlockEvent extends ProjectileHitEvent{
	
	protected Block block;

	public ProjectileHitBlockEvent(Projectile projectile, Block block) {
		super(projectile);
		this.block = block;
	}
	
	public Block getBlock(){
		return block;
	}

}
