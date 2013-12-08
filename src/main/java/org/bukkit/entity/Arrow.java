package org.bukkit.entity;

/**
 * Represents an arrow.
 */
public interface Arrow extends Projectile {

	/**
	 * Get the knockbackStrength for an arrow.
	 * 
	 * @return the knockbackStrength value.
	 */
	public int getKnockbackStrength();

	/**
	 * Set the knockbackStrength for an arrow. Must be nonnegative.
	 * 
	 * @param knockbackStrength the knockbackStrength value.
	 */
	public void setKnockbackStrength(int knockbackStrength);
}
