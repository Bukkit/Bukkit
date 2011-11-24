package org.bukkit.inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

/**
 * Represents a shapeless recipe, where the arrangement of the ingredients on the crafting grid
 * does not matter.
 */
public class ShapelessRecipe implements Recipe {
    private ItemStack output;
    private ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();

    /**
     * Create a shapeless recipe to craft the specified ItemStack. The constructor merely determines the
     * result and type; to set the actual recipe, you'll need to call the appropriate methods.
     * @param result The item you want the recipe to create.
     * @see ShapelessRecipe#addIngredient(Material)
     * @see ShapelessRecipe#addIngredient(MaterialData)
     * @see ShapelessRecipe#addIngredient(Material,int)
     * @see ShapelessRecipe#addIngredient(int,Material)
     * @see ShapelessRecipe#addIngredient(int,MaterialData)
     * @see ShapelessRecipe#addIngredient(int,Material,int)
     */
    public ShapelessRecipe(ItemStack result) {
        this.output = result;
    }

    /**
     * Adds the specified ingredient.
     * @param ingredient The ingredient to add.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapelessRecipe addIngredient(MaterialData ingredient) {
        return addIngredient(1, ingredient);
    }

    /**
     * Adds the specified ingredient.
     * @param ingredient The ingredient to add.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapelessRecipe addIngredient(Material ingredient) {
        return addIngredient(1, ingredient, 0);
    }

    /**
     * Adds the specified ingredient.
     * @param ingredient The ingredient to add.
     * @param rawdata The data value, or -1 to allow any data value.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapelessRecipe addIngredient(Material ingredient, int rawdata) {
        return addIngredient(1, ingredient, rawdata);
    }

    /**
     * Adds multiples of the specified ingredient.
     * @param count How many to add (can't be more than 9!)
     * @param ingredient The ingredient to add.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapelessRecipe addIngredient(int count, MaterialData ingredient) {
        return addIngredient(count, ingredient.getItemType(), ingredient.getData());
    }

    /**
     * Adds multiples of the specified ingredient.
     * @param count How many to add (can't be more than 9!)
     * @param ingredient The ingredient to add.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapelessRecipe addIngredient(int count, Material ingredient) {
        return addIngredient(count, ingredient, 0);
    }

    /**
     * Adds multiples of the specified ingredient.
     * @param count How many to add (can't be more than 9!)
     * @param ingredient The ingredient to add.
     * @param rawdata The data value, or -1 to allow any data value.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapelessRecipe addIngredient(int count, Material ingredient, int rawdata) {
        if (ingredients.size() + count > 9) {
            throw new IllegalArgumentException("Shapeless recipes cannot have more than 9 ingredients");
        }
        while (count-- > 0) {
            ingredients.add(new ItemStack(ingredient, 1, (short) rawdata));
        }
        return this;
    }

    /**
     * Removes an ingredient from the list. If the ingredient occurs multiple times,
     * only one instance of it is removed. Only removes exact matches, with a data value
     * of 0.
     * @param ingredient The ingredient to remove
     * @return The changed recipe.
     */
    public ShapelessRecipe removeIngredient(Material ingredient) {
        return removeIngredient(ingredient, 0);
    }
    
    /**
     * Removes an ingredient from the list. If the ingredient occurs multiple times,
     * only one instance of it is removed. If the data value is -1, only ingredients
     * with a -1 data value will be removed.
     * @param ingredient The ingredient to remove
     * @return The changed recipe.
     */
    public ShapelessRecipe removeIngredient(MaterialData ingredient) {
        return removeIngredient(ingredient.getItemType(), ingredient.getData());
    }
    
    /**
     * Removes multiple instances of an ingredient from the list. If there are less instances
     * then specified, all will be removed. Only removes exact matches, with a data value
     * of 0.
     * @param count The number of copies to remove.
     * @param ingredient The ingredient to remove
     * @return The changed recipe.
     */
    public ShapelessRecipe removeIngredient(int count, Material ingredient) {
        return removeIngredient(count, ingredient, 0);
    }
    
    /**
     * Removes multiple instances of an ingredient from the list. If there are less instances
     * then specified, all will be removed. If the data value is -1, only ingredients
     * with a -1 data value will be removed.
     * @param count The number of copies to remove.
     * @param ingredient The ingredient to remove.
     * @return The changed recipe.
     */
    public ShapelessRecipe removeIngredient(int count, MaterialData ingredient) {
        return removeIngredient(count, ingredient.getItemType(), ingredient.getData());
    }

    /**
     * Removes an ingredient from the list. If the ingredient occurs multiple times,
     * only one instance of it is removed. If the data value is -1, only ingredients
     * with a -1 data value will be removed.
     * @param ingredient The ingredient to remove
     * @param rawdata The data value;
     * @return The changed recipe.
     */
    public ShapelessRecipe removeIngredient(Material ingredient, int rawdata) {
        ItemStack toRemove = new ItemStack(ingredient, 1, (short) rawdata);
        this.ingredients.remove(toRemove);
        return this;
    }

    /**
     * Removes multiple instances of an ingredient from the list. If there are less instances
     * then specified, all will be removed. If the data value is -1, only ingredients
     * with a -1 data value will be removed.
     * @param count The number of copies to remove.
     * @param ingredient The ingredient to remove.
     * @param rawdata The data value.
     * @return The changed recipe.
     */
    public ShapelessRecipe removeIngredient(int count, Material ingredient, int rawdata) {
        while (count-- > 0) {
            removeIngredient(ingredient, rawdata);
        }
        return this;
    }

    /**
     * Get the result of this recipe.
     * @return The result stack.
     */
    public ItemStack getResult() {
        return output;
    }

    /**
     * Get a copy of the list of ingredients used for this recipe.
     * @return The input list
     */
    public List<ItemStack> getIngredientList() {
        List<ItemStack> toReturn = new ArrayList<ItemStack>();
        for (ItemStack stack : ingredients) {
            toReturn.add(new ItemStack(stack.getType(), 1, stack.getDurability()));
        }
        return toReturn;
    }
}
