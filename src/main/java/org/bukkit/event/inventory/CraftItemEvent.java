package org.bukkit.event.inventory;

import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.Recipe;

public class CraftItemEvent extends InventoryClickEvent {
    private Recipe recipe;

    @Deprecated
    public CraftItemEvent(Recipe recipe, InventoryView what, SlotType type, int slot, boolean right, boolean shift) {
        this(recipe, what, type, slot, (right ? MouseButton.RIGHT : MouseButton.LEFT), shift);
    }

    public CraftItemEvent(Recipe recipe, InventoryView what, SlotType type, int slot, MouseButton button, boolean shift) {
        super(what, type, slot, button, shift);
        this.recipe = recipe;
    }

    /**
     * @return A copy of the current recipe on the crafting matrix.
     */
    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public CraftingInventory getInventory() {
        return (CraftingInventory) super.getInventory();
    }
}
