package org.bukkit.entity;

import org.bukkit.ai.Pathfinder;
/**
 * Represents an entity which can use pathfinders
 *
 */
public interface PathfindingEntity extends Controllable, LivingEntity {
    /**
     * Attaches a new {@link Pathfinder} to this entity.
     * @param pf - Pathfinder to attach.
     */
    public void attachPathfinder(Pathfinder pf);
    /**
     * Deletes all the pathfinders(even the vanilla ones)
     */
    public void clearPathfinders();
}
