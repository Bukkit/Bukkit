package org.bukkit.event.inventory;

import org.bukkit.inventory.InventoryView;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;

public class InventorySwapEvent extends InventoryClickEvent {
    private static final HandlerList handlers = new HandlerList();
    private int numberPressed;

    /**
     * Fired when a player uses the number keys
     * to swap between items to the hotbar 
     * (feature added in snapshot-12w40a).
     * {@link #getHotbarRawSlot()} returns raw slot number which was swapped to.
     * {@link #getHotbarSlot()} returns the slot number which was swapped to (1-9).
     * 
     * @param numberPressed the number key the player pressed (1-9)
     */
    public InventorySwapEvent(InventoryView what, SlotType type, int slot, int numberPressed) {
        super(what, type, slot, false, false);
        this.numberPressed = numberPressed;
    }

    /**
     * {@inheritDoc}
     */
    public ItemStack getCursor() {
        return getView().getItem(getHotbarRawSlot());
    }

    /**
     * {@inheritDoc} <p><i>Note: Swap events don't use mouse buttons; this method always returns false.</i></p>
     */
    @Override
    public boolean isLeftClick() {
        return false;
    }

    /**
     * {@inheritDoc} <p><i>Note: Swap events don't use mouse buttons; this method always returns false.</i></p>
     */
    @Override
    public boolean isRightClick() {
        return false;
    }

    /**
     * {@inheritDoc} <p><i>Note: Swap events don't use mouse buttons; this method always returns false.</i></p>
     */
    @Override
    public boolean isShiftClick() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void setCursor(ItemStack what) {
        getView().setItem(getHotbarRawSlot(), what);
    }

    /**
     * The raw slot number of the slot which was swapped to, which is unique for the view.
     * @return The slot number.
     */
    public int getHotbarRawSlot() {
        return getView().countSlots() - (9 - numberPressed);
    }

    /**
     * The hotbar slot which is being swapped with the item under the cursor. (from 1-9).
     * @return The hotbar slot from which to be swapped.
     */
    public int getHotbarSlot() {
        return numberPressed;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}