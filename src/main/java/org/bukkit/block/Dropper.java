package org.bukkit.block;

/**
 * Represents a dropper.
 */
public interface Dropper extends Dispenser {

    /**
     * Attempts to dispense the contents of this block
     *
     * If the block is no longer a dropper, this will return false
     *
     * @return true if successful, otherwise false
     */
    public boolean dispense();
}
