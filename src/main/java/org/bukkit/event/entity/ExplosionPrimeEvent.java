package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Explosive;
import org.bukkit.event.HandlerList;

/**
 * Called when an entity has made a decision to explode.
 * @deprecated in favor of {@link EntityPreExplodeEvent} due to bad name
 */
public class ExplosionPrimeEvent extends EntityPreExplodeEvent {
    public ExplosionPrimeEvent(final Entity what, final float radius, final boolean fire) {
        super(what, radius, fire);
    }

    public ExplosionPrimeEvent(final Explosive explosive) {
        this(explosive, explosive.getYield(), explosive.isIncendiary());
    }

    public HandlerList getHandlers() {
        return super.getHandlers();
    }

    public static HandlerList getHandlerList() {
        return EntityPreExplodeEvent.getHandlerList();
    }
}
