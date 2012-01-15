package org.bukkit.event.entity;

import org.bukkit.entity.Projectile;
import org.bukkit.event.HandlerList;

/**
 * Called when a projectile hits an object
 */
@SuppressWarnings("serial")
public class ProjectileHitEvent extends EntityEvent {

    public ProjectileHitEvent(Projectile projectile) {
        super(Type.PROJECTILE_HIT, projectile);
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
