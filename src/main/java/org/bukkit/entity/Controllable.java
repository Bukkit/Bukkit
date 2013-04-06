package org.bukkit.entity;

import org.bukkit.Location;
import org.bukkit.ai.Pathfinder;

/**
 * Represents a mob which can be controlled WITHOUT {@link Pathfinder}.
 *
 */
public interface Controllable {
    /**
     * 
     * @return If this entity avoids water while walking
     */
    public boolean doesAvoidWater();
    /** 
     * Tells entity to avoid or not avoid water
     * 
     * @param w - If this entity should avoid water
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
     * @param breakDoors - if this entity can break doors
     */
    public void setBreakDoors(boolean breakDoors);
    /**
     * Sets if this entity can pass through open doors
     * @param pass - if the entity can pass through open doors
     */
    public void setEnterDoors(boolean pass);
    /**
     * Sets if this entity should avoid sun
     * 
     * @param noSun - if this entity should avoid sun
     */
    public void setAvoidSun(boolean noSun);
    /**
     * Sets speed of this entity while moving. Speed scale is the same as in {@link Player}.setFlySpeed or {@link Player}.setWalkSpeed 
     * @param speed - Speed of movement. 1.0 is too fast, 0.3 is fine for most cases
     */
    public void setSpeed(float speed);
    /**
     * Sets if this entity has an ability to swim
     * @param swim - if this entity has an ability to swim
     */
    public void setCanSwim(boolean swim);

    /** 
     * Makes controlled entity look at another entity 
     * 
     * @param entity - Entity to look at
     * @param speedPitch - Speed of rotation around the X-axis
     * @param speedYaw - Speed of rotation around the Y-axis
     */
    public void lookAt(Entity entity, float speedPitch, float speedYaw);
    /**
     * Makes controlled entity look at specified location
     * @param location - Location where to look at
     * @param speedPitch - Speed of rotation around the X-axis
     * @param speedYaw - Speed of rotation around the Y-axis
     */
    public void lookAt(Location location, float speedPitch, float speedYaw);
    /** 
     * Makes controlled entity look at specified place
     * 
     * @param x - X position to look at
     * @param y - Y position to look at
     * @param z - Z position to look at
     * @param speedPitch - Speed of rotation around the X-axis
     * @param speedYaw - Speed of rotation around the Y-axis
     */
    public void lookAt(double x, double y, double z, float speedPitch, float speedYaw);
    /** 
     * Moves entity to specified place with specified speed
     * 
     * @param x - X of position where to move
     * @param y - Y of position where to move
     * @param z - Z of position where to move
     * @param speed - Speed of entity movement
     */
    public void moveTo(double x, double y, double z, float speed);
    /**
     * Moves entity to specified location with specified speed
     * @param loc - Destination of this movement
     * @param speed - Speed of entity movement
     */
    public void moveTo(Location loc, float speed);
    /** 
     * Makes entity jump 1 block high with X,Z velocity applied to the jump
     * 
     */
    public void jump();
}
