package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a creature is spawned into a world as the result of a {@link
 * org.bukkit.Material#MOB_SPAWNER mob spawner} block.
 */
public class SpawnerSpawnEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private final CreatureSpawner sourceSpawner;

    public SpawnerSpawnEvent(Entity spawnee, CreatureSpawner sourceSpawner) {
        super(spawnee);
        this.sourceSpawner = sourceSpawner;
    }

    /**
     * Gets the location at which the creature is spawning.
     *
     * @return where the creature is spawning
     */
    public Location getLocation() {
        return this.getEntity().getLocation();
    }

    /**
     * Gets the block state for the mob spawner block that is spawning the
     * entity.
     *
     * @return block state for the mob spawner or null if some mismatch occurs
     */
    public CreatureSpawner getSourceSpawner() {
        return this.sourceSpawner;
    }

    /**
     * Gets if entity should be spawned or not after event finishes processing.
     *
     * @return true if entity will be spawned
     */
    public boolean isCancelled() {
        return canceled;
    }

    /**
     * Set if entity should be spawned or not after even finishes processing.
     *
     * @param cancel true to cancel entity spawn
     */
    public void setCancelled(boolean cancel) {
        canceled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
