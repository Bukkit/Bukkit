package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

/**
 * @author Xolsom
 */
public class BlockFallStopEvent extends BlockFallEvent {
    private final StopCause stopCause;

    private boolean droppable;
    private boolean transformable;

    public BlockFallStopEvent(Block block, Entity fallingEntity, boolean instantFall, StopCause stopCause, boolean transformable, boolean droppable) {
        super(block, fallingEntity, instantFall);

        this.stopCause = stopCause;
        this.droppable = droppable;
        this.transformable = transformable;
    }

    /**
     * Get the cause that stopped the entity from falling
     *
     * @return The stop cause
     */
    public StopCause getStopCause() {
        return this.stopCause;
    }

    /**
     * Returns whether the falling entity can be transformed into a block
     *
     * @return Whether the entity can be transformed into a block
     */
    public boolean isTransformable() {
        return this.transformable;
    }

    /**
     * Set whether the falling entity should be able to be transformed into a block.
     * Setting this with a timeout cause will not have any effect
     *
     * @param transformable Whether the falling entity should be transformable
     */
    public void setTransformable(boolean transformable) {
        this.transformable = transformable;
    }

    /**
     * Returns whether the falling entity can be dropped as a block item
     *
     * @return Whether the entity can be dropped as a block item
     */
    public boolean isDroppable() {
        return this.droppable;
    }

    /**
     * Set whether the falling entity should be able to be dropped as a block item
     *
     * @param droppable  Whether the falling entity should be droppable
     */
    public void setDroppable(boolean droppable) {
        this.droppable = droppable;
    }

    /**
     * Determines the cause that stopped the entity from falling
     */
    public enum StopCause {
        /**
         * The entity touched the ground
         */
        GROUND,
        /**
         * The fall duration was too long
         */
        TIMEOUT;
    }
}
