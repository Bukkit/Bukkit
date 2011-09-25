package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * Called when a player should start tracking an entity
 */
public class EntityTrackEvent extends EntityEvent implements Cancellable {
    private boolean cancel;
    private Player tracker;

    public EntityTrackEvent(Entity entity, Player tracker) {
        super(Type.ENTITY_TRACK, entity);
        this.tracker = tracker;
        this.cancel = false;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Returns the player tracking the entity
     */
    public Player getTracker() {
        return tracker;
    }

}
