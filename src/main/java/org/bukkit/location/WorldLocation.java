package org.bukkit.location;

import org.bukkit.World;
import org.bukkit.block.Block;

public interface WorldLocation extends Location {

    /**
     * Gets the world that this location resides in
     *
     * @return World that contains this location
     */
    World getWorld();

    /**
     * Gets the block at the represented location
     *
     * @return Block at the represented location
     */
    Block getBlock();
}
