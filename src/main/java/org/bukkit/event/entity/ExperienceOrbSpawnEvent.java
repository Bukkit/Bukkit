package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

/**
 * Event triggered when an experience orb spawns.
 */
public class ExperienceOrbSpawnEvent extends EntityEvent implements Cancellable {

    private final Location location;
    private boolean canceled;

    public ExperienceOrbSpawnEvent(Entity spawnee, Location loc) {
        super(Type.EXPERIENCE_ORB_SPAWN, spawnee);
        this.location = loc;
    }

    public boolean isCancelled() {
        return canceled;
    }

    public void setCancelled(boolean cancel) {
        canceled = cancel;
    }

    /**
     * Gets the location at which the orb is spawning.
     *
     * @return The location at which the orb is spawning.
     */
    public Location getLocation() {
        return location;
    }
}
