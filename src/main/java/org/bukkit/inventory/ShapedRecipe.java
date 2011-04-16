package org.bukkit.inventory;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

public class ShapedRecipe implements Recipe {
    private ItemStack output;
    private String[] rows;
    private HashMap<Character,MaterialData> ingred = new HashMap<Character,MaterialData>();

    /**
     * Create a shaped recipe to craft the specified ItemStack. The constructor merely determines the
     * result and type; to set the actual recipe, you'll need to call the appropriate methods.
     * @param result The item you want the recipe to create.
     * @param type The type of recipe you want this to be.
     * @see ShapedRecipe#shape(String)
     * @see ShapedRecipe#shape(String, String)
     * @see ShapedRecipe#shape(String, String, String)
     */
    public ShapedRecipe(ItemStack result) {
        this.output = result;
    }
    
    /**
     * Make this recipe shaped, with the specified row. Each character represents a different
     * ingredient; exactly what each character represents is set separately.
     * @param row The single row of the recipe.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapedRecipe shape(String row) {
        if(row == null || row.length() > 3 || row.length() < 1)
            throw new IllegalArgumentException("Crafting rows should be 1, 2, or 3 characters.");
        this.rows = new String[] {row};
        return this;
    }
    
    /**
     * Make this recipe shaped, with the specified rows. Each character represents a different
     * ingredient; exactly what each character represents is set separately.
     * @param first The first row of the recipe.
     * @param second The second row of the recipe.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapedRecipe shape(String first, String second) {
        if(first == null || first.length() > 3 || first.length() < 1)
            throw new IllegalArgumentException("Crafting rows should be 1, 2, or 3 characters.");
        if(second == null || second.length() > 3 || second.length() < 1)
            throw new IllegalArgumentException("Crafting rows should be 1, 2, or 3 characters.");
        this.rows = new String[] {first, second};
        return this;
    }
    
    /**
     * Make this recipe shaped, with the specified rows. Each character represents a different
     * ingredient; exactly what each character represents is set separately.
     * @param top The top row of the recipe.
     * @param middle The middle row of the recipe.
     * @param bottom The bottom row of the recipe.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapedRecipe shape(String top, String middle, String bottom) {
        if(top == null || top.length() > 3 || top.length() < 1)
            throw new IllegalArgumentException("Crafting rows should be 1, 2, or 3 characters.");
        if(middle == null || middle.length() > 3 || middle.length() < 1)
            throw new IllegalArgumentException("Crafting rows should be 1, 2, or 3 characters.");
        if(bottom == null || bottom.length() > 3 || bottom.length() < 1)
            throw new IllegalArgumentException("Crafting rows should be 1, 2, or 3 characters.");
        this.rows = new String[] {top, middle, bottom};
        return this;
    }
    
    /**
     * Sets the material that a character in the recipe shape refers to.
     * @param key The character that represents the ingredient in the shape.
     * @param ingredient The ingredient.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapedRecipe setIngredient(char key, MaterialData ingredient) {
        if(!hasKey(key)) throw new IllegalArgumentException("Symbol " + key + " does not appear in the shape.");
        ingred.put(key, ingredient);
        return this;
    }
    
    /**
     * Sets the material that a character in the recipe shape refers to.
     * @param key The character that represents the ingredient in the shape.
     * @param ingredient The ingredient.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapedRecipe setIngredient(char key, Material ingredient) {
        MaterialData data = ingredient.getNewData((byte) 0);
        if(data == null) data = new MaterialData(ingredient);
        return setIngredient(key, data);
    }
    
    /**
     * Sets the material that a character in the recipe shape refers to.
     * @param key The character that represents the ingredient in the shape.
     * @param ingredient The ingredient.
     * @param raw The raw material data as an integer.
     * @return The changed recipe, so you can chain calls.
     */
    public ShapedRecipe setIngredient(char key, Material ingredient, int raw) {
        MaterialData data = ingredient.getNewData((byte) raw);
        if(data == null) data = new MaterialData(ingredient,(byte) raw);
        return setIngredient(key, data);
    }
    
    private boolean hasKey(char c) {
        String key = Character.toString(c);
        for(String row : rows) {
            if(row.contains(key)) return true;
        }
        return false;
    }
    
    /**
     * Get the ingredients map.
     */
    public HashMap<Character, MaterialData> getIngredientMap() {
        return ingred;
    }
    
    /**
     * Get the shape.
     */
    public String[] getShape() {
        return rows;
    }
    
    /**
     * Get the result.
     */
    public ItemStack getResult() {
        return output;
    }
}
