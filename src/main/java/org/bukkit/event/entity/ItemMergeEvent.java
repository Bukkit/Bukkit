package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when an Item merges with another.
 */
public class ItemMergeEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private Entity item;
    private boolean cancelled;

    public ItemMergeEvent(Entity current, Entity merged) {
        super(current);
        item = merged;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
    
    /**
     * Returns the entity involved with the merge.
     * 
     * @return Item
     */
    @Override
    public Item getEntity() {
        return (Item) entity;
    }
    
    /**
     * Returns the merged entity involved with the event.
     * 
     * @return Item
     */
    public Item getMerged() {
        return (Item) item;
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
