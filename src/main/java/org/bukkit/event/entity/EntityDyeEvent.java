package org.bukkit.event.entity;

import org.bukkit.DyeColor;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when an entity has dye successfully used on them.
 * <p>
 * For sheep, this event is called when their wool is dyed. For wolves, this
 * event is fired when their collar is dyed. Note that this event will not
 * fire if the dye being used is the same color as the wool or the collar.
 */
public class EntityDyeEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel;
    private DyeColor color;

    public EntityDyeEvent(final Entity entity, final DyeColor color) {
        super(entity);
        this.cancel = false;
        this.color = color;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Gets the DyeColor for this event
     *
     * @return the DyeColor for this event
     */
    public DyeColor getColor() {
        return color;
    }

    /**
     * Sets the DyeColor for this event
     *
     * @param color the DyeColor for this event
     */
    public void setColor(DyeColor color) {
        this.color = color;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
