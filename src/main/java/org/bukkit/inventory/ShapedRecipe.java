package org.bukkit.inventory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.Validate;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

/**
 * Represents a shaped (ie normal) crafting recipe.
 */
public class ShapedRecipe implements Recipe {
    private ItemStack output;
    private String[] rows;
    private Map<Character, ItemStack> ingredients = new HashMap<Character, ItemStack>();

    /**
     * Create a shaped recipe to craft the specified ItemStack. The constructor merely determines the
     * result and type; to set the actual recipe, you'll need to call the appropriate methods.
     *
     * @param result The item you want the recipe to create.
     * @see ShapedRecipe#shape(String...)
     * @see ShapedRecipe#setIngredient(char, Material)
     * @see ShapedRecipe#setIngredient(char, Material, int)
     * @see ShapedRecipe#setIngredient(char, MaterialData)
     */
    public ShapedRecipe(ItemStack result) {
        this.output = new ItemStack(result);
    }

    /**
     * Set the shape of this recipe to the specified rows. Each character represents a different
     * ingredient; exactly what each character represents is set separately.
     *
     * @param shape The rows of the recipe (up to 3 rows).
     * @return The changed recipe, so you can chain calls.
     */
    public ShapedRecipe shape(final String... shape) {
        Validate.notNull(shape, "Must provide a shape");
        Validate.isTrue(shape.length > 0 && shape.length < 4, "Crafting recipes should be 1, 2, 3 rows, not ", shape.length);

        for (String row : shape) {
            Validate.notNull(row, "Shape cannot have null rows");
            Validate.isTrue(row.length() > 0 && row.length() < 4, "Crafting rows should be 1, 2, or 3 characters, not ", row.length());
        }
        this.rows = new String[shape.length];
        for (int i = 0; i < shape.length; i++) {
            this.rows[i] = shape[i];
        }

        // Remove character mappings for characters that no longer exist in the shape
        HashMap<Character, ItemStack> newIngredients = new HashMap<Character, ItemStack>();
        for (String row : shape) {
            for (Character c : row.toCharArray()) {
                newIngredients.put(c, ingredients.get(c));
            }
        }
        this.ingredients = newIngredients;

        return this;
    }

    /**
     * Sets the material that a character in the recipe shape refers to.
     *
     * @param key The character that represents the ingredient in the shape.
     * @param ingredient The ingredient.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapedRecipe setIngredient(char key, MaterialData ingredient) {
        return setIngredient(key, ingredient.getItemType(), ingredient.getData());
    }

    /**
     * Sets the material that a character in the recipe shape refers to.
     *
     * @param key The character that represents the ingredient in the shape.
     * @param ingredient The ingredient.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapedRecipe setIngredient(char key, Material ingredient) {
        return setIngredient(key, ingredient, 0);
    }

    /**
     * Sets the material that a character in the recipe shape refers to.
     *
     * @param key The character that represents the ingredient in the shape.
     * @param ingredient The ingredient.
     * @param raw The raw material data as an integer.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapedRecipe setIngredient(char key, Material ingredient, int raw) {
        Validate.isTrue(ingredients.containsKey(key), "Symbol does not appear in the shape:", key);
        ingredients.put(key, new ItemStack(ingredient, 1, (short) raw));
        return this;
    }

    /**
     * Get a copy of the ingredients map.
     *
     * @return The mapping of character to ingredients.
     */
    public Map<Character, ItemStack> getIngredientMap() {
        HashMap<Character, ItemStack> result = new HashMap<Character, ItemStack>();
        for (Map.Entry<Character, ItemStack> ingredient : ingredients.entrySet()) {
            if (ingredient.getValue() == null) {
                result.put(ingredient.getKey(), null);
            } else {
                result.put(ingredient.getKey(), ingredient.getValue().clone());
            }
        }
        return result;
    }

    /**
     * Get the shape.
     *
     * @return The recipe's shape.
     */
    public String[] getShape() {
        return rows.clone();
    }

    /**
     * Get the result.
     *
     * @return The result stack.
     */
    public ItemStack getResult() {
        return output.clone();
    }

    public boolean matches(Recipe other) {
        if(other == null) {
            return false;
        }
        if(!(other instanceof ShapedRecipe)) {
            return false;
        }
        ShapedRecipe sother = (ShapedRecipe) other;
        boolean outputm = sother.output.equals(output);
        boolean ingredientsm = sother.ingredients.equals(ingredients);
        boolean rowsm = false;
        if(rows == null && sother.rows == null) {
            rowsm = true;
        }
        if((rows == null) != (sother.rows == null)) {
            rowsm = false;
        }
        if(rows != null && sother.rows != null) {
            rowsm = Arrays.equals(rows, sother.rows);
        }
        return outputm && ingredientsm && rowsm;
    }
}
