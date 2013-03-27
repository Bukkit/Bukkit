package org.bukkit.event.entity;

import org.bukkit.DyeColor;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

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
