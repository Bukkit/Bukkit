package org.bukkit.event.world;

import org.bukkit.World;

/**
 * Called when all players in the world are sleeping
 */
public class TimeChangeEvent extends WorldEvent {
    public TimeChangeEvent(World world) {
        super(Type.TIME_CHANGE, world);
    }
}