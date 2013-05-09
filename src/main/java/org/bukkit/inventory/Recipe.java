package org.bukkit.inventory;

/**
 * Represents some type of crafting recipe.
 */
public interface Recipe {

    /**
     * Get the result of this recipe.
     *
     * @return The result stack
     */
    ItemStack getResult();

    /**
     * Checks if supplied object is a recipe of the same type with same ingredients and result.
     * 
     * @param obj
     * @return True if recipes are equal, false otherwise.
     */
    boolean equals(Object obj);

    /**
     * CHecks if supplied recipe is similar to this one by only checking if ingredients match.
     * 
     * @param recipe the recipe to check against
     * @return True if recipes are of same type and have same ingredients, false otherwise.
     */
    boolean isSimilar(Recipe recipe);
}
