package org.bukkit.block;

import org.bukkit.entity.ProjectileShooter;

/**
 * Represents a dispenser.
 */
public interface Dispenser extends BlockState, ContainerBlock, ProjectileShooter {

    /**
     * Attempts to dispense the contents of this block<br />
     * <br />
     * If the block is no longer a dispenser, this will return false
     *
     * @return true if successful, otherwise false
     */
    public boolean dispense();
}
