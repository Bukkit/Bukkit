package org.bukkit.event.inventory;

import org.bukkit.block.Block;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

public class BrewEndEvent extends BrewEvent {
    private ItemStack ingredient;

    /**
     * This event is called after an ingredient is consumed in a brewing stand.
     * @param brewer 
     * @param contents
     * @param ingredient
     */
    public BrewEndEvent(Block brewer, BrewerInventory contents, ItemStack ingredient) {
        super(brewer, contents);
        this.ingredient = ingredient;
    }

    /**
     * Returns the ingredient used in this brewing session.
     * @return The ingredient used as an {@link org.bukkit.inventory.ItemStack}.
     */
    public ItemStack getIngredient() {
        return ingredient;
    }
}
