package org.bukkit.event.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;

public class InventoryPaintEvent extends InventoryEvent implements Cancellable {

    public class PaintedSlot {
        private ItemStack item;
        private ItemStack result;
        private SlotType slotType;
        private int whichSlot;
        private int rawSlot;

        private PaintedSlot(int rawSlot, int amount) {
            InventoryView view = getView();
            item = view.getItem(rawSlot);
            result = view.getCursor();
            if (result != null) {
                result = result.clone();
                result.setAmount(item.getAmount() + amount);
            }
            slotType = view.getSlotType(rawSlot);
            whichSlot = view.convertSlot(rawSlot);
            this.rawSlot = rawSlot;
        }

        /**
         * <p>Gets the item in the painted slot.</p>
         * @return The item
         */
        public ItemStack getItem() {
            return item.clone();
        }

        /**
         * <p>Gets the result item in the painted slot after the painting is done.</p>
         *
         * <p>Changes to this item stack will be reflected in the inventory.</p>
         * @return The result item
         */
        public ItemStack getResult() {
            return result;
        }

        /**
         * <p>Sets the result item in the painted slot.</p>
         * @param result The result item
         */
        public void setResult(ItemStack result) {
            this.result = result;
        }

        /**
         * <p>Gets the painted slot's type.</p>
         * @return The slot type
         */
        public SlotType getSlotType() {
            return slotType;
        }

        /**
         * <p>The slot number that was clicked, ready for passing to {@link Inventory#getItem(int)}.</p>
         *
         * <p>Note that there may be two slots with the same slot number, since a view links two different inventories.</p>
         * @return The slot number.
         */
        public int getSlot() {
            return whichSlot;
        }

        /**
         * <p>The raw slot number, which is unique for the view.</p>
         * @return The slot number.
         */
        public int getRawSlot() {
            return rawSlot;
        }
    }

    private static final HandlerList handlers = new HandlerList();
    private ItemStack cursor;
    private boolean rightClick;
    private List<PaintedSlot> slots;
    private boolean cancelled;

    public InventoryPaintEvent(InventoryView what, ItemStack cursor, boolean right, Map<Integer, Integer> slots) {
        super(what);
        this.cursor = cursor;
        this.rightClick = right;
        this.slots = new ArrayList<PaintedSlot>();
        for (Map.Entry<Integer, Integer> slot : slots.entrySet()) {
            this.slots.add(new PaintedSlot(slot.getKey(), slot.getValue()));
        }
    }

    /**
     * <p>Get the result cursor after the painting is done.</p>
     *
     * <p>Changing this item stack changes the cursor item. Note that changing the affected "painted" slots does not update this item stack to reflect the changes you've made.</p>
     *
     * <p>To get the cursor item before the painting begins, use {@link #getView()} and then {@link InventoryView#getCursor()}</p>
     * @return The cursor item
     */
    public ItemStack getCursor() {
        return this.cursor;
    }

    /**
     * <p>Sets the result cursor after the painting is done.</p>
     * @param cursor The new cursor item
     */
    public void setCursor(ItemStack cursor) {
        this.cursor = cursor;
    }

    /**
     * @return True if the click is a right-click.
     */
    public boolean isRightClick() {
        return rightClick;
    }

    /**
     * @return True if the click is a left-click.
     */
    public boolean isLeftClick() {
        return !rightClick;
    }

    /**
     * <p>Get the player who performed the click.</p>
     * @return The clicking player.
     */
    public HumanEntity getWhoClicked() {
        return getView().getPlayer();
    }

    /**
     * <p>Gets a list of painted slots.</p>
     * @return The list of painted slots.
     */
    public List<PaintedSlot> getPaintedSlots() {
        return slots;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean toCancel) {
        cancelled = toCancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
