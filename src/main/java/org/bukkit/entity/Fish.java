package org.bukkit.entity;

/**
 * Represents a fishing hook.
 */
public interface Fish extends Projectile {
    /**
     * causes a fish to bite the hook. 
     */
    public void bite();
    public short getBiteChance();
    public void setBiteChance(short chance);
    public short getRainyBiteChance();
    public void setRainyBiteChance(short chance);
}
