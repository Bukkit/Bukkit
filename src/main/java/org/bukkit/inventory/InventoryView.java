package org.bukkit.inventory;

import org.bukkit.GameMode;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.potion.PotionEffectType;

/**
 * Represents a view linking two inventories and a single player (whose
 * inventory may or may not be one of the two).
 * <p>
 * Note: If you implement this interface but fail to satisfy the expected
 * contracts of certain methods, there's no guarantee that the game will work
 * as it should.
 */
public abstract class InventoryView {
    public final static int OUTSIDE = -999;
    /**
     * Represents various extra properties of certain inventory windows.
     */
    public enum Property {
        /**
         * The progress of the down-pointing arrow in a brewing inventory.
         * This also affects the bubbles.
         */
        BREW_TIME(0, InventoryType.BREWING, Integer.class),
        /**
         * The progress of the right-pointing arrow in a furnace inventory.
         */
        COOK_TIME(0, InventoryType.FURNACE, Integer.class),
        /**
         * The progress of the flame in a furnace inventory.
         */
        BURN_TIME(1, InventoryType.FURNACE, Integer.class),
        /**
         * How many total ticks the current fuel should last.
         * This is in effect the BURN_TIME that will make the flame full.
         */
        TICKS_FOR_CURRENT_FUEL(2, InventoryType.FURNACE, Integer.class),
        /**
         * In an enchanting inventory, the top button's experience level
         * value.
         */
        ENCHANT_BUTTON1(0, InventoryType.ENCHANTING, Integer.class),
        /**
         * In an enchanting inventory, the middle button's experience level
         * value.
         */
        ENCHANT_BUTTON2(1, InventoryType.ENCHANTING, Integer.class),
        /**
         * In an enchanting inventory, the bottom button's experience level
         * value.
         */
        ENCHANT_BUTTON3(2, InventoryType.ENCHANTING, Integer.class),
        /**
         * In an anvil inventory, the reported enchantment cost
         */
        ANVIL_COST(0, InventoryType.ANVIL, Integer.class),
        /**
         * In a beacon inventory, the level of effects it enables.
         */
        BEACON_LEVEL(0, InventoryType.BEACON, Integer.class),
        /**
         * In a beacon inventory, the primary potion effect.
         * Only displayed potion effects are accepted.
         */
        BEACON_PRIMARY(1, InventoryType.BEACON, PotionEffectType.class),
        /**
         * In a beacon inventory, the secondary potion effect.
         * Only displayed potion effects are accepted.
         */
        BEACON_SECONDARY(2, InventoryType.BEACON, PotionEffectType.class),
        ;
        int id;
        InventoryType style;
        Class<?> clz;
        private Property(int id, InventoryType appliesTo, Class<?> value) {
            this.id = id;
            style = appliesTo;
            clz = value;
        }

        public InventoryType getType() {
            return style;
        }

        public Class<?> getValueType() {
            return clz;
        }

        /**
         *
         * @deprecated Magic value
         */
        @Deprecated
        public int getId() {
            return id;
        }
    }
    /**
     * Get the upper inventory involved in this transaction.
     *
     * @return the inventory
     */
    public abstract Inventory getTopInventory();

    /**
     * Get the lower inventory involved in this transaction.
     *
     * @return the inventory
     */
    public abstract Inventory getBottomInventory();

    /**
     * Get the player viewing.
     *
     * @return the player
     */
    public abstract HumanEntity getPlayer();

    /**
     * Determine the type of inventory involved in the transaction. This
     * indicates the window style being shown. It will never return PLAYER,
     * since that is common to all windows.
     *
     * @return the inventory type
     */
    public abstract InventoryType getType();

    /**
     * Sets one item in this inventory view by its raw slot ID.
     * <p>
     * Note: If slot ID -999 is chosen, it may be expected that the item is
     * dropped on the ground. This is not required behaviour, however.
     *
     * @param slot The ID as returned by InventoryClickEvent.getRawSlot()
     * @param item The new item to put in the slot, or null to clear it.
     */
    public void setItem(int slot, ItemStack item) {
        if (slot != OUTSIDE) {
            if (slot < getTopInventory().getSize()) {
                getTopInventory().setItem(convertSlot(slot),item);
            } else {
                getBottomInventory().setItem(convertSlot(slot),item);
            }
        } else {
            getPlayer().getWorld().dropItemNaturally(getPlayer().getLocation(), item);
        }
    }

