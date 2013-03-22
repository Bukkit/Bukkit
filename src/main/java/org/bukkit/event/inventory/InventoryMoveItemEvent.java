package org.bukkit.event.inventory;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * Called when some entity or block (e.g. hopper) tries to move items
 * directly from one inventory to another.
 *
 * When this event is called, the initiator may already have removed the item
 * from the source inventory and is ready to move it into the destination
 * inventory.
 *
 * If this event is cancelled, the items will be returned to the source
 * inventory, if needed.
 *
 * If this event is not cancelled, the initiator will try to put the
 * ItemStack into the destination inventory. If this is not possible and the
 * ItemStack has not been modified, the source inventory slot will be
 * restored to its former state. Otherwise any additional items will be
 * discarded.
 */
public class InventoryMoveItemEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Inventory sourceInventory;
    private final Inventory destinationInventory;
    private ItemStack itemStack;
    private final boolean sourceDidInitiate;

    public InventoryMoveItemEvent(final Inventory sourceInventory, final ItemStack itemStack, final Inventory destinationInventory, final boolean sourceDidInitiate) {
        super();
        this.sourceInventory = sourceInventory;
        this.itemStack = itemStack;
        this.destinationInventory = destinationInventory;
        this.sourceDidInitiate = sourceDidInitiate;
    }

    /**
     * Gets the Inventory that the ItemStack is being picked from
     *
     * @return Inventory, may be null if the source is not an InventoryHolder
     */
    public Inventory getSourceInventory() {
        return sourceInventory;
    }

    /**
     * Gets the ItemStack being moved; if modified, the original item will
     * not be removed from the source inventory.
     *
     * @return ItemStack
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Sets the ItemStack being moved; if this is different from the original
     * ItemStack, the original item will not be removed from the source
     * inventory.
     *
     * @param ItemStack
     */
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Gets the Inventory that the ItemStack is being put into
     *
     * @return Inventory, null if the destination is not an InventoryHolder
     */
    public Inventory getDestinationInventory() {
        return destinationInventory;
    }

    /**
     * Gets the InventoryHolder that initiated the transfer
     *
     * @return InventoryHolder that initiated the transfer, may be null if
     *         the initiator was not an InventoryHolder
     */
    public InventoryHolder getInitiator() {
        if (sourceDidInitiate && sourceInventory != null)
            return sourceInventory.getHolder();
        else if (!sourceDidInitiate && destinationInventory != null)
            return destinationInventory.getHolder();
        return null;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
