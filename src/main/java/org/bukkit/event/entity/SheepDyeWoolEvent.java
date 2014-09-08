package org.bukkit.event.entity;

import org.bukkit.DyeColor;
import org.bukkit.entity.Sheep;
import org.bukkit.event.HandlerList;

/**
 * Called when a sheep's wool is dyed
 *
 * @deprecated Use EntityDyeEvent instead
 */
@Deprecated
public class SheepDyeWoolEvent extends EntityDyeEvent {
    private static final HandlerList handlers = new HandlerList();

    public SheepDyeWoolEvent(final Sheep sheep, final DyeColor color) {
        super(sheep, color);
    }

    @Override
    public Sheep getEntity() {
        return (Sheep) entity;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