    /**
     * Gets one item in this inventory view by its raw slot ID.
     *
     * @param slot The ID as returned by InventoryClickEvent.getRawSlot()
     * @return The item currently in the slot.
     */
    public ItemStack getItem(int slot) {
        if (slot == OUTSIDE) {
            return null;
        }
        if (slot < getTopInventory().getSize()) {
            return getTopInventory().getItem(convertSlot(slot));
        } else {
            return getBottomInventory().getItem(convertSlot(slot));
        }
    }

    /**
     * Sets the item on the cursor of one of the viewing players.
     *
     * @param item The item to put on the cursor, or null to remove the item
     *     on their cursor.
     */
    public final void setCursor(ItemStack item) {
        getPlayer().setItemOnCursor(item);
    }

    /**
     * Get the item on the cursor of one of the viewing players.
     *
     * @return The item on the player's cursor, or null if they aren't holding
     *     one.
     */
    public final ItemStack getCursor() {
        return getPlayer().getItemOnCursor();
    }

    /**
     * Converts a raw slot ID into its local slot ID into whichever of the two
     * inventories the slot points to.
     * <p>
     * If the raw slot refers to the upper inventory, it will be returned
     * unchanged and thus be suitable for getTopInventory().getItem(); if it
     * refers to the lower inventory, the output will differ from the input
     * and be suitable for getBottomInventory().getItem().
     *
     * @param rawSlot The raw slot ID.
     * @return The converted slot ID.
     */
    public final int convertSlot(int rawSlot) {
        int numInTop = getTopInventory().getSize();
        if (rawSlot < numInTop) {
            return rawSlot;
        }
        int slot = rawSlot - numInTop;
        if (getPlayer().getGameMode() == GameMode.CREATIVE && getType() == InventoryType.PLAYER) {
            return slot;
        }
        if (getType() == InventoryType.CRAFTING) {
            if(slot < 4) return 39 - slot;
            else slot -= 4;
        }
        if (slot >= 27) slot -= 27;
        else slot += 9;
        return slot;
    }

    /**
     * Closes the inventory view.
     */
    public final void close() {
        getPlayer().closeInventory();
    }

    /**
     * Check the total number of slots in this view, combining the upper and
     * lower inventories.
     * <p>
     * Note though that it's possible for this to be greater than the sum of
     * the two inventories if for example some slots are not being used.
     *
     * @return The total size
     */
    public final int countSlots() {
        return getTopInventory().getSize() + getBottomInventory().getSize();
    }

    /**
     * Sets an extra property of this inventory if supported by that
     * inventory, for example the state of a progress bar.
     *
     * @param prop the window property to update
     * @param value the new value for the window property
     * @return true if the property was updated successfully, false if the
     *     property is not supported by that inventory
     * @deprecated In favour of {@link #setIntegerProperty(Property, int)}.
     */
    @Deprecated
    public final boolean setProperty(Property prop, int value) {
        return getPlayer().setWindowProperty(prop, value);
    }

    /**
     * Sets an extra property of this inventory if supported by that
     * inventory, for example the state of a progress bar.
     *
     * @param <T> The type of the property, as specified by the {@link Property} constant.
     * @param prop the window property to update
     * @param value the new value for the window property
     * @return true if the property was updated successfully, false if the
     *     property is not supported by that inventory or the wrong type was passed
     */
    public final <T> boolean setProperty(Property prop, T value) {
        return getPlayer().setWindowProperty(prop, value);
    }

    /**
     * Sets an extra property of this inventory if supported by that
     * inventory, for example the state of a progress bar.
     *
     * @param prop the window property to update
     * @param value the new value for the window property
     * @return true if the property was updated successfully, false if the
     *     property is not supported by that inventory
     */
    public final boolean setIntegerProperty(Property prop, int value) {
        return getPlayer().setWindowProperty(prop, Integer.valueOf(value));
    }

    /**
     * Fetches an extra property of this inventory if supported by that
     * inventory, for example the state of a progress bar.
     *
     * @param prop The property to check
     * @return The current value of the property, or null if unsupported by that inventory
     */
    public final Object getProperty(Property prop) {
        return getPlayer().getWindowProperty(prop);
    }

    /**
     * Fetches an extra property of this inventory if supported by that
     * inventory, for example the state of a progress bar.
     *
     * @param prop The property to check
     * @return The current value of the property, or null if unsupported by that inventory
     */
    public final int getIntegerProperty(Property prop) {
        return (Integer) getProperty(prop);
    }

    /**
     * Get the title of this inventory window.
     *
     * @return The title.
     */
    public String getTitle() {
        return getTopInventory().getTitle();
    }
}
