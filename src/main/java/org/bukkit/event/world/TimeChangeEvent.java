package org.bukkit.event.world;

import org.bukkit.World;

/**
 * Called when a World is loaded
 */
public class TimeChangeEvent extends WorldEvent {
    public TimeChangeEvent(World world) {
        super(Type.TIME_CHANGE, world);
    }
}