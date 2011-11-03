package org.bukkit;
/**
 * Allows plugins to change what every client sees without changing the data on the disk
 */
public interface ChunkFilter {
	/**
	 * Creates a filter entry
	 * @param from
	 * @param to
	 */
	public void setFilter(byte from, byte to);
	/**
	 * Creates a filter entry
	 * @param from
	 * @param to
	 */
	public void setFilter(int from, int to);
	/**
	 * Creates a filter entry
	 * @param from
	 * @param to
	 */
	public void setFilter(Material from, Material to);
	/**
	 * Clears the filter entries
	 */
	public void clearFilters();

}
