package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

/**
 * Called when a entity is spawned into a world. This is not called for some
 * entities, see table below for the right events for those.
 * <p>
 * If a Entity Spawn event is cancelled, the entity will not spawn.
 * </p>
 * <p>
 * Entity spawns not handled by this event are {@link LivingEntity},
 * {@link Item}, and {@link Projectile} spawns. They can be tracked with the
 * existing events {@link CreatureSpawnEvent}, {@link ItemSpawnEvent}, and
 * {@link ProjectileLaunchEvent}, respectively.
 * </p>
 *
 * @see CreatureSpawnEvent
 * @see ItemSpawnEvent
 * @see ProjectileLaunchEvent
 */
public class EntitySpawnEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private final SpawnReason spawnReason;

    public EntitySpawnEvent(final Entity spawnee, final SpawnReason spawnReason) {
        super(spawnee);
        this.spawnReason = spawnReason;
    }

    public boolean isCancelled() {
        return canceled;
    }

    public void setCancelled(boolean cancel) {
        canceled = cancel;
    }

    @Override
    public Entity getEntity() {
        return entity;
    }

    /**
     * Gets the location at which the entity is spawning.
     *
     * @return The location at which the entity is spawning
     */
    public Location getLocation() {
        return getEntity().getLocation();
    }

    /**
     * Gets the reason for why the entity is being spawned.
     *
     * @return A SpawnReason value detailing the reason for the entity being
     *         spawned
     */
    public SpawnReason getSpawnReason() {
        return spawnReason;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
