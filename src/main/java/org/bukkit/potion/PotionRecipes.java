package org.bukkit.potion;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.BrewRecipe;
import org.bukkit.potion.PotionEffect;

public class PotionRecipes {
    private PotionRecipes() {
    }

    private static HashMap<Integer, String> recipes = new HashMap<Integer, String>();
    private static ArrayList<Integer> newIngredients = new ArrayList<Integer>();
    private static HashMap<Integer, ArrayList<PotionEffect>> customEffects = new HashMap<Integer, ArrayList<PotionEffect>>();

    /**
     * Registers a new ingredient to the specified bit modifier
     * 
     * @param bitModifier
     *            The bit modifier string to register with the ingredient
     * @param material
     *            The Material data of the ingredient
     */
    public static void addRecipe(String bitModifier, Material material) {
        recipes.put(material.getId(), bitModifier);
        if (!newIngredients.contains(material.getId()))
            newIngredients.add(material.getId());
    }

    /**
     * Registers a new ingredient to the specified bit modifier
     * 
     * @param bitModifier
     *            The PotionIngredientMeta to register with the ingredient
     * @param material
     *            The Material data of the ingredient
     */
    public static void addRecipe(PotionIngredientMeta bitModifier, Material material) {
        recipes.put(material.getId(), bitModifier.getBitString());
        if (!newIngredients.contains(material.getId()))
            newIngredients.add(material.getId());
    }

    /**
     * Overrides an existing potion's PotionEffect with a different PotionEffect
     * 
     * @param recipe
     *            The potion recipe to register the effect to
     * @param neweffects
     *            An ArrayList of effects to overwrite onto the specified potion
     */
    public static void overrideEffect(BrewRecipe recipe, PotionEffect neweffect) {
        overrideEffect(recipe.getResult().getDurability(), neweffect);
    }

    /**
     * Overrides an existing potion's PotionEffect with a different PotionEffect
     * 
     * @param potiondata
     *            The integer value of the potion to override
     * @param neweffects
     *            A PotionEffect to overwrite onto the specified potion
     */
    public static void overrideEffect(int potiondata, PotionEffect neweffect) {
        ArrayList<PotionEffect> effect = new ArrayList<PotionEffect>();
        effect.add(neweffect);

        int data = potiondata;
        if (data > 15)
            data = (data << 12) >> 12;

        overrideEffect(data, effect);
    }

    /**
     * Overrides an existing potion's PotionEffect with a different PotionEffect
     * 
     * @param potiondata
     *            The integer value of the potion to override
     * @param neweffects
     *            An ArrayList of effects to overwrite onto the specified potion
     */
    public static void overrideEffect(int potiondata, ArrayList<PotionEffect> neweffects) {
        customEffects.put(potiondata, neweffects);
    }

    /**
     * Returns the bit string for the specified potion ingredient
     * 
     * @param material
     *            The potion ingredient to retrieve the bit string with
     * @return The potion's bit string
     */
    public static String getMetaforMaterial(Material material) {
        return recipes.get(material);
    }

    /**
     * Returns the PotionIngredientMeta for the specified potion ingredient
     * 
     * @param material
     *            The potion ingredient to retrieve the bit string with
     * @return The potion's PotionIngredientMeta
     */
    public static PotionIngredientMeta getPotionIngredientMetaforMaterial(Material material) {
        return new PotionIngredientMeta(recipes.get(material));
    }

    /**
     * Returns the bit string for the specified potion ingredient
     * 
     * @param material
     *            The potion ingredient's integer ID to retrieve the bit string
     *            with
     * @return The potion's bit string
     */
    public static String getMetaforId(int material) {
        return recipes.get(material);
    }

    /**
     * Returns the PotionIngredientMeta for the specified potion ingredient
     * 
     * @param material
     *            The potion ingredient's integer ID to retrieve the bit string
     *            with
     * @return The potion's PotionIngredientMeta
     */
    public static PotionIngredientMeta getPotionIngredientMetaforId(int material) {
        return new PotionIngredientMeta(recipes.get(material));
    }

    /**
     * Returns an ArrayList of custom ingredients registered
     * 
     * @return An ArrayList of ingredient ID's
     */
    public static ArrayList<Integer> getNewIngredients() {
        return newIngredients;
    }

    /**
     * Returns an HashMap of custom effects linked with their associated
     * potion's ID
     * 
     * @return
     */
    public static HashMap<Integer, ArrayList<PotionEffect>> getCustomEffectsTable() {
        return customEffects;
    }

    /**
     * Returns a HashMap of bit strings linked with their associated potion's ID
     * 
     * @return
     */
    public static HashMap<Integer, String> getRecipes() {
        return recipes;
    }
}