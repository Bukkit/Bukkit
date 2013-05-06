package org.bukkit.event.inventory;

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