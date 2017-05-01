package org.bukkit.event.entity;

import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;

/**
 * Called when an entity is spawned into a world by a spawner.
 * <p>
 * If cancelled, the entity will not spawn.
 */
public class SpawnerSpawnEvent extends EntitySpawnEvent {
    private final CreatureSpawner spawner;

    public SpawnerSpawnEvent(final Entity spawnee, final CreatureSpawner spawner) {
        super(spawnee);
        this.spawner = spawner;
    }

    /**
     * Returns the CreatureSpawner involved in this event
     *
     * @return CreatureSpawner which is involved in this event
     */
    public CreatureSpawner getSpawner() {
        return spawner;
    }
}
