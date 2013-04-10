package org.bukkit;

import org.bukkit.entity.EntityType;

/**
 * Represents one of the possible entities that may spawn from a spawner
 */
public interface SpawnerEntry {
    /**
     * Gets the type of entity that would be created.
     *
     * @return The type
     */
    public EntityType getEntityType();

    /**
     * Gets the weight of this entry, a higher weight means this spawn will be more likely to happen.
     * 
     * @return The weight
     */
    public int getWeight();
}
