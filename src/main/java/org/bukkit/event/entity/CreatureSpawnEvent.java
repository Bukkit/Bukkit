package org.bukkit.event.entity;

import org.bukkit.Entity;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * Stores data for damage events
 */
public class CreatureSpawnEvent extends EntityEvent implements Cancellable {

    private Location location;
    private boolean canceled;
    private CreatureType creaturetype;

    public CreatureSpawnEvent(Entity spawnee, CreatureType creaturetype, Location loc)
    {
        super(Event.Type.CREATURE_SPAWN, spawnee);
        this.creaturetype = creaturetype;
        this.location = loc;
    }

    protected CreatureSpawnEvent(Event.Type type, Entity spawnee, CreatureType creaturetype, Location loc)
    {
        super(type, spawnee);
        this.creaturetype = creaturetype;
        this.location = loc;
    }

    /**
     * Gets the cancellation state of this event. A canceled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @return true if this event is canceled
     */
    public boolean isCancelled() {
        return canceled;
    }

    /**
     * Sets the cancellation state of this event. A canceled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        canceled = cancel;
    }

    /**
     * Gets the location at which the creature is spawning.
     * @return The location at which the creature is spawning
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * Gets the type of creature being spawned.
     * 
     * @return A CreatureType value detailing the type of creature being spawned
     */
    public CreatureType getCreatureType()
    {
        return creaturetype;
    }

    /**
     * An enum to specify the type of creature being spawned.
     */
    public enum CreatureType
    {
        /**
         * A harmless creature such as a sheep or cow.
         */
        ANIMAL,
        /**
         * A hostile creature such as a zombie or spider.
         */
        MONSTER,
    }
}