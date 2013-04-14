package org.bukkit.event.inventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

/**
 * An abstract base for the various types of actions a HumanEntity can do in
 * an Inventory.
 */
public abstract class InventoryActionEvent extends InventoryEvent {
    protected final ClickType click;
    protected final InventoryAction action;

    public InventoryActionEvent(InventoryView transaction, ClickType click, InventoryAction action) {
        super(transaction);
        this.click = click;
        this.action = action;
    }

    /**
     * What the client did to trigger this action (not the result).
     */
    public enum ClickType {
        /**
         * The left (or primary) mouse button.
         */
        LEFT,
        /**
         * Holding shift while pressing the left mouse button.
         */
        SHIFT_LEFT,
        /**
         * The right mouse button.
         */
        RIGHT,
        /**
         * Holding shift while pressing the right mouse button.
         */
        SHIFT_RIGHT,
        /**
         * Clicking the left mouse button on the grey area around the
         * inventory.
         */
        WINDOW_BORDER_LEFT,
        /**
         * Clicking the right mouse button on the grey area around the
         * inventory.
         */
        WINDOW_BORDER_RIGHT,
        /**
         * The middle mouse button, or a "scrollwheel click".
         */
        MIDDLE,
        /**
         * @see InventoryDragEvent
         * A drag with the left mouse button (even item split).
         */
        DRAG_LEFT,
        /**
         * @see InventoryDragEvent
         * A drag with the right mouse button (1 item per slot).
         */
        DRAG_RIGHT,
        /**
         * One of the number keys 1-9, correspond to slots on the hotbar.
         */
        NUMBER_KEY,
        /**
         * Pressing the left mouse button twice in quick succession.
         */
        DOUBLE_CLICK,
        /**
         * The "Drop" key (defaults to Q).
         */
        DROP,
        /**
         * Holding Ctrl while pressing the "Drop" key (defaults to Q).
         */
        CONTROL_DROP,
        /**
         * Any action done with the Creative inventory open.
         */
        CREATIVE,
        /**
         * A type of inventory manipulation not yet recognized by Bukkit.
         * This is only for transitional purposes on a new Minecraft update,
         * and should never be relied upon.
         * <p>
         * Any ClickAction.UNKNOWN is called on a best-effort basis.
         */
        UNKNOWN,
        ;
    }

    /**
     * An estimation of what the result will be.
     */
    public enum InventoryAction {
        /**
         * Nothing will happen from the click.
         * There may be cases where nothing will happen and this is value is
         * not provided, but it is guaranteed that this value is accurate
         * when given.
         */
        NOTHING,
        /**
         * All of the items on the clicked slot are moved to the cursor.
         */
        PICKUP_ALL,
        /**
         * Some of the items on the clicked slot are moved to the cursor.
         */
        PICKUP_SOME,
        /**
         * Half of the items on the clicked slot are moved to the cursor.
         */
        PICKUP_HALF,
        /**
         * One of the items on the clicked slot are moved to the cursor.
         */
        PICKUP_ONE,
        /**
         * All of the items on the cursor are moved to the clicked slot.
         */
        PLACE_ALL,
        /**
         * Some of the items from the cursor are moved to the clicked slot
         * (usually up to the max stack size).
         */
        PLACE_SOME,
        /**
         * A single item from the cursor is moved to the clicked slot.
         */
        PLACE_ONE,
        /**
         * The clicked item and the cursor are exchanged.
         */
        SWAP_WITH_CURSOR,
        /**
         * The entire cursor item is dropped.
         */
        DROP_ALL_CURSOR,
        /**
         * One item is dropped from the cursor.
         */
        DROP_ONE_CURSOR,
        /**
         * The entire clicked slot is dropped.
         */
        DROP_ALL_SLOT,
        /**
         * One item is dropped from the clicked slot.
         */
        DROP_ONE_SLOT,
        /**
         * The item is moved to the opposite inventory if a space is found.
         */
        MOVE_TO_OTHER_INVENTORY,
        /**
         * The clicked item is moved to the hotbar, and the item currently
         * there is re-added to the player's inventory.
         */
        HOTBAR_MOVE_AND_READD,
        /**
         * The clicked slot and the picked hotbar slot are swapped.
         */
        HOTBAR_SWAP,
        /**
         * A max-size stack of the clicked item is put on the cursor.
         */
        CLONE_STACK,
        /**
         * The inventory is searched for the same material, and they are put
         * on the cursor up to {@link org.bukkit.Material#getMaxStackSize()}.
         */
        COLLECT_TO_CURSOR,
        /**
         * The progress of the drag is reset. This usually means a bad packet
         * ordering.
         * @see InventoryDragEvent
         */
        RESET_DRAG_STATE,
        /**
         * A drag is started.
         * @see InventoryDragEvent
         */
        BEGIN_DRAG,
        /**
         * A slot is added to the drag.
         * @see InventoryDragEvent
         */
        DRAG_ADD_SLOT,
        /**
         * The drag is finished.
         * @see InventoryDragEvent
         */
        FINISH_DRAG,
        /**
         * One item from the cursor is placed in each selected slot.
         */
        PLACE_DRAG_SINGLE,
        /**
         * The cursor is split evenly across all selected slots, not to
         * exceed the Material's max stack size, with the remainder going to
         * the cursor.
         */
        PLACE_DRAG_EVEN,
        /**
         * An unrecognized ClickType.
         */
        UNKNOWN,
        ;
    }

