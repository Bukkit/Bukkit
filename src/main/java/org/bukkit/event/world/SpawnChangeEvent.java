package org.bukkit.event.world;

import org.bukkit.World;
import org.bukkit.location.WorldLocation;

/**
 * An event that is called when a world's spawn changes. The
 * world's previous spawn location is included.
 */
public class SpawnChangeEvent extends WorldEvent {
    private WorldLocation previousLocation;

    public SpawnChangeEvent(World world, WorldLocation previousLocation) {
        super(Type.SPAWN_CHANGE, world);
        this.previousLocation = previousLocation;
    }

    /**
     * Gets the previous spawn location
     *
     * @return Location that used to be spawn
     */
    // TODO: Return worldless location as this event has a world as location?
    public WorldLocation getLocation() {
        return this.previousLocation;
    }
}
