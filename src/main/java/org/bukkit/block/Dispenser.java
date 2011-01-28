package org.bukkit.block;

/**
 * Represents a dispenser.
 * 
 * @author sk89q
 */
public interface Dispenser extends BlockState, ContainerBlock {
    /**
     * Dispense a random block
     */
    public void dispense();
}
