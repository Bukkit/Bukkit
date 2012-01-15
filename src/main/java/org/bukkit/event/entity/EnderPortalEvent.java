package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

public class EnderPortalEvent extends EntityEvent implements Cancellable {

    private boolean cancel;
    private Location location;

    public EnderPortalEvent(Entity what, Location location) {
        super(Type.ENDER_PORTAL, what);
        this.location = location;
        this.cancel = false;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Get the location that the Ender Portal is going to start at.
     *
     * @return location the ender portal is going to start at
     */
    public Location getLocation() {
        return location;
    }
}
