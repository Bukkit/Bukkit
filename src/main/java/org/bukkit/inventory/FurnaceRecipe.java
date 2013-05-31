package org.bukkit.inventory;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

/**
 * Represents a smelting recipe.
 */
public class FurnaceRecipe implements Recipe {
    private ItemStack output;
    private ItemStack ingredient;
    private int hash;

    /**
     * Create a furnace recipe to craft the specified ItemStack.
     *
     * @param result The item you want the recipe to create.
     * @param source The input material.
     */
    public FurnaceRecipe(ItemStack result, Material source) {
        this(result, source, 0);
    }

    /**
     * Create a furnace recipe to craft the specified ItemStack.
     *
     * @param result The item you want the recipe to create.
     * @param source The input material.
     */
    public FurnaceRecipe(ItemStack result, MaterialData source) {
        this(result, source.getItemType(), source.getData());
    }

    /**
     * Create a furnace recipe to craft the specified ItemStack.
     *
     * @param result The item you want the recipe to create.
     * @param source The input material.
     * @param data The data value. (Note: This is currently ignored by the CraftBukkit server.)
     */
    public FurnaceRecipe(ItemStack result, Material source, int data) {
        this.output = new ItemStack(result);
        this.ingredient = new ItemStack(source, 1, (short) data);
        calculateHashCode();
    }

    /**
     * Sets the input of this furnace recipe.
     *
     * @param input The input material.
     * @return The changed recipe, so you can chain calls.
     */
    public FurnaceRecipe setInput(MaterialData input) {
        return setInput(input.getItemType(), input.getData());
    }

    /**
     * Sets the input of this furnace recipe.
     *
     * @param input The input material.
     * @return The changed recipe, so you can chain calls.
     */
    public FurnaceRecipe setInput(Material input) {
        return setInput(input, 0);
    }

    /**
     * Sets the input of this furnace recipe.
     *
     * @param input The input material.
     * @param data The data value. (Note: This is currently ignored by the CraftBukkit server.)
     * @return The changed recipe, so you can chain calls.
     */
    public FurnaceRecipe setInput(Material input, int data) {
        this.ingredient = new ItemStack(input, 1, (short) data);
        calculateHashCode();
        return this;
    }

    /**
     * Get the input material.
     *
     * @return The input material.
     */
    public ItemStack getInput() {
        return this.ingredient.clone();
    }

    /**
     * Get the result of this recipe.
     *
     * @return The resulting stack.
     */
    public ItemStack getResult() {
        return output.clone();
    }

    @Override
    public int hashCode() {
        return hash;
    }

    private void calculateHashCode() {
        // TODO use ingredient.hashCode() when furnace data PR is pulled
        hash = ("smelt:" + ingredient.getTypeId() + "=" + output.hashCode()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj instanceof FurnaceRecipe) {
            FurnaceRecipe r = (FurnaceRecipe) obj;

            if (!this.getResult().equals(r.getResult())) {
                return false;
            }

            return this.isSimilar(r);
        }

        return false;
    }

    public boolean isSimilar(Recipe recipe) {
        Validate.notNull(recipe, "Recipe can not be null.");

        if (recipe == this) {
            return true;
        }

        if (recipe instanceof FurnaceRecipe) {
            FurnaceRecipe r = (FurnaceRecipe) recipe;

            return this.getInput().getTypeId() == r.getInput().getTypeId(); // TODO use equals() on items when furnace data PR is pulled
        }

        return false;
    }
}
