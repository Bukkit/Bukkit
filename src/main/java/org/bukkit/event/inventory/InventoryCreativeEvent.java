package org.bukkit.event.inventory;

import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

/**
 * This event is called when a player in creative mode puts down or picks up
 * an item in their inventory / hotbar, opens the creative inventory for the
 * first time, and when they drop items from creative.
 * <p>
 * This event is also called when a player not in creative attempts to send
 * those same packets to the server.
 * <p>
 * Result behavior:
 * <ul>
 * <li><b>DEFAULT</b> - if the checks failed, nothing will be done.</li>
 * <li><b>DENY</b> - Nothing will be done.</li>
 * <li><b>ALLOW</b> - the click will go through, even with an invalid item.</li>
 * </ul>
 */
public class InventoryCreativeEvent extends InventoryClickEvent {
    private ItemStack item;
    private boolean defaultResult;

    public InventoryCreativeEvent(InventoryView what, SlotType type, int slot, InventoryAction action, ItemStack newItem, boolean allowed) {
        super(what, type, slot, ClickType.CREATIVE, action);
        this.item = newItem;
        this.defaultResult = allowed;
    }

    /**
     * Get the ItemStack this slot is being set to.
     * @return the new ItemStack
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * Sets the item the slot is being set to.
     * @param newItem the new ItemStack
     */
    public void setItem(ItemStack newItem) {
        item = newItem;
    }

    /**
     * Whether is creative event would be allowed without plugin
     * intervention.
     * @return if the item is allowed in the inventory
     */
    public boolean isValidItem() {
        return defaultResult;
    }

    /**
     * The client doesn't tell the server what the cursor is, so we use
     * what's about to be set as a good substitute.
     */
    @Override
    public ItemStack getCursor() {
        return getItem();
    }

    /**
     * The client doesn't tell the server what the cursor is, so we use
     * what's about to be set as a good substitute.
     */
    @Override
    public void setCursor(ItemStack item) {
        setItem(item);
    }

    @Override
    public boolean isCancelled() {
        if (result == Result.ALLOW) {
            return false;
        } else if (result == Result.DENY) {
            return true;
        } else {
            return !defaultResult;
        }
    }

    @Override
    public void setCancelled(boolean cancel) {
        // Avoid ALLOW as best we can without violating the contract
        result = cancel ? Result.DENY : defaultResult ? Result.DEFAULT : Result.ALLOW;
    }
}
