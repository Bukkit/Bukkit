package org.bukkit.entity;

/**
 * Representing a "wrapper" based on a custom by a user defined entity
 */
public interface UserDefinedEntity {
	/**
	 * Gets a bukkit entity of this object
	 * A custom entity, which needs to implements this.
	 * @return CraftEntity
	 */
	public Entity getBukkitEntity();
}
