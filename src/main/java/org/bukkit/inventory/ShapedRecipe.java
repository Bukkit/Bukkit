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
    private int hash;

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
        calculateHashCode();
    }

    /**
     * Set the shape of this recipe to the specified rows. Each character represents a different
     * ingredient; exactly what each character represents is set separately. The first row supplied
     * corresponds with the upper most part of the recipe on the workbench e.g. if all three 
     * rows are supplies the first string represents the top row on the workbench.
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
        calculateHashCode();
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

        // -1 is the old wildcard, map to Short.MAX_VALUE as the new one
        if (raw == -1) {
            raw = Short.MAX_VALUE;
        }

        ingredients.put(key, new ItemStack(ingredient, 1, (short) raw));
        calculateHashCode();
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

    @Override
    public int hashCode() {
        return hash;
    }

    private void calculateHashCode() {
        ItemStack[] matrix = shapeToMatrix(this.getShape(), this.ingredients);
        StringBuilder str = new StringBuilder("shaped:");

        for (ItemStack item : matrix) {
            if (item == null) {
                str.append('0');
            } else {
                str.append(item.hashCode());
            }
            str.append(';');
        }

        str.append('=').append(output.hashCode());

        hash = str.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj instanceof ShapedRecipe) {
            ShapedRecipe r = (ShapedRecipe) obj;

            if (!this.getResult().equals(r.getResult())) {
                return false;
            }

            return this.isSimilar(r);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     * <br>
     * <br>Shape is also checked horizontally mirrored if does not match as-is.
     */
    public boolean isSimilar(Recipe recipe) {
        Validate.notNull(recipe, "Recipe can not be null.");

        if (recipe == this) {
            return true;
        }

        if (recipe instanceof ShapedRecipe) {
            ShapedRecipe r = (ShapedRecipe) recipe;

            // convert both shapes and ingredient maps to common ItemStack array.
            ItemStack[] matrix1 = shapeToMatrix(this.getShape(), this.getIngredientMap());
            ItemStack[] matrix2 = shapeToMatrix(r.getShape(), r.getIngredientMap());

            // compare arrays and if they don't match run another check with one shape mirrored.
            if (!Arrays.equals(matrix1, matrix2)) {
                mirrorMatrix(matrix1);

                return Arrays.equals(matrix1, matrix2);
            }

            return true; // ingredients match.
        }

        return false;
    }

    private static ItemStack[] shapeToMatrix(String[] shape, Map<Character, ItemStack> map) {
        ItemStack[] matrix = new ItemStack[9];
        int slot = 0;

        for (int r = 0; r < shape.length; r++) {
            for (char col : shape[r].toCharArray()) {
                matrix[slot] = map.get(col);
                slot++;
            }

            slot = ((r + 1) * 3);
        }

        return matrix;
    }

    private static void mirrorMatrix(ItemStack[] matrix) {
        ItemStack tmp;

        for (int r = 0; r < 3; r++) {
            tmp = matrix[(r * 3)];
            matrix[(r * 3)] = matrix[(r * 3) + 2];
            matrix[(r * 3) + 2] = tmp;
        }
    }
}
