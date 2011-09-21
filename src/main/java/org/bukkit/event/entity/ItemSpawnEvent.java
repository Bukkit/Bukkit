package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;

/**
 * Called when an item is spawned into a world
 */
public class ItemSpawnEvent extends EntitySpawnEvent {

    public ItemSpawnEvent(Entity spawnee, Location loc) {
        super(Type.ITEM_SPAWN, spawnee, loc);
    }
}
