package org.bukkit.inventory;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

public class ShapelessRecipe implements Recipe {
    private ItemStack output;
    private ArrayList<MaterialData> ingred = new ArrayList<MaterialData>();

    /**
     * Create a shapeless recipe to craft the specified ItemStack. The constructor merely determines the
     * result and type; to set the actual recipe, you'll need to call the appropriate methods.
     * @param result The item you want the recipe to create.
     * @param type The type of recipe you want this to be.
     * @see ShapelessRecipe#addIngredient(Material)
     * @see ShapelessRecipe#addIngredient(MaterialData)
     */
    public ShapelessRecipe(ItemStack result) {
        this.output = result;
    }
    
    /**
     * Make this recipe shapeless and adds the specified ingredient.
     * @param ingredient The ingredient to add.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapelessRecipe addIngredient(MaterialData ingredient) {
        if(ingred.size() == 9)
            throw new IllegalArgumentException("Shapeless recipes cannot have more than 9 ingredients or less than 1");
        ingred.add(ingredient);
        return this;
    }
    
    /**
     * Make this recipe shapeless and adds the specified ingredient.
     * @param ingredient The ingredient to add.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapelessRecipe addIngredient(Material ingredient) {
        MaterialData data = ingredient.getNewData((byte) 0);
        if(data == null) data = new MaterialData(ingredient);
        return addIngredient(data);
    }
    
    /**
     * Make this recipe shapeless and adds the specified ingredient.
     * @param ingredient The ingredient to add.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapelessRecipe addIngredient(Material ingredient, int rawdata) {
        MaterialData data = ingredient.getNewData((byte) rawdata);
        if(data == null) data = new MaterialData(ingredient, (byte) rawdata);
        return addIngredient(data);
    }

    /**
     * Get the result of this recipe.
     */
    public ItemStack getResult() {
        return output;
    }
    
    /**
     * Get the list of ingredients used for this recipe.
     */
    public ArrayList<MaterialData> getIngredientList() {
        return ingred;
    }

}
