package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.potion.PotionEffect;

/**
 * Fired when a entity's potion effects change. <br>
 * Causes for firing:<br>
 * <ul>
 * <li>Beacon potion effect gained/lost</li>
 * <li>Splash/edible potion effect gained/lost</li>
 * <li>Plugin altered potion effect gained/lost</li>
 * <li>Mob (Cave Spider, Zombie, Wither, etc) attacks entity</li>
 * </ul>
 */
public class EntityPotionEffectChangeEvent extends EntityEvent implements Cancellable {
    public static enum PotionChangeCause{
        /**
         * Wither-like entity caused event
         */
        WITHER, 
        /**
         * Caused by a zombie
         */
        ZOMBIE, 
        /**
         * Caused by a cave spider
         */
        CAVE_SPIDER, 
        /**
         * Caused by a beacon block
         */
        BEACON, 
        /**
         * Caused by a potion, splashed or consumed
         */
        POTION, 
        /**
         * Plugin altered effect
         */
        PLUGIN, 
        /**
         * Effect loss
         */
        LOSS;
    }

    private static final HandlerList handlers = new HandlerList();
    private boolean lostEffect = false;
    private boolean cancel = false;
    private boolean ambient = false;
    private PotionEffect effect;
    private PotionChangeCause cause;
    private Location location;

    /**
     * Construct a new EntityPotionEffectChangeEvent
     * 
     * @param who the entity being affected
     * @param effect the effect
     * @param lostEffect true to identify the effect is being lost
     * @param cause the cause to be applied
     * @param ambient true if ambient
     * @param location the location of the event, if applicable
     */
    public EntityPotionEffectChangeEvent(LivingEntity who, PotionEffect effect, boolean lostEffect, PotionChangeCause cause, boolean ambient, Location location){
        super(who);
        this.lostEffect = lostEffect;
        this.effect = effect;
        this.cause = cause;
        this.ambient = ambient;
        this.location = location.clone();
    }

    /**
     * Gets the source of the effect.
     * @return location of source
     */
    public Location getLocation(){
        return location;
    }

    /**
     * Gets the ambient flag for beacons. This will be false otherwise 
     * (unless set by a plugin)
     * @return true if ambient
     */
    public boolean isAmbient(){
        return ambient;
    }

    /**
     * Sets the ambient flag
     * @param ambient true if ambient
     */
    public void setAmbient(boolean ambient){
        this.ambient = ambient;
    }

    /**
     * Gets the cause associated with this event
     * 
     * @return the cause of the event
     */
    public PotionChangeCause getCause(){
        return cause;
    }

    /**
     * Gets the potion effect applied to this event. Modifying this modifies 
     * the overall effect.<br>
     * If the entity is going to lose the effect, modifying this has no 
     * effect.
     * 
     * @return the potion effect
     */
    public PotionEffect getEffect(){
        return effect;
    }

    /**
     * Used to determine if the entity is going to lose the effect
     * 
     * @return true if the entity will lose the effect, false otherwise
     */
    public boolean isLosingEffect(){
        return lostEffect;
    }

    public boolean isCancelled(){
        return cancel;
    }

    /**
     * Set the event as cancelled.<br>
     * The event will only cancel if the entity is <b>not</b> losing the 
     * effect.
     * 
     * @param cancel true to cancel, if valid
     */
    public void setCancelled(boolean cancel){
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers(){
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }
}
