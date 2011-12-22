package org.bukkit.inventory.container;

import org.bukkit.block.ContainerBlock;

/**
 * 
 * @author Meaglin
 * 
 * Indicates an active BlockContainer.
 *
 */
public interface BlockContainer extends Container {

    /**
     * This function returns the block this container represents.
     * This can either be a Furnace, Chest, Dispenser, Workbench
     * 
     * @return The Block this container represents
     * 
     * @see ContainerBlock
     */
    public ContainerBlock getBlock();
}
