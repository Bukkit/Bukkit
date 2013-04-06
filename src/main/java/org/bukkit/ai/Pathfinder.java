package org.bukkit.ai;


import org.bukkit.entity.LivingEntity;

/** 
 * Represents a Pathfinder for {@link Controllable}s
 * 
 *
 */
public interface Pathfinder {
    /**
     * 
     * @return Entity controlled by the pathfinder
     */
    public LivingEntity getEntity();
    /**
     * Sets entity to be controlled by the pathfinder
     * @param entity
     */
    public void setEntity(LivingEntity entity);
    /**
     * 
     * @return If pathfinder can start guiding the entity (if it can see the entity, if it's in love etc.)
     */
    public boolean canStart();
    /**
     * 
     * @return If pathfinder should continue execution(if player is in required range etc.)
     */
    public boolean canContinue();
    /** 
     * Called when pathfinder execution stops
     * 
     */
    public void onStop();
    /** 
     * Gets type of this Pathfinder. Can't be null.
     * 
     * @return
     */
    public PathfinderType getType();
    /**
     *Gets priority of this pathfinder. 0 = highest
     * 
     * @return Priority in range 0 - Integer.MAX_VALUE
     */
    public int getPriority();
    /**
     *Performs a logical update of this pathfinder.
     * 
     */
    public void update();
}
