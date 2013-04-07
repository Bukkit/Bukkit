package org.bukkit.block;

import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

/**
 * Represents a creature spawner.
 */
public interface CreatureSpawner extends BlockState {

    /**
     * Get the spawner's creature type.
     *
     * @return The creature type.
     * @deprecated In favour of {@link #getSpawnedType()}.
     */
    @Deprecated
    public CreatureType getCreatureType();

    /**
     * Get the spawner's creature type.
     *
     * @return The creature type.
     */
    public EntityType getSpawnedType();

    /**
     * Set the spawner's creature type.
     *
     * @param creatureType The creature type.
     */
    public void setSpawnedType(EntityType creatureType);

    /**
     * Sets the entity that will be spawned.
     * 
     * @param entity The entity
     */
    public void setSpawnedEntity(Entity entity);

    /**
     * Sets the entity that will be spawned.
     * 
     * @param entity The entity
     * @param includePosition If true position data will be kept
     */
    public void setSpawnedEntity(Entity entity, boolean includePosition);

    /**
     * Set the spawner creature type.
     *
     * @param creatureType The creature type.
     * @deprecated In favour of {@link #setSpawnedType(EntityType)}.
     */
    @Deprecated
    public void setCreatureType(CreatureType creatureType);

    /**
     * Get the spawner's creature type.
     *
     * @return The creature type's name.
     * @deprecated Use {@link #getCreatureTypeName()}.
     */
    @Deprecated
    public String getCreatureTypeId();

    /**
     * Set the spawner mob type.
     *
     * @param creatureType The creature type's name.
     */
    public void setCreatureTypeByName(String creatureType);

    /**
     * Get the spawner's creature type.
     *
     * @return The creature type's name.
     */
    public String getCreatureTypeName();

    /**
     * Set the spawner mob type.
     *
     * @param creatureType The creature type's name.
     * @deprecated Use {@link #setCreatureTypeByName(String)}.
     */
    @Deprecated
    public void setCreatureTypeId(String creatureType);

    /**
     * Get the spawner's delay.
     *
     * @return The delay.
     */
    public int getDelay();

    /**
     * Set the spawner's delay.
     *
     * @param delay The delay.
     */
    public void setDelay(int delay);

    /**
     * Get the spawner's minimum delay.
     *
     * @return The delay.
     */
    public int getMinDelay();

    /**
     * Set the spawner's minimum delay.
     *
     * @param delay The delay.
     */
    public void setMinDelay(int delay);

    /**
     * Get the spawner's maximum delay.
     *
     * @return The delay.
     */
    public int getMaxDelay();

    /**
     * Set the spawner's maximum delay.
     *
     * @param delay The delay.
     */
    public void setMaxDelay(int delay);

    /**
     * Gets the number of entities that the spawner will attempt to spawn.
     * 
     * @return The number.
     */
    public int getCount();

    /**
     * Sets the number of entities that the spawner will attempt to spawn.
     * 
     * @param count The number.
     */
    public void setCount(int count);

    /**
     * Gets the maximum distance from the spawner that entities will be spawned.
     * 
     * @return The distance.
     */
    public int getRange();

    /**
     * Sets the maximum distance from the spawner that entities will be spawned.
     * 
     * @param range The distance.
     */
    public void setRange(int range);

    /**
     * Gets the number of other entities that need to be within the spawner's range to prevent spawning,
     * 
     * @return The number.
     */
    public int getMaxNearbyEntities();

    /**
     * Sets the number of other entities that need to be within the spawner's range to prevent spawning.
     * 
     * @param maxNearbyEntities The number.
     */
    public void setMaxNearbyEntities(int maxNearbyEntities);

    /**
     * Gets the range that a player must be within to allow spawning.
     * 
     * @return The range.
     */
    public int getRequiredPlayerRange();

    /**
     * Sets the range that a player must be within to allow spawning.
     * 
     * @param requiredPlayerRange The range.
     */
    public void setRequiredPlayerRange(int requiredPlayerRange);
}
