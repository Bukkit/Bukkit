package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public class PreItemSpawnEvent extends Event implements Cancellable {

    private boolean cancelled = false;
    private Block block;
    private ItemStack item;
    private Location location;

    public PreItemSpawnEvent(Block block, ItemStack item, Location location) {
        super(Type.PRE_ITEM_SPAWN);
        this.block = block;
        this.item = item;
        this.location = location;
    }

    /**
     * Gets the block that is spawning an item of itself. The block may already 
     * be destroyed when this event is called.
     * 
     * @return the block that is spawning an item
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Gets the item that is being dispensed. Modifying the returned item
     * will have no effect.
     *
     * @return an ItemStack for the item being dispensed
     */
    public ItemStack getItem() {
        return item.clone();
    }

    /**
     * Gets the location at which the item is spawning.
     * 
     * @return The location at which the item is spawning
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @return true if this event is cancelled
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
