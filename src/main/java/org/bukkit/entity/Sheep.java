/**
 * 
 */
package org.bukkit.entity;

import org.bukkit.DyeColor;

/**
 * Represents a Sheep.
 * 
 * @author Cogito
 *
 */
public interface Sheep extends Animals {

	/**
	 * Gets the color of this sheep.
	 * 
	 * @return The DyeColor of this sheep.
	 */
	public DyeColor getColor();
	
	/**
	 * Sets the color of this sheep to the specified data value.
	 * 
	 * @param color The color of the sheep, as a int.
	 */
	public void setColor(int color);
	
	/**
	 * Sets the color of this sheep to the specified DyeColor.
	 * 
	 * @param color The color of the sheep, as a DyeColor.
	 */
	public void setColor(DyeColor color);
}
