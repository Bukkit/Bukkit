package org.bukkit.event.entity;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a human entity's food level changes
 */
public class FoodLevelChangeEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private Material food;
    private int level;
    private float saturation;
    private float exhaustion;

    @Deprecated
    public FoodLevelChangeEvent(final HumanEntity what, final int level) {
        this(what, null, level, level, 0.0F);
    }

    public FoodLevelChangeEvent(final HumanEntity what, final Material food, final int level, final float saturation, final float exhaustion) {
        super(what);
        this.food = food;
        this.level = level;
        this.saturation = saturation;
        this.exhaustion = exhaustion;
    }

    @Override
    public HumanEntity getEntity() {
        return (HumanEntity) entity;
    }

    /**
     * Get the food being eaten in this event.
     *
     * @return the food eaten by the entity
     */
    public Material getFood() {
        return food;
    }

    /**
     * Gets the resultant food level that the entity involved in this event
     * should be set to.
     * <p>
     * Where 20 is a full food bar and 0 is an empty one.
     *
     * @return The resultant food level
     */
    public int getFoodLevel() {
        return level;
    }

    /**
     * Sets the resultant food level that the entity involved in this event
     * should be set to
     *
     * @param level the resultant food level that the entity involved in this
     *     event should be set to
     */
    public void setFoodLevel(int level) {
        if (level > 20) {
            level = 20;
        } else if (level < 0) {
            level = 0;
        }

        this.level = level;
    }

    /**
     * Gets the resultant saturation that the entity involved in this event
     * should be set to. An entity can never have a higher saturation than
     * their current food level.
     *
     * @return The resultant saturation
     */
    public float getSaturation() {
        return saturation;
    }

    /**
     * Sets the resultant saturation that the entity involved in this event
     * should be set to.
     *
     * @param saturation the resultant saturation that the entity involved in this
     *     event should be set to
     */
    public void setSaturation(float saturation) {
        if (saturation > level) {
            saturation = level;
        } else if (saturation < 0) {
            saturation = 0;
        }

        this.saturation = saturation;
    }

    /**
     * Gets the resultant food level that the entity involved in this event
     * should be set to. Exhaustion cannot exceed 40.0F
     *
     * @return The resultant exhaustion level
     */
    public float getExhaustion() {
        return exhaustion;
    }

    /**
     * Sets the resultant exhaustion level that the entity involved in this event
     * should be set to
     *
     * @param exhaustion the resultant exhaustion level that the entity involved in this
     *     event should be set to
     */
    public void setExhaustion(float exhaustion) {
        if (exhaustion > 40.0F) {
            exhaustion = 40.0F;
        } else if (exhaustion < 0) {
            exhaustion = 0;
        }

        this.exhaustion = exhaustion;
    }

    public boolean isCancelled() {
        return cancel;
    }

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
