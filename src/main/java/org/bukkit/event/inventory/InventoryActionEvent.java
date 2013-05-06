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
