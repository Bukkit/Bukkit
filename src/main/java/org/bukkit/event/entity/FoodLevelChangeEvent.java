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
    private int level;
	private Material consuming;

    public FoodLevelChangeEvent(final HumanEntity what, final int level, Material consuming) {
        super(what);
        this.level = level;
        this.consuming = consuming;
    }

    @Override
    public HumanEntity getEntity() {
        return (HumanEntity) entity;
    }

    /**
     * Gets the resultant food level that the entity involved in this event should be set to.
     * <p />
     * Where 20 is a full food bar and 0 is an empty one.
     *
     * @return The resultant food level
     */
    public int getFoodLevel() {
        return level;
    }

    /**
     * Sets the resultant food level that the entity involved in this event should be set to
     *
     * @param level the resultant food level that the entity involved in this event should be set to
     */
    public void setFoodLevel(int level) {
        if (level > 20) level = 20;
        else if (level < 0) level = 0;

        this.level = level;
    }
    
    /**
     * Gets whether the event is related to a food consumption.
     *
     * @return true if the entity is consuming a Material
     */
    public boolean isConsuming() {
    	return consuming != null;
    }
    
    /**
     * Gets the Material that the entity did consume.
     *
     * @return The material that the entity did consume. null if the entity did not consume anything
     */
    public Material getConsumedMaterial() {
    	return consuming;
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
