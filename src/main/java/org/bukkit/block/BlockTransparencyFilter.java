package org.bukkit.block;

/**
 * Instances of classes which implement this interface are used to test for block
 * transparency by methods which find targeted blocks.
 */
public interface BlockTransparencyFilter {
	/**
	 * Tests if a specific block should be considered transparent for the purposes
	 * of block targeting.
	 *
	 * @param block the block to test
	 * @return true if the block should be considered transparent; false otherwise
	 */
	public boolean isTransparent(Block block);
}
