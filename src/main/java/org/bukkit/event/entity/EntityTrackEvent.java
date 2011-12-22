package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * Called when a player should start tracking an entity
 */
public class EntityTrackEvent extends EntityEvent implements Cancellable {
    private boolean cancel;
    private boolean handled;
    private Player tracker;

    public EntityTrackEvent(Entity entity, Player tracker) {
        super(Type.ENTITY_TRACK, entity);
        this.tracker = tracker;
        this.cancel = false;
        this.handled = false;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Checks if the event was handled by a plugin
     * 
     * @return boolean if event was handled
     */
    public boolean wasHandled() {
        return handled;
    }

    /**
     * Sets if the event is being handled by a plugin
     * 
     * @param handled If event is handled
     */
    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    /**
     * Returns the player tracking the entity
     * 
     * @return Player the player tracking the entity
     */
    public Player getTracker() {
        return tracker;
    }
}
