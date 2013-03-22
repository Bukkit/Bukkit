package org.bukkit.entity;

import java.util.Collection;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

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
     * Returns a copy of the ItemStack for this thrown potion.
     * <p>
     * Altering this copy will not alter the thrown potion directly.
     * If you want to alter the thrown potion, you must use the
     * {@link addEffect}, {@link addEffects}, {@link setEffects} or
     * {@link removeEffect} methods.
     *
     * @return A copy of the ItemStack for this thrown potion.
     */
    public ItemStack getItem();

    /**
     * Set the ItemStack for this thrown potion.
     * <p>
     * The ItemStack must be a potion, otherwise an exception is thrown.
     *
     * @param item New ItemStack
     */
    public void setItem(ItemStack item);

    /**
     * Adds a new effect to be applied by this potion.
     *
     * @param pe new {@link PotionEffect}
     */
    public void addEffect(PotionEffect pe);

    /**
     * Adds a {@link Collection} of new {@link PotionEffect}s to be applied
     * by this potion.
     *
     * @param pe {@link Collection} of new {@link PotionEffect}s
     */
    public void addEffects(Collection<PotionEffect> pe);

    /**
     * Replaces all effects to be applied by this potion.
     *
     * @param pe {@link Collection} of {@link PotionEffect}s
     */
    public void setEffects(Collection<PotionEffect> cpe);

    /**
     * Removes a single {@link PotionEffect}s from this potion.
     *
     * @param pe {@link PotionEffect}
     */
    public void removeEffect(PotionEffect pe);

    /**
     * Sets this potion {@link PotionType}.
     *
     * @param pt the {@link Potion}s to copy from its {@link PotionEffect}s
     * to this {@link ThrownPotion}
     */
    public void setPotionType(PotionType pt);
}
