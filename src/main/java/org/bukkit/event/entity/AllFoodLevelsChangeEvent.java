package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.util.Util;

public class AllFoodLevelsChangeEvent extends FoodLevelChangeEvent {

    private float saturationLevel;
    private float exhaustionLevel;

    public AllFoodLevelsChangeEvent(Entity what, int foodLevel, float saturationLevel, float exhaustionLevel) {
        super(what, foodLevel);
        this.setSaturationLevel(saturationLevel);
        this.setExhaustionLevel(exhaustionLevel);
    }

    /**
     * <p>Gets the resultant saturation level that the entity involved in this event should be set to.</p>
     * <p>Where 20 is a fully saturated and 0 is causing the food level to drain.</p>
     *
     * @return The saturation food level
     */
    public float getSaturationLevel() {
        return saturationLevel;
    }

    /**
     * Sets the resultant food level that the entity involved in this event should be set to.
     *
     * @param level the resultant food level that the entity involved in this event should be set to.
     */
    public void setSaturationLevel(float saturationLevel) {
        this.saturationLevel = Util.between(saturationLevel, 0F, 20F);
    }

    /**
     * <p>Gets the resultant exhaustion level that the entity involved in this event should be set to.</p>
     * <p>If the exhaustion level is greater than 4 it will decrease the saturation by 1 and the exhaustion by 4 each tick.</p>
     *
     * @return The saturation food level
     */
    public float getExhaustionLevel() {
        return exhaustionLevel;
    }

    /**
     * Sets the resultant exhaustion level that the entity involved in this event should be set to.
     *
     * @param level the resultant exhaustion level that the entity involved in this event should be set to.
     */
    public void setExhaustionLevel(float exhaustionLevel) {
        this.exhaustionLevel = exhaustionLevel;
    }
}
