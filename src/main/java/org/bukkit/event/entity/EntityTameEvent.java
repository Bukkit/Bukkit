package org.bukkit.event.entity;

import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

/**
 * Thrown when a LivingEntity is tamed
 *
 * @author halvors
 */
public class EntityTameEvent extends EntityEvent implements Cancellable {
    private boolean cancelled;
    private AnimalTamer owner;

    public EntityTameEvent(Entity entity, AnimalTamer owner) {
        super(Type.ENTITY_TAME, entity);
        this.owner = owner;
    }

    /**
     * Gets the cancellation state of this event. Set to true if you
     * want to prevent buckets from placing water and so forth
     *
     * @return boolean cancellation state
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets the cancellation state of this event. A canceled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * Canceling this event will prevent use of food (player won't lose the
     * food item), prevent bows/snowballs/eggs from firing, etc. (player won't
     * lose the ammo)
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
    
    /**
     * Gets the owning AnimalTamer
     *
     * @return the owning AnimalTamer
     */
    public AnimalTamer getOwner() {
        return owner;
    }
}
