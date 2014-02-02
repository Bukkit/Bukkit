package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * Called when an item is dispensed from a block.
 * <p>
 * If a Block Dispense event is cancelled, the block will not dispense the
 * item.
 */
public class BlockDispenseEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private ItemStack item;
    private Vector velocity;
    private int slot;

    /**
     * @deprecated Use {@link #BlockDispenseEvent(Block, ItemStack, Vector, int)} instead
     */
    @Deprecated
    public BlockDispenseEvent(final Block block, final ItemStack dispensed, final Vector velocity) {
        this(block, dispensed, velocity, -1);
    }

    public BlockDispenseEvent(final Block block, final ItemStack dispensed, final Vector velocity, final int slot) {
        super(block);
        this.item = dispensed;
        this.velocity = velocity;
        this.slot = slot;
    }

    /**
     * Gets the item that is being dispensed. Modifying the returned item will
     * have no effect, you must use {@link
     * #setItem(org.bukkit.inventory.ItemStack)} instead.
     *
     * @return An ItemStack for the item being dispensed
     */
    public ItemStack getItem() {
        return item.clone();
    }

    /**
     * Sets the item being dispensed.
     *
     * @param item the item being dispensed
     */
    public void setItem(ItemStack item) {
        this.item = item;
    }

    /**
     * Gets the slot the item is being dispensed from. This returns -1 if the slot was not provided.
     *
     * @return The index of the slot being dispensed from, or -1 if not present
     */
     public int getSlot() {
         return slot;
     }

    /**
     * Gets the velocity.
     * <p>
     * Note: Modifying the returned Vector will not change the velocity, you
     * must use {@link #setVelocity(org.bukkit.util.Vector)} instead.
     *
     * @return A Vector for the dispensed item's velocity
     */
    public Vector getVelocity() {
        return velocity.clone();
    }

    /**
     * Sets the velocity of the item being dispensed.
     *
     * @param vel the velocity of the item being dispensed
     */
    public void setVelocity(Vector vel) {
        velocity = vel;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
