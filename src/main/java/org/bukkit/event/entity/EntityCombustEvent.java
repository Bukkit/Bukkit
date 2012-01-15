package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when an entity combusts.
 * <p />
 * If an Entity Combust event is cancelled, the entity will not combust.
 */
@SuppressWarnings("serial")
public class EntityCombustEvent extends EntityEvent implements Cancellable {
    private int duration;
    private boolean cancel;

    public EntityCombustEvent(Entity combustee, int duration) {
        super(Type.ENTITY_COMBUST, combustee);
        this.duration = duration;
        this.cancel = false;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * @return the amount of time (in seconds) the combustee should be alight for
     */
    public int getDuration() {
        return duration;
    }

    /**
     * The number of seconds the combustee should be alight for.
     *
     * This value will only ever increase the combustion time, not decrease existing combustion times.
     *
     * @param duration the time in seconds to be alight for.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    private static final HandlerList handlers = new HandlerList();
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
