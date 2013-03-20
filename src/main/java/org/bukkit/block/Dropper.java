package org.bukkit.block;
//CraftBukkit start
/**
 * Represents a dropper.
 */
public interface Dropper extends Dispenser {

    /**
     * Try to drop one of item from one itemstack the dropper contain
     *
     * This dispense method return false if the block is no longer a dropper
     *
     * @return true if successful, otherwise false
     */
    public boolean dispense();
}
//CraftBukkit end