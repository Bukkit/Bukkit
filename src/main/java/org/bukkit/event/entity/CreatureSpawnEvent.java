package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

/**
 * @author jlogsdon
 */
public class CreatureSpawnEvent extends EntityEvent implements Cancellable {
    private boolean cancel;

    public CreatureSpawnEvent(Entity entity) {
        super(Type.CREATURE_SPAWN, entity);
        this.cancel = false;
    }

    public boolean isCancelled() {
        return cancel;
    }

    /**
     * Cancel the change in target. The entity will remain with their original
     * target, whether that is nothing or another entity.
     * @param cancel
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}