package org.bukkit.event.inventory;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.ImmutableSet;

/**
 * This event is called when the player drags an item in their cursor across
 * the inventory. The event is called even when only one slot is selected
 * (despite not rendering on the client) for technical reasons.
 */
public class InventoryDragEvent extends InventoryActionEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private ItemStack newCursor;
    private Map<Integer, ItemStack> addedItems;
    private Set<Integer> containerSlots;

    public InventoryDragEvent(InventoryView what, ItemStack newCursor, boolean right, Map<Integer, ItemStack> slots) {
        super(what, right ? ClickType.DRAG_RIGHT : ClickType.DRAG_LEFT, right ? InventoryAction.PLACE_DRAG_SINGLE : InventoryAction.PLACE_DRAG_EVEN);
        this.cancelled = false;
        this.newCursor = newCursor;
        this.addedItems = slots;
        ImmutableSet.Builder<Integer> b = ImmutableSet.builder();
        for (Integer slot : slots.keySet()) {
            b.add(what.convertSlot(slot));
        }
        this.containerSlots = b.build();
    }

    /**
     * Get all items to be added to the inventory in this drag.
     * @return map from raw slot id to new ItemStack
     */
    public Map<Integer, ItemStack> getNewItems() {
        return Collections.unmodifiableMap(addedItems);
    }

    /**
     * Get the slots to be changed in this InventoryDragEvent.
     * @return list of raw slot ids, suitable for InventoryView.getItem()
     */
    public Set<Integer> getRawSlots() {
        return addedItems.keySet();
    }

    /**
     * Get the slots to be changed in this InventoryDragEvent.
     * @return list of converted slot ids, suitable for Container.getItem()
     */
    public Set<Integer> getContainerSlots() {
        return containerSlots;
    }

    /**
     * Get the result cursor after the drag is done.
     * <p>
     * Changing this item stack changes the cursor item. Note that changing
     * the affected "dragged" slots does not update this item stack to
     * reflect the changes you've made.
     * <p>
     * To get the cursor item before the drag begins, use
     * {@link #getView()} and then {@link InventoryView#getCursor()}.
     * @return the cursor ItemStack
     */
    public ItemStack getNewCursor() {
        return newCursor;
    }

    /**
     * Sets the result cursor after the drag is done.
     * @param newCursor - the new cursor itemstack
     */
    public void setNewCursor(ItemStack newCursor) {
        this.newCursor = newCursor;
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
