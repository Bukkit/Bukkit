package org.bukkit.event.inventory;

import org.bukkit.GameMode;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;

/**
 * This event is called when a player clicks in an inventory.
 * <p>
 * The ClickType enum describes the various types of ways a player can
 * click on an inventory, including an OTHER value for methods not yet
 * recognized by Bukkit.
 * <p>
 * The InventoryAction enum describes the approximate result of the click.
 * <p>
 * This event is called with ClickType.DRAG_(RIGHT|LEFT) for the progress on
 * individual slots - to disallow the action as a whole, use
 * {@link InventoryDragEvent}.
 */
public class InventoryClickEvent extends InventoryActionEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private SlotType slot_type;
    private int whichSlot;
    private int rawSlot;
    private ItemStack current = null;
    private int hotbarKey = -1;

    @Deprecated
    public InventoryClickEvent(InventoryView what, SlotType type, int slot, boolean right, boolean shift) {
        this(what, type, slot, right ? (shift ? ClickType.SHIFT_RIGHT : ClickType.RIGHT) : (shift ? ClickType.SHIFT_LEFT : ClickType.LEFT), InventoryAction.SWAP_WITH_CURSOR);
    }

    public InventoryClickEvent(InventoryView what, SlotType type, int slot, ClickType click, InventoryAction action) {
        super(what, click, action);
        this.slot_type = type;
        this.rawSlot = slot;
        this.whichSlot = what.convertSlot(slot);
    }

    public InventoryClickEvent(InventoryView what, SlotType type, int slot, ClickType click, InventoryAction action, int key) {
        this(what, type, slot, click, action);
        this.hotbarKey = key;
    }

    /**
     * Get the type of slot that was clicked.
     * @return The slot type.
     */
    public SlotType getSlotType() {
        return slot_type;
    }

    /**
     * Get the current item in the clicked slot.
     * @return The slot item.
     */
    public ItemStack getCurrentItem() {
        if (slot_type == SlotType.OUTSIDE) {
            return current;
        }
        return getView().getItem(rawSlot);
    }

    /**
     * Set the current item in the slot.
     * @param what The new slot item.
     */
    public void setCurrentItem(ItemStack what) {
        if (slot_type == SlotType.OUTSIDE) {
            current = what;
        }
        else getView().setItem(rawSlot, what);
    }

    /**
     * Returns whether this event will be cancelled. If the result is
     * DEFAULT, a guess is made as to whether vanilla will allow the click.
     * @return whether the event should be considered cancelled
     */
    public boolean isCancelled() {
        if (result == Result.ALLOW) {
            return false;
        } else if (result == Result.DENY) {
            return true;
        } else {
            // Guess whether vanilla will deny it (this only applies for middle clicks, because InventoryCreativeEvent overrides it)
            if (isCreativeAction()) {
                return getView().getPlayer().getGameMode() != GameMode.CREATIVE;
            }
            return false;
        }
    }

    /**
     * Proxy method to {@link InventoryClickEvent#setResult(Result)} for the
     * Cancellable interface. setResult is preferred, as it allows you to
     * specify use of the default behavior.
     *
     * @param toCancel result becomes DENY if true, ALLOW if false
     */
    public void setCancelled(boolean toCancel) {
        result = toCancel ? Result.DENY : Result.ALLOW;
    }

    /**
     * The slot number that was clicked, ready for passing to
     * {@link Inventory#getItem(int)}. Note that there may be two slots with
     * the same slot number, since a view links two different inventories.
     * @return The slot number.
     */
    public int getSlot() {
        return whichSlot;
    }

    /**
     * The raw slot number clicked, ready for passing to
     * {@link InventoryView#getItem(int)} This slot number is unique for the
     * view.
     * @return The slot number.
     */
    public int getRawSlot() {
        return rawSlot;
    }

    /**
     * If the ClickAction is NUMBER_KEY, this method will return the offset
     * into the InventoryView of the appropriate slot on the hotbar.
     * @return a raw slot index
     */
    public int getHotbarSlot() {
        if (hotbarKey == -1) return -1;
        return hotbarKey + getView().getTopInventory().getSize() + 28;
    }

    /**
     * If the ClickAction is NUMBER_KEY, this method will return the index
     * of the pressed key (0-8).
     * @return the number on the key minus 1 (range 0-8); or -1 if not
     *     a NUMBER_KEY action
     */
    public int getHotbarButton() {
        return hotbarKey;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
