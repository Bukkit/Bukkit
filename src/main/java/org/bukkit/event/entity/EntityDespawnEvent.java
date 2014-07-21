package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

/**
 * Called whenever an Entity is removed from the world.
 */
public class EntityDespawnEvent extends EntityEvent {
    /**
     * An enum to specify the type of despawning
     */
    public enum DespawnReason {
        /**
         * When an entity despawns due to a chunk unload
         */
        CHUNK_UNLOAD,
        /**
         * When a living entity despawns because it died
         */
        DEATH,
        /**
         * When an entity despawns because a plugin or GameRule removed it,
         * or a non-living entity died (EG, ender crystal destroyed)
         */
        REMOVED,
        /**
         * When an entity is missing a DespawnReason
         */
        DEFAULT
    }

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
     * Gets the reason for why the entity is being despawned.
     *
     * @return The {@link DespawnReason} detailing the reason for the entity being despawned
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
}
