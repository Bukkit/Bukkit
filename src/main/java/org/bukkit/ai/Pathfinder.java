package org.bukkit.ai;


import org.bukkit.entity.Controllable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PathfindingEntity;

/** 
 * Represents a Pathfinder for {@link Controllable} entities
 *
 */
public interface Pathfinder {
    /**
     * 
     * @return Entity controlled by the pathfinder
     */
    public PathfindingEntity getEntity();
    /**
     * Sets entity to be controlled by the pathfinder
     * @param entity
     */
    public void setEntity(PathfindingEntity entity);
    /**
     * Called every tick this Pathfinder is in the queue and is not running
     * 
     * @return If pathfinder can start guiding the entity (if it can see the entity, if it's in love etc.)
     */
    public boolean canStart();
    /**
     * Called everytime after update() method gets called - Every tick this Pathfidner
     * @return If pathfinder should continue execution(if player is in required range etc.)
     */
    public boolean canContinue();
    /** 
     * Called when pathfinder execution stops - After update() gets executed and canContinue() returns false
     * 
     */
    public void onStop();
    /** 
     * Gets type of this Pathfinder. Can't be null.<BR>
     * GOAL pathfinders learn mobs useful abilities like swimming, looking at player or breeding<BR>
     * TARGET pathfinders help monsters to look for targets. <BR>
     * Briefly, you probably want GOAL pathfinder if you're doing something peaceful
     * @return type of this pathfinder
     */
    public PathfinderType getType();
    /**
     * Gets priority of this pathfinder. 0 = highest
     * Higher priority Pathfinders can be activated before lower priority pathfinders
     * @return Priority in range 0 - Integer.MAX_VALUE
     */
    public int getPriority();
    /**
     * Performs a logical update of this pathfinder. Called every tick when this pathfinder active.
     * 
     */
    public void update();
}
