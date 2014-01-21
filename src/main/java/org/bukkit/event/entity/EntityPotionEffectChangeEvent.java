package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;

/**
 * Fired when a entity's potion effects change.
 * <p>
 * Causes for firing:
 * <ul>
 * <li>Beacon potion effect gained/lost</li>
 * <li>Splash/edible potion effect gained/lost</li>
 * <li>Plugin altered potion effect gained/lost</li>
 * <li>Mob (Cave Spider, Zombie, Wither, etc) attacks entity</li>
 * <li>The entity consumed a food item with vanilla effects (can trigger multiple events depending on item)</li>
 * <li>The entity used a milk bucket to remove an effect</li>
 * <li>The entity was cured of being a Zombie (triggers multiple events)</li>
 * <li>The entity ate an Enchanted Golden Apple (triggers multiple events)</li>
 * </ul>
 */
public class EntityPotionEffectChangeEvent extends EntityEvent implements Cancellable {
    public static enum Cause {
        /**
         * Caused by vanilla Mob spawn, Enchantment effect, Mob attack, etc.
         */
        MOB,

        /**
         * Caused by a beacon block
         */
        BEACON,

        /**
         * Caused by a potion, splashed or consumed
         */
        POTION,

        /**
         * Cause by a food item
         */
        FOOD,

        /**
         * Caused by a golden apple
         */
        GOLDEN_APPLE,

        /**
         * Caused by an enchanted golden apple
         */
        ENCHANTED_GOLDEN_APPLE,

        /**
         * A plugin added the effect
         */
        PLUGIN_ADDED,

        /**
         * A plugin removed the effect
         */
        PLUGIN_REMOVED,

        /**
         * The effect expired (timer reached 0:00)
         */
        EXPIRED,

        /**
         * The effect was treated. Current examples:
         * <p>
         * <ul>
         * <li>Player used milk bucket</li>
         * <li>Villager cure process was started</li>
         * </ul>
         */
        TREATED;
    }

    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private boolean ambient = false;
    private PotionEffect effect;
    private Cause cause;
    private Location location;

    /**
     * Construct a new EntityPotionEffectChangeEvent
     * @param who the entity being affected
     * @param effect the effect
     * @param cause the cause to be applied
     * @param ambient true if ambient
     * @param location the location of the event, if applicable
     */
    public EntityPotionEffectChangeEvent(LivingEntity who, PotionEffect effect, Cause cause, boolean ambient, Location location) {
        super(who);
        this.effect = effect;
        this.cause = cause;
        this.ambient = ambient;
        this.location = location.clone();
    }

    /**
     * Gets the source of the effect
     * @return location of source
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Gets the ambient flag for beacons. This will be false otherwise
     * (unless set by a plugin)
     * @return true if ambient
     */
    public boolean isAmbient() {
        return ambient;
    }

    /**
     * Sets the ambient flag
     * @param ambient true if ambient
     */
    public void setAmbient(boolean ambient) {
        this.ambient = ambient;
    }

    /**
     * Gets the cause associated with this event
     * @return the cause of the event
     */
    public Cause getCause() {
        return cause;
    }

    /**
     * Gets the potion effect applied to this event. Modifying this modifies
     * the overall effect.
     * <p>
     * If the entity is going to lose the effect, modifying this has no
     * effect.
     * @return the potion effect
     */
    public PotionEffect getEffect() {
        return effect;
    }

    /**
     * Used to determine if the entity is gaining the effect.
     * @return true if the entity is gaining the effect, false otherwise
     */
    public boolean isGainingEffect() {
        switch (cause) {
            case MOB:
            case BEACON:
            case POTION:
            case FOOD:
            case GOLDEN_APPLE:
            case ENCHANTED_GOLDEN_APPLE:
            case PLUGIN_ADDED:
                return true;
            default:
                return false;
        }
    }

    public boolean isCancelled() {
        return cancel;
    }

    /**
     * Sets the event as cancelled.
     * <p>
     * The event will only cancel if the entity is <b>gaining</b> the
     * effect.
     * @param cancel true to cancel, if valid
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}