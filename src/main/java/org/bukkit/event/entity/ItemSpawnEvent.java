package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.location.DirectionalLocation;

/**
 * Called when an item is spawned into a world
 */
public class ItemSpawnEvent extends EntityEvent implements Cancellable {

    private DirectionalLocation location;
    private boolean canceled;

    public ItemSpawnEvent(Entity spawnee, DirectionalLocation loc) {
        super(Type.ITEM_SPAWN, spawnee);
        this.location = loc;
    }

    public boolean isCancelled() {
        return canceled;
    }

    public void setCancelled(boolean cancel) {
        canceled = cancel;
    }

    /**
     * Gets the location at which the item is spawning.
     *
     * @return The location at which the item is spawning
     */
    public DirectionalLocation getLocation() {
        return location;
    }
}
