package org.bukkit.event.block;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;

public class BeaconPotionEffectEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled;
    private Set<HumanEntity> humans;
    private Set<PotionEffect> potions;

    public BeaconPotionEffectEvent(Set<HumanEntity> humans, Set<PotionEffect> potioneffects) {
        this.humans = (humans != null ? humans : new HashSet<HumanEntity>());
        this.potions = (potioneffects != null ? potioneffects : new HashSet<PotionEffect>());
        this.isCancelled = false;
    }

    /**
     * Cancel or uncancel the event.
     * 
     * @param isCancelled New cancelled state.
     */
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    /**
     * Set affected players.
     * 
     * @param humans Affected players.
     */
    public void setEntities(Set<HumanEntity> humans) {
        this.humans = (humans != null ? humans : new HashSet<HumanEntity>());
    }

    /**
     * Set applied potions.
     * 
     * @param potions Applied potions.
     */
    public void setEffects(Set<PotionEffect> potions) {
        this.potions = (potions != null ? potions : new HashSet<PotionEffect>());
    }

    /**
     * Returns affected players.
     * 
     * @return List of affected players.
     */
    public Set<HumanEntity> getEntities() {
        return this.humans;
    }

    /**
     * Returns applied potions.
     * 
     * @return Applied potions.
     */
    public Set<PotionEffect> getEffects() {
        return this.potions;
    }

    /**
     * Is the event cancelled?
     * 
     * @return Is it?
     */
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return BeaconPotionEffectEvent.handlers;
    }
}
