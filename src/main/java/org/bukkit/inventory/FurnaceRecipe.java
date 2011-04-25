package org.bukkit.inventory;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

public class FurnaceRecipe implements Recipe {
    private ItemStack output;
    private MaterialData ingred;

    /**
     * Create a furnace recipe to craft the specified ItemStack.
     * @param result The item you want the recipe to create.
     * @param source The input material.
     */
    public FurnaceRecipe(ItemStack result, Material source) {
        this.output = result;
        this.ingred = source.getNewData((byte) 0);
        if(this.ingred == null)
            this.ingred = new MaterialData(source);
    }

    /**
     * Create a furnace recipe to craft the specified ItemStack.
     * @param result The item you want the recipe to create.
     * @param source The input material.
     */
    public FurnaceRecipe(ItemStack result, MaterialData source) {
        this.output = result;
        this.ingred = source;
    }
    
    /**
     * Sets the input of this furnace recipe.
     * @param input The input material.
     * @return The changed recipe, so you can chain calls.
     */
    public FurnaceRecipe setInput(MaterialData input) {
        this.ingred = input;
        return this;
    }
    
    /**
     * Sets the input of this furnace recipe.
     * @param input The input material.
     * @return The changed recipe, so you can chain calls.
     */
    public FurnaceRecipe setInput(Material input) {
        this.ingred = input.getNewData((byte) 0);
        if(this.ingred == null)
            this.ingred = new MaterialData(input);
        return this;
    }
    
    /**
     * Get the input material.
     */
    public MaterialData getInput() {
        return (MaterialData) ingred;
    }

    /**
     * Get the result of this recipe.
     */
    public ItemStack getResult() {
        return output;
    }

}
