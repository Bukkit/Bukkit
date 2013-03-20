package org.bukkit.block;
/**
 * Represents a dropper.
 */
public interface Dropper extends Dispenser {

    /**
     * Try to drop one of item from one itemstack the dropper contain
     * 
     * Item choose by random function : 1/ContentsSize
     * 
     * Example : 
     * 
     * 1 2 3
     * 4   6
     * 7
     * 
     * Dropper can dispense one by one these items : 1 3 2 7 1 2 2 3 4 6 6 ...
     *
     * @return true if successful dispense, false if the block is no longer a dropper
     */
    public boolean dispense();
}