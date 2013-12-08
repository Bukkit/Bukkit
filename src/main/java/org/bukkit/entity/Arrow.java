package org.bukkit.entity;

/**
 * Represents an arrow.
 */
public interface Arrow extends Projectile {
	
    /**
   * Set the knockbackStrenght for an arrow. Must be nonnegative.
   *
   * @param knockbackStrength the punch level effect.
   */
  public void setKnockbackStrength(int knockbackStrength);	
}
