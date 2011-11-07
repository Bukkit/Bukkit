package org.bukkit;
/**
 * Allows plugins to change what every client sees without changing the data on the disk
 * Iterates through the data being sent to the client in the main chunk packet and replaces the id
 * of every listed block with an entry in the filter with the replacement id.
 * 
 * Fantastic for anti-xray or unique effects.
 * This is per-chunk and is designed to be applied onChunkLoad();
 */
public interface ChunkFilter {
	/**
	 * Creates a filter entry for the specific chunk
	 * Any occurances of "from" will be replaced with "to".
	 * So if you had [5, 1, 1, 3] setFilter((byte) 5, (byte) 1) would become [1, 1, 1, 3]
	 * 
	 * @param from
	 * @param to
	 */
	public void setFilter(byte from, byte to);
	/**
	 * Creates a filter entry for the specific chunk
	 * 
	 * @param from
	 * @param to
	 */
	public void setFilter(int from, int to);
	/**
	 * Creates a filter entry for the specific chunk
	 * 
	 * @param from
	 * @param to
	 */
	public void setFilter(Material from, Material to);
	/**
	 * Clears the filter entries (can be useful for onChunkUnload() to ensure memory is freed up quickly)
	 */
	public void clearFilters();

}
