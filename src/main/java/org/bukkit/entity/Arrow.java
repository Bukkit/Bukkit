package org.bukkit.entity;

/**
 * Represents an arrow.
 */
public interface Arrow extends Projectile {
	/**
	 * represents different states to check for when a player tries to pickup an arrow
	 * ALL: Arrow can always be picked up
	 * CREATIVE: Arrow can be picked up by a player in creative mode
	 * NONE: Arrow can never be picked up
	 */
	public enum Pickup {
		ALL, CREATIVE, NONE
	}

    /**
     * Gets the knockback strength for an arrow, which is the
     * {@link org.bukkit.enchantments.Enchantment#KNOCKBACK KnockBack} level
     * of the bow that shot it.
     *
     * @return the knockback strength value
     */
    public int getKnockbackStrength();

    /**
     * Sets the knockback strength for an arrow.
     *
     * @param knockbackStrength the knockback strength value
     */
    public void setKnockbackStrength(int knockbackStrength);

    /**
     * Gets whether this arrow is critical.
     * <p>
     * Critical arrows have increased damage and cause particle effects.
     * <p>
     * Critical arrows generally occur when a player fully draws a bow before
     * firing.
     *
     * @return true if it is critical
     */
    public boolean isCritical();

    /**
     * Sets whether or not this arrow should be critical.
     *
     * @param critical whether or not it should be critical
     */
    public void setCritical(boolean critical);

    /**
     * Gets required condition for picking up this arrow
     *
     * @return condition as defined in Arrow.Pickup
     */
    public Arrow.Pickup getPickup();

    /**
     * Sets the condition required for picking up this arrow
     *
     * @param canPickup condition for allowing pickup
     */
    public void setPickup(Arrow.Pickup pickup);
}
