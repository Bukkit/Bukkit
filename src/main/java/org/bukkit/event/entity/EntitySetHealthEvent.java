package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * Stores data for health-set events
 */
public class EntitySetHealthEvent extends EntityEvent implements Cancellable {

    private boolean cancelled;
    private int newhealth;
    private int oldhealth;

    public EntitySetHealthEvent(Entity entity, int new_value, int old_value) {
        super(Event.Type.ENTITY_SET_HEALTH, entity);
        this.newhealth = new_value;
        this.oldhealth = old_value;
    }

    /**
     * Returns new health
     */
    public int getNewHealth() {
        return this.newhealth;
    }
    /**
     * Returns old health
     */
    public int getOldHealth() {
        return this.oldhealth;
    }

    /**
     * Sets new health
     *
     * @param new_health the new health
     */
    public void setNewHealth(int new_value) {
        this.newhealth = new_value;
    }
     /**
     * Sets old health
     *
     * @param old_health the old health
     */
    public void setOldHealth(int old_value) {
        this.oldhealth = old_value;
    }

    /**
     * Returns if the event is cancelled
     */        
    public boolean isCancelled() {
        return cancelled;
    }
    /**
     * Sets if the event is cancelled
     *
     * @param cancel Boolean if cancelled
     */
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
