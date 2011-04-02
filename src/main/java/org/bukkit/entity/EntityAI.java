
package org.bukkit.entity;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.util.Vector;

/**
 * Interface for custom entity AI
 */
public interface EntityAI {
    /**
     * Gets the target of the entity.  This is only used for attacking.
     *
     * @return Entity the target of the entity
     */
    public Entity getTarget();

    /**
     * Gets the destination of the entity.  
     *
     * @return Location the destination of the entity, or null if the destination isn't a block
     */
    public Location getBlockDestination();

    /**
     * Gets the destination of the entity.
     *
     * @return Entity the destination of the entity, or null if the destination isn't an entity
     */
    public Entity getEntityDestination();

}
