package org.bukkit;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;

/**
 * Represents a chunk of blocks
 */
public interface Chunk {

    /**
     * Gets the X-coordinate of this chunk
     *
     * @return X-coordinate
     */
    int getX();

    /**
     * Gets the Z-coordinate of this chunk
     *
     * @return Z-coordinate
     */
    int getZ();

    /**
     * Gets the world containing this chunk
     *
     * @return Parent World
     */
    World getWorld();

    /**
     * Gets a block from this chunk
     *
     * @param x 0-15
     * @param y 0-127
     * @param z 0-15
     * @return the Block
     */
    Block getBlock(int x, int y, int z);

    /**
     * Capture thread-safe read-only snapshot of chunk data
     * @return ChunkSnapshot
     */
    ChunkSnapshot getChunkSnapshot();
 
    /**
     * Capture thread-safe read-only snapshot of chunk data
     * @param include_maxblocky - if true, snapshot includes per-coordinate maximum Y values
     * @param include_biome - if true, snapshot includes per-coordinate biome type
     * @param include_biome_temp_rain - if true, snapshot includes per-coordinate raw biome temperature and rainfall
     * @return ChunkSnapshot
     */
    ChunkSnapshot getChunkSnapshot(boolean include_maxblocky, boolean include_biome,
            boolean include_biome_temp_rain);

    Entity[] getEntities();

    BlockState[] getTileEntities();
}
