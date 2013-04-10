package org.bukkit.block;

import java.util.List;

import org.bukkit.SpawnerEntry;
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
     * @return The creature type.
     */
    public EntityType getSpawnedType();

    /**
     * Set the spawner's creature type.
     *
     * @param creatureType The creature type.
     * @throws IllegalArgumentException If the entity cannot be spawned from spawners.
     */
    public void setSpawnedType(EntityType creatureType);

    /**
     * Sets the entity that will be spawned.
     *
     * @param entity The entity
     * @throws IllegalArgumentException If the entity cannot be spawned from spawners.
     */
    public void setSpawnedEntity(Entity entity);

    /**
     * Sets the entity that will be spawned.
     *
     * @param entity The entity.
     * @param includePosition If true position data will be kept.
     * @throws IllegalArgumentException If the entity cannot be spawned from spawners.
     */
    public void setSpawnedEntity(Entity entity, boolean includePosition);

    /**
     * Gets a list of the entity types that this spawner could create. Note that modifying
     * this list will not affect the behviour of the spawner.
     *
     * @return The list of types.
     */
    public List<SpawnerEntry> getSpawnPotentials();

    /**
     * Removes all spawn possibilities from the spawner.
     */
    public void clearSpawnPotentials();

    /**
     * Adds an entity type to the spawn list. The weight is used to control the chance
     * of each spawn potential occurring, a higher value relative to the other list entries
     * will result in this spawn being more frequent.
     *
     * @param creatureType The entity type.
     * @param weight The weight.
     * @throws IllegalArgumentException If the entity cannot be spawned from spawners.
     */
    public void addPotentialSpawnedType(EntityType creatureType, int weight);

    /**
     * Adds an entity to the spawn list. The weight is used to control the chance
     * of each spawn potential occurring, a higher value relative to the other list entries
     * will result in this spawn being more frequent.
     *
     * @param entity The entity
     * @param includePosition If true position data will be kept.
     * @param weight The weight.
     * @throws IllegalArgumentException If the entity cannot be spawned from spawners.
     */
    public void addPotentialSpawnedEntity(Entity entity, boolean includePosition, int weight);

    /**
     * Adds an entity to the spawn list. The weight is used to control the chance
     * of each spawn potential occurring, a higher value relative to the other list entries
     * will result in this spawn being more frequent.
     *
     * @param entity The entity.
     * @param weight The weight.
     * @throws IllegalArgumentException If the entity cannot be spawned from spawners.
     */
    public void addPotentialSpawnedEntity(Entity entity, int weight);

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
     * @throws IllegalArgumentException if delay is less than or equal to 0
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
     * Gets the maximum distance from the spawner that entities will be spawned. This uses a horizontal
     * square centred on the spawner.
     *
     * @return The distance.
     */
    public int getRange();

    /**
     * Sets the maximum distance from the spawner that entities will be spawned. This uses a horizontal
     * square centred on the spawner
     *
     * @param range The distance.
     */
    public void setRange(int range);

    /**
     * Set the vertical range of this CreatureSpawner. Spawn attempts will be uniformly distributed
     * in the range specified. Vanilla behavior (1 below, 1 above) is equivalent to calling this with (1, 1).
     *
     * @param maxBelow Maximum Y-difference below this CreatureSpawner for spawning attempts
     * @param maxAbove Maximum Y-difference above this CreatureSpawner for spawning attempts
     * @throws IllegalArgumentException when no Y-values would be possible (e.g. setVerticalRange(1, -2))
     */
    public void setVerticalRange(int maxBelow, int maxAbove);

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
     * @throws IllegalArgumentException If maxNearbyEntities is less than 0.
     */
    public void setMaxNearbyEntities(int maxNearbyEntities);

    /**
     * Gets the radius of the sphere that a player must be within to allow spawning.
     *
     * @return The range.
     */
    public int getRequiredPlayerRange();

    /**
     * Sets the radius of the sphere that a player must be within to allow spawning.
     *
     * @param requiredPlayerRange The range.
     * @throws IllegalArgumentException If requiredPlayerRange is less than or equal to 0.
     */
    public void setRequiredPlayerRange(int requiredPlayerRange);
}
