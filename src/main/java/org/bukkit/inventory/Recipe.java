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
     * Checks if the supplied object is a recipe that has identical ingredient layout and identical results.
     * <br>This is like {@link #isSimilar(Recipe)} except it checks the result.
     *
     * @return True if object is the same recipe as this recipe.
     */
    boolean equals(Object obj);

    /**
     * Checks if recipes are of the same type and have matching ingredients and shape.
     * <br>This is like {@link #equals(Object)} except it doesn't check result.
     *
     * @param recipe the recipe to compare against, must not be null.
     * @return True if both recipes have the same unique ingredient mix, false otherwise.
     */
    boolean isSimilar(Recipe recipe);
}
