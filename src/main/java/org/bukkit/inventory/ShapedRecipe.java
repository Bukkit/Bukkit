package org.bukkit.inventory;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

/**
 * Represents a shaped (ie normal) crafting recipe.
 */
public class ShapedRecipe implements Recipe {
    private ItemStack output;
    private String[] rows;
    private HashMap<Character, ItemStack> ingredients = new HashMap<Character, ItemStack>();

    /**
     * Create a shaped recipe to craft the specified ItemStack. The constructor merely determines the
     * result and type; to set the actual recipe, you'll need to call the appropriate methods.
     * @param result The item you want the recipe to create.
     * @see ShapedRecipe#shape(String...)
     * @see ShapedRecipe#setIngredient(char, Material)
     * @see ShapedRecipe#setIngredient(char, Material, int)
     * @see ShapedRecipe#setIngredient(char, MaterialData)
     */
    public ShapedRecipe(ItemStack result) {
        this.output = result;
    }

    /**
     * Set the shape of this recipe to the specified rows. Each character represents a different
     * ingredient; exactly what each character represents is set separately.
     * @param shape The rows of the recipe (up to 3 rows).
     * @return The changed recipe, so you can chain calls.
     */
    public ShapedRecipe shape(String... shape) {
        if (shape == null || shape.length > 3 || shape.length < 1) {
            throw new IllegalArgumentException("Crafting recipes should be 1, 2, or 3 rows.");
        }
        for (String row : shape) {
            if (row == null || row.length() > 3 || row.length() < 1) {
                throw new IllegalArgumentException("Crafting rows should be 1, 2, or 3 characters.");
            }
        }
        this.rows = shape;

        // Remove character mappings for characters that no longer exist in the shape
        HashMap<Character, ItemStack> ingredientsTemp = this.ingredients;

        this.ingredients = new HashMap<Character, ItemStack>();
        for (char key : ingredientsTemp.keySet()) {
            try {
                setIngredient(key, ingredientsTemp.get(key).getType(), ingredientsTemp.get(key).getDurability());
            } catch (IllegalArgumentException e) {}
        }
        return this;
    }

    /**
     * Sets the material that a character in the recipe shape refers to.
     * @param key The character that represents the ingredient in the shape.
     * @param ingredient The ingredient.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapedRecipe setIngredient(char key, MaterialData ingredient) {
        return setIngredient(key, ingredient.getItemType(), ingredient.getData());
    }

    /**
     * Sets the material that a character in the recipe shape refers to.
     * @param key The character that represents the ingredient in the shape.
     * @param ingredient The ingredient.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapedRecipe setIngredient(char key, Material ingredient) {
        return setIngredient(key, ingredient, 0);
    }

    /**
     * Sets the material that a character in the recipe shape refers to.
     * @param key The character that represents the ingredient in the shape.
     * @param ingredient The ingredient.
     * @param raw The raw material data as an integer.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapedRecipe setIngredient(char key, Material ingredient, int raw) {
        if (!hasKey(key)) {
            throw new IllegalArgumentException("Symbol " + key + " does not appear in the shape.");
        }
        ingredients.put(key, new ItemStack(ingredient, 1, (short) raw));
        return this;
    }

    private boolean hasKey(char c) {
        String key = Character.toString(c);

        for (String row : rows) {
            if (row.contains(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get a copy of the ingredients map.
     * @return The mapping of character to ingredients.
     */
    public Map<Character, ItemStack> getIngredientMap() {
        Map<Character, ItemStack> toReturn = new HashMap<Character, ItemStack>();
        for (char c : ingredients.keySet()) {
            toReturn.put(c, new ItemStack(ingredients.get(c).getType(), 1, ingredients.get(c).getDurability()));
        }
        return toReturn;
    }

    /**
     * Get the shape.
     * @return The recipe's shape.
     */
    public String[] getShape() {
        return rows;
    }

    /**
     * Get the result.
     * @return The result stack.
     */
    public ItemStack getResult() {
        return output;
    }
}
