package org.bukkit.entity;

import org.bukkit.inventory.CreatureInventory;

/**
 * Represents a Creature. Creatures are non-intelligent monsters or animals which
 * have very simple abilities.
 */
public interface Creature extends LivingEntity {

    /**
     * Instructs this Creature to set the specified LivingEntity as its target.
     * Hostile creatures may attack their target, and friendly creatures may
     * follow their target.
     *
     * @param target New LivingEntity to target, or null to clear the target
     */
    public void setTarget(LivingEntity target);

    /**
     * Gets the current target of this Creature
     *
     * @return Current target of this creature, or null if none exists
     */
    public LivingEntity getTarget();
    
    /**
     *  Gets the inventory with the equipment worn by this entity.
     *  
     *  @return the entities inventory.
     */
    public CreatureInventory getInventory();
    
    /**
     * Sets whether or not the creature can pick up items.
     * 
     * @param pickup Whether or not creature can pick up items.
     */
    public void setCanPickupItems(boolean pickup);
    
    /**
     * Gets if the creature can pick up items.
     * 
     * @return whether or not the creature can pick up items.
     */
    public boolean canPickupItems();
}
