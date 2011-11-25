package org.bukkit.block;

/**
 * Represents a brewing stand.
 */
public interface BrewingStand extends BlockState, ContainerBlock {

    /**
     * Get brew time.
     *
     * @return Brew time
     */
    public short getBrewTime();

    /**
     * Set brew time.
     *
     * @param brewTime Brew time
     */
    public void setBrewTime(short brewTime);
}
