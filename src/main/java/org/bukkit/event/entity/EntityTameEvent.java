package org.bukkit.event.entity;

import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * Thrown when a LivingEntity is tamed
 * 
 * @author halvors
 */
public class EntityTameEvent extends EntityEvent implements Cancellable {
	private boolean cancel;
	private AnimalTamer owner;
	
    public EntityTameEvent(final Entity entity, final AnimalTamer owner) {
        super(Type.ENTITY_TAME, entity);
        this.owner = owner;
    }
    
    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @return true if this event is cancelled
     */
    public boolean isCancelled() {
        return cancel;
    }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins.
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
    
    /**
     * Gets the current owning AnimalTamer
     *
     * @return the owning AnimalTamer
     */
    public AnimalTamer getOwner() {
    	return owner;
    }
}