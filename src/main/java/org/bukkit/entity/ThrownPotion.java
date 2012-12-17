package org.bukkit.entity;

import java.util.Collection;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

/**
 * Represents a thrown potion bottle
 */
public interface ThrownPotion extends Projectile {
    /**
     * Returns the effects that are applied by this potion.
     * @return The potion effects
     */
    public Collection<PotionEffect> getEffects();

    /**
     * Returns a copy of the item stack for this thrown potion.
     * Altering this copy will not alter the thrown potion directly.
     * If you want to alter the thrown potion you must use the {@link #setItemStack(ItemStack) setItemStack} method.
     * 
     * @return A copy of the item stack for this thrown potion.
     */
    public ItemStack getItemStack();

    /**
     * Set the item stack for this thrown potion.
     * The ItemStack must be a potion. Otherwise an exception is thrown.
     * 
     * @param itemStack 
     */
    public void setItemStack(ItemStack itemStack);
}
