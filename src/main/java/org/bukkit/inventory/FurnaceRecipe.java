package org.bukkit.inventory;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

/**
 * Represents a smelting recipe.
 */
public class FurnaceRecipe implements Recipe {
    private ItemStack output;
    private ItemStack ingredient;

    /**
     * Create a furnace recipe to craft the specified ItemStack.
     * @param result The item you want the recipe to create.
     * @param source The input material.
     */
    public FurnaceRecipe(ItemStack result, Material source) {
        this(result, source, 0);
        if (this.ingredient == null) {
            setInput(new MaterialData(source));
        }
    }

    /**
     * Create a furnace recipe to craft the specified ItemStack.
     * @param result The item you want the recipe to create.
     * @param source The input material.
     */
    public FurnaceRecipe(ItemStack result, MaterialData source) {
        this(result, source.getItemType(), source.getData());
    }

    /**
     * Create a furnace recipe to craft the specified ItemStack.
     * @param result The item you want the recipe to create.
     * @param source The input material.
     * @param data The data value. (Note: This is currently ignored by the CraftBukkit server.)
     */
    public FurnaceRecipe(ItemStack result, Material source, int data) {
        this.output = result;
        this.ingredient = new ItemStack(source, 1, (short) data);
    }

    /**
     * Sets the input of this furnace recipe.
     * @param input The input material.
     * @return The changed recipe, so you can chain calls.
     */
    public FurnaceRecipe setInput(MaterialData input) {
        return setInput(input.getItemType(), input.getData());
    }

    /**
     * Sets the input of this furnace recipe.
     * @param input The input material.
     * @return The changed recipe, so you can chain calls.
     */
    public FurnaceRecipe setInput(Material input) {
        return setInput(input, 0);
    }
    
    /**
     * Sets the input of this furnace recipe.
     * @param input The input material.
     * @param data The data value. (Note: This is currently ignored by the CraftBukkit server.)
     * @return The changed recipe, so you can chain calls.
     */
    public FurnaceRecipe setInput(Material input, int data) {
        this.ingredient = new ItemStack(input, 1, (short) data);
        return this;
    }

    /**
     * Get a copy of the input material.
     * @return The input material.
     */
    public ItemStack getInput() {
        return new ItemStack(ingredient.getType(), 1, ingredient.getDurability());
    }

    /**
     * Get the result of this recipe.
     * @return The resulting stack.
     */
    public ItemStack getResult() {
        return output;
    }
}
