package org.bukkit.event.inventory;

import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.BeaconInventory;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.MerchantInventory;

/**
 * These constants specify the style and appearance of an inventory, not its use.
 * Thus, any inventory which uses the basic nine-per-row layout is of type CHEST, for example.
 */
public enum InventoryType {
    /**
     * A chest inventory, with 0, 9, 18, 27, 36, 45, or 54 slots of type
     * CONTAINER.
     */
    CHEST(27,"Chest", Inventory.class),
    /**
     * A dispenser inventory, with 9 slots of type CONTAINER.
     */
    DISPENSER(9,"Dispenser", Inventory.class),
    /**
     * A dropper inventory, with 9 slots of type CONTAINER.
     * @deprecated Will be replaced with {@link InventoryType#DISPENSER}
     */
    @Deprecated
    DROPPER(9, "Dropper", Inventory.class),
    /**
     * A furnace inventory, with a RESULT slot, a CRAFTING slot, and a FUEL
     * slot.
     */
    FURNACE(3,"Furnace", FurnaceInventory.class),
    /**
     * A workbench inventory, with 9 CRAFTING slots and a RESULT slot.
     */
    WORKBENCH(10,"Crafting", CraftingInventory.class),
    /**
     * A player's crafting inventory, with 4 CRAFTING slots and a RESULT slot.
     * Also implies that the 4 ARMOR slots are accessible.
     */
    CRAFTING(5,"Crafting", CraftingInventory.class),
    /**
     * An enchantment table inventory, with one CRAFTING slot and three
     * enchanting buttons.
     */
    ENCHANTING(1,"Enchanting", EnchantingInventory.class),
    /**
     * A brewing stand inventory, with one FUEL slot and three CRAFTING slots.
     */
    BREWING(4,"Brewing", BrewerInventory.class),
    /**
     * A player's inventory, with 9 QUICKBAR slots, 27 CONTAINER slots, and 4
     * ARMOR slots. The ARMOUR slots may not be visible to the player, though.
     */
    PLAYER(36,"Player", Inventory.class),
    /**
     * The creative mode inventory, with only 9 QUICKBAR slots and nothing
     * else. (The actual creative interface with the items is client-side and
     * cannot be altered by the server.)
     */
    CREATIVE(9,"Creative", Inventory.class),
    /**
     * The merchant inventory, with 2 TRADE-IN slots, and 1 RESULT slot.
     */
    MERCHANT(3,"Villager", MerchantInventory.class),
    /**
     * The ender chest inventory, with 27 slots.
     * @deprecated Will be replaced with {@link InventoryType#CHEST}
     */
    @Deprecated
    ENDER_CHEST(27,"Ender Chest", Inventory.class),
    /**
     * An anvil inventory, with 2 CRAFTING slots and 1 RESULT slot
     */
    ANVIL(3, "Repairing", AnvilInventory.class),
    /**
     * A beacon inventory, with 1 CRAFTING slot
     */
    BEACON(1, "container.beacon", BeaconInventory.class),
    /**
     * A hopper inventory, with 5 slots of type CONTAINER.
     */
    HOPPER(5, "Item Hopper", Inventory.class),
    /**
     * A horse inventory, with 2 slots of type ARMOR.
     * Used by all horses that don't have a chest attached.
     * The second armor slot may not be visible in the GUI.
     */
    HORSE(2, "Horse", HorseInventory.class),
    /**
     * A horse inventory, with 2 slots of type ARMOR and 15 slots of type CONTAINER.
     * The second armor slot is not visible in the GUI.
     */
    HORSE_PACK(17, "Horse", HorseInventory.class),
    ;

    private final int size;
    private final String title;
    private final Class<? extends Inventory> invenInterface;

    private InventoryType(int defaultSize, String defaultTitle, Class<? extends Inventory> iface) {
        size = defaultSize;
        title = defaultTitle;
        invenInterface = iface;
    }

    public int getDefaultSize() {
        return size;
    }

    public String getDefaultTitle() {
        return title;
    }

    public Class<? extends Inventory> getInventoryClass() {
        return invenInterface;
    }

    public enum SlotType {
        /**
         * A result slot in a furnace or crafting inventory.
         */
        RESULT,
        /**
         * A slot in the crafting matrix, or the input slot in a furnace
         * inventory, the potion slot in the brewing stand, or the enchanting
         * slot.
         */
        CRAFTING,
        /**
         * An armour slot in the player's inventory.
         */
        ARMOR,
        /**
         * A regular slot in the container or the player's inventory; anything
         * not covered by the other enum values.
         */
        CONTAINER,
        /**
         * A slot in the bottom row or quickbar.
         */
        QUICKBAR,
        /**
         * A pseudo-slot representing the area outside the inventory window.
         */
        OUTSIDE,
        /**
         * The fuel slot in a furnace inventory, or the ingredient slot in a
         * brewing stand inventory.
         */
        FUEL;
    }
}
