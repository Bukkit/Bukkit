package org.bukkit.block;

import java.util.Collection;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;

/**
 * Represents a Beacon
 */
public interface Beacon extends BlockState{
    /**
     * Gets the primary level for the beacon.
     *
     * @return The primary level of the beacon.
     */
    public int getPrimary();

    /**
     * Sets the primary level for the beacon.
     *
     * @param level - The primary level of the beacon.
     */
	public void setPrimary(int level);
	
    /**
     * Get the secondary level for the beacon.
     *
     * @return The secondary level of the beacon.
     */
    public int getSecondary();
    
    /**
     * Sets the secondary level for the beacon.
     *
     * @param level - The secondary level of the beacon.
     */
    public void setSecondary(int level);
	
    /**
     * Get the combined level for the beacon.
     *
     * @return The combined level of the beacon.
     */
    public int getLevel();
    
    /**
     * Sets the combined level of the beacon.
     * 
     * @param level - Sets the combined level.
     */
    public void setLevel(int level);

    /**
     * Get the materials accepted by the beacon.
     *
     * @return A list of materials accepted to activate the beacon. 
     */
    public List<Material> getAcceptedMaterials();

    /**
     * Set the materials accepted by the beacon.
     *
     * @param materials - A list of materials to accepted by a beacon. 
     */    
    public void setAcceptedMaterials(List<Material> materials);
    
    /**
     * Gets a collection of potion effect in use by the beacon.
     * 
     * @return A collection of potion effects currently in use by the beacon.
     */
    public Collection<PotionEffect> getActivePotionEffects();
}
