
package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;

/**
 * Represents a player related inventory event
 */
public class PlayerInventoryOpenEvent extends PlayerInventoryEvent implements Cancellable {
    protected Block block;
    private boolean cancelled;
    
    public PlayerInventoryOpenEvent(Player player, Inventory inventory, Block block) {
        super(Type.INVENTORY_OPEN, player, inventory);
        
        this.block = block;
        this.cancelled = false;
    }
    
    /**
     * Gets the block belonging to the open inventory
     * @return A Block object that contains the inventory. Null if the inventory isn't block based.
     */
    public Block getBlock() {
        return this.block;
    }
    
    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * If an inventory open event is cancelled, the inventory screen will not show.
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
     * If an inventory open event is cancelled, the inventory screen will not show.
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
