package org.bukkit;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

/**
 * Represents one of the possible entities that may spawn from a spawner
 */
public class SpawnerEntry {
    protected EntityType type;
    protected boolean includePosition;
    protected int weight;

    public SpawnerEntry(EntityType type, int weight) {
        if (type == null || type.getName() == null) {
            throw new IllegalArgumentException("Can't spawn EntityType " + type + " from mobspawners!");
        }

        this.type = type;
        this.includePosition = false;
        this.weight = weight;
    }

    /**
     * Gets the type of entity that would be created.
     *
     * @return The type
     */
    public EntityType getEntityType() {
        return type;
    }

    /**
     * Gets the include position flag, if true the entity will be spawned at it's current location.
     * 
     * @return The flag
     */
    public boolean isPositionIncluded() {
        return includePosition;
    }

    /**
     * Sets the include position flag, if true the entity will be spawned at it's current location.
     *
     * @param includePosition The flag
     */
    public void setIncludePosition(boolean includePosition) {
        this.includePosition = includePosition;
    }

    /**
     * Gets the weight of this entry, a higher weight means this spawn will be more likely to happen.
     * 
     * @return The weight
     */
    public int getWeight() {
        return weight;
    }
    
    /**
     * Gets the weight of this entry, a higher weight means this spawn will be more likely to happen.
     *
     * @param weight The weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
