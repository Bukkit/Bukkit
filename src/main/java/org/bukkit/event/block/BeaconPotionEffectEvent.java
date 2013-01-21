package org.bukkit.event.block;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;

public class BeaconPotionEffectEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled;
    private List<HumanEntity> humans;
    private List<PotionEffect> potions;

    public BeaconPotionEffectEvent(List<HumanEntity> humans, List<PotionEffect> potions) {
        this.humans = (humans != null ? humans : new ArrayList<HumanEntity>());
        this.potions = (potions != null ? potions : new ArrayList<PotionEffect>());
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
    public void setEntities(List<HumanEntity> humans) {
        this.humans = (humans != null ? humans : new ArrayList<HumanEntity>());
    }

    /**
     * Set applied potions.
     * 
     * @param potions Applied potions.
     */
    public void setEffects(List<PotionEffect> potions) {
        this.potions = (potions != null ? potions : new ArrayList<PotionEffect>());
    }

    /**
     * Returns affected players.
     * 
     * @return List of affected players.
     */
    public List<HumanEntity> getEntities() {
        return this.humans;
    }

    /**
     * Returns applied potions.
     * 
     * @return Applied potions.
     */
    public List<PotionEffect> getEffects() {
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
