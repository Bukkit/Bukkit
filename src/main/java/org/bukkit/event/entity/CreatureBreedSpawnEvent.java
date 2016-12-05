package org.bukkit.event.entity;

import org.bukkit.entity.LivingEntity;

/**
 * Called when a creature spawns because of breeding.
 */
public class CreatureBreedSpawnEvent extends CreatureSpawnEvent {
	private LivingEntity parent1;
	private LivingEntity parent2;
	
	public CreatureBreedSpawnEvent(LivingEntity spawnee, LivingEntity parent1, LivingEntity parent2) {
		super(spawnee, SpawnReason.BREEDING);
		this.parent1 = parent1;
		this.parent2 = parent2;
	}

	public LivingEntity getParent1() {
		return parent1;
	}

	public LivingEntity getParent2() {
		return parent2;
	}
	
	
}
