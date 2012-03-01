package org.bukkit.event.player;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Thrown when a player picks an item up from the ground
 */
public class PlayerPickupItemEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Item item;
    private boolean cancel;
    private final int remaining;
    private final boolean pickup;

    public PlayerPickupItemEvent(final Player player, final Item item, final int remaining, final boolean pickup) {
        super(player);
        this.item = item;
        this.remaining = remaining;
        this.pickup = pickup;
        this.cancel = !pickup;
    }

    /**
     * Gets the ItemDrop created by the player
     *
     * @return Item
     */
    public Item getItem() {
        return item;
    }

    /**
     * Gets the amount remaining on the ground, if any
     *
     * @return amount remaining on the ground
     */
    public int getRemaining() {
        return remaining;
    }

    /**
     * Get if item could be picked up from the first place, before another
     * plugin cancelled it
     *
     * @return true if item can be picked up according to vanilla minecraft
     */
    public boolean getPickup() {
        return pickup;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
