package org.bukkit.block;

import org.bukkit.inventory.ItemStack;

/**
 * Represents a dispenser.
 * 
 * @author sk89q
 */
public interface Dispenser extends BlockState, ContainerBlock {
    /**
     * Attempts to dispense the contents of this block<br />
     * <br />
     * If the block is no longer a dispenser, this will return false
     * 
     * @return true if successful, otherwise false
     */
    public boolean dispense();
    
    /**
     * Dispenses the specific item normally, including shooting or throwing
     * arrows, eggs, and snowballs.<br />
     * <br />
     * If the block is no longer a dispenser, this will return false
     * 
     * @return true if successful, otherwise false
     */
    public boolean dispenseItemStack(ItemStack stack);
    
    /**
     * Dispenses the specific item. If normal is true then arrows,
     * eggs, and snowballs will be shot or thrown like the dispenser
     * normally does, otherwise they will just be dispensed as a
     * regular item.<br />
     * <br />
     * If the block is no longer a dispenser, this will return false
     * 
     * @return true if successful, otherwise false
     */
    public boolean dispenseItemStack(ItemStack stack, boolean normal);
}
