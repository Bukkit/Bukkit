package org.bukkit.entity;

import org.bukkit.ai.Pathfinder;

/**
 * Represents an intelligent mob(every living entity with pathfinder)
 * 
 *
 */
public interface Intelligent {
    /**
     * 
     * @return If this entity avoids water while walking
     */
    public boolean doesAvoidWater();
    /** 
     * Tells entity to avoid or not avoid water
     * 
     * @param w
     */
    public void setAvoidWater(boolean w);
    /**
     * 
     * @return If this entity can break doors (zombies in villages)
     */
    public boolean canBreakDoors();
    /** 
     * Sets if this entity can break doors
     * 
     * @param breakDoors
     */
    public void setBreakDoors(boolean breakDoors);
    /**
     * Sets if this entity can pass open doors
     * @param pass
     */
    public void setEnterDoors(boolean pass);
    /**
     * Sets if this entity should avoid sun
     * 
     * @param noSun
     */
    public void setAvoidSun(boolean noSun);
    /**
     * Sets speed of this entity while moving. 1.0 is really fast
     * @param speed
     */
    public void setSpeed(float speed);
    /**
     * Sets if this entity has an ability to swim
     * @param swim
     */
    public void setCanSwim(boolean swim);
    /**
     * Attaches a new {@link Pathfinder} to this entity.
     * @param pf Pathfinder to attach.
     */
    public void attachPathfinder(Pathfinder pf);
    /**
     * Deletes all the pathfinders(even the vanilla ones)
     */
    public void clearPathfinders();
    /** 
     * Makes controlled entity look at another entity 
     * 
     * @param entity
     * @param speedPitch
     * @param speedYaw
     */
    public void lookAt(Entity entity,float speedPitch,float speedYaw);
    /** 
     * Makes controlled entity look at specified place
     * 
     * @param x
     * @param y
     * @param z
     * @param speedPitch
     * @param speedYaw
     */
    public void lookAt(double x,double y,double z,float speedPitch,float speedYaw);
    /** 
     * Moves entity to specified place with specified speed
     * 
     * @param x
     * @param y
     * @param z
     * @param speed
     */
    public void moveTo(double x,double y,double z,float speed);
    /** 
     * Makes entity jump
     * 
     */
    public void jump();
}
