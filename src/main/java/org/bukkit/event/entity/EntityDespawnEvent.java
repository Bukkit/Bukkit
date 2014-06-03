package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

/**
 * Called whenever an Entity is removed from the world.
 */
public class EntityDespawnEvent extends EntityEvent {
    private static final HandlerList handlers = new HandlerList();
    private final DespawnReason despawnReason;

    public EntityDespawnEvent(final Entity entity) {
        super(entity);
        despawnReason = DespawnReason.DEFAULT;
    }

    public EntityDespawnEvent(final Entity entity, DespawnReason reason) {
        super(entity);
        despawnReason = reason;
    }

    /**
     * Gets the reason for why the creature is being despawned.
     *
     * @return A DespawnReason value detailing the reason for the entity being despawned
     */
    public DespawnReason getDespawnReason() {
        return despawnReason;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * An enum to specify the type of despawning
     */
    public enum DespawnReason {

        /**
         * When an entity despawns due to a chunk unload
         */
        CHUNK_UNLOAD,
        /**
         * When an entity is missing a DespawnReason
         */
        DEFAULT
    }
}