    /**
     * Get the current item on the cursor.
     * @return The cursor item
     */
    public ItemStack getCursor() {
        return getView().getCursor();
    }

    /**
     * Set the item on the cursor.
     * @param what The new cursor item.
     */
    public void setCursor(ItemStack what) {
        getView().setCursor(what);
    }

    /**
     * Get the ClickType for this event.
     * <p>
     * This is insulated against changes to the inventory by other plugins.
     * @return the type of inventory click
     */
    public ClickType getClick() {
        return click;
    }

    /**
     * Get the InventoryAction for this event.
     * <p>
     * This may become incorrect as other plugins change the inventory.
     * @return the approximate action
     */
    public InventoryAction getAction() {
        return action;
    }

    /**
     * @return True if the click action is with the right mouse button.
     */
    public boolean isRightClick() {
        return (click == ClickType.RIGHT) || (click == ClickType.SHIFT_RIGHT) || (click == ClickType.DRAG_RIGHT);
    }

    /**
     * @return True if the click action is with the left mouse button.
     */
    public boolean isLeftClick() {
        return (click == ClickType.LEFT) || (click == ClickType.SHIFT_LEFT) || (click == ClickType.DRAG_LEFT) || (click == ClickType.DOUBLE_CLICK) || (click == ClickType.CREATIVE);
    }

    /**
     * Shift and Ctrl can be combined with some actions as a modifier.
     * @return True if the action uses Shift or Ctrl.
     */
    public boolean isShiftClick() {
        return (click == ClickType.SHIFT_LEFT) || (click == ClickType.SHIFT_RIGHT) || (click == ClickType.CONTROL_DROP);
    }

    public boolean isKeyboardClick() {
        return (click == ClickType.NUMBER_KEY) || (click == ClickType.DROP) || (click == ClickType.CONTROL_DROP);
    }

    /**
     * Some click events are only permitted in Creative mode.
     * @return True if this action normally requires Creative mode
     */
    public boolean isCreativeAction() {
        return (click == ClickType.MIDDLE) || (click == ClickType.CREATIVE);
    }

    /**
     * Get the player who performed the click.
     * @return The clicking player.
     */
    public HumanEntity getWhoClicked() {
        return getView().getPlayer();
    }

    /**
     * Convenience alias for {@link #getWhoClicked()}
     */
    public HumanEntity getPlayer() {
        return getView().getPlayer();
    }
}
