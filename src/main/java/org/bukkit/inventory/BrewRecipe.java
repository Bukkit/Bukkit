package org.bukkit.inventory;

import org.bukkit.Material;
import org.bukkit.potion.PotionBitSet;

/**
 * Represents a brewing recipe.
 */
public class BrewRecipe implements Recipe {

    private String bitString;
    private Material ingredient;
    private PotionBitSet bitSet;

    /**
     * Create a brewing recipe to brew the desired bit modifications into a
     * potion. The bit string modifies the different bit values in the potion's
     * damage, which is stored as a short. The bit modifier string syntax is as
     * follows: [+/-/&/!][number]. + sets bit, - retracts. & checks if bit is
     * set, ! checks if it isn't.
     * 
     * If a check fails, the potion will refuse to brew. Most recipes have an &4
     * to check if the potion is an awkward potion. For more info on different
     * potion bits, see http://tinyurl.com/potionbits
     * 
     * @param bitModifier
     *            The bits to modify. Example: +0-1-2-3&4-4+13 creates a potion
     *            of regeneration.
     * @param input
     *            The ingredient to add.
     */
    public BrewRecipe(String bitModifier, Material input) {
        bitString = bitModifier;
        bitSet = new PotionBitSet(bitModifier);
        ingredient = input;
    }

    /**
     * Create a brewing recipe to brew the desired bit modifications into a
     * potion.
     * 
     * @param bitSet
     *            A PotionBitSet which sets what the potion will brew into.
     * @param input
     *            The ingredient to add.
     */
    public BrewRecipe(PotionBitSet bitSet, Material input) {
        bitString = bitSet.getBitString();
        this.bitSet = bitSet;
        ingredient = input;
    }

    /**
     * Returns the resulting potion with the added ingredient.
     * 
     * @return Returns the most basic level potion that can be created with this
     *         recipe as an ItemStack.
     */
    @Override
    public ItemStack getResult() {
        return new ItemStack(Material.POTION, 1, PotionBitSet.getFinalBits(16, bitString));
    }

    /**
     * Returns the ingredient to be added to the potion.
     * 
     * @return Returns the added ingredient as a Material
     */
    public Material getInput() {
        return ingredient;
    }

    /**
     * Returns the bit modifier string passed to NMS
     * 
     * @return Returns the bit modifier string passed to NMS as a String
     */
    public String getBitModifier() {
        return bitString;
    }

    /**
     * Returns the bit modifier as a PotionBitSet
     * 
     * @return Returns the bit modifier as a PotionBitSet
     */
    public PotionBitSet getPotionBitSet() {
        return bitSet;
    }

}