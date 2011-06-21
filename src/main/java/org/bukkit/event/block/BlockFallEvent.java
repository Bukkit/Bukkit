package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

/**
 * Called when a block attempts to fall
 *
 * @author Xolsom
 */
public class BlockFallEvent extends BlockEvent {
    private final Entity fallingEntity;

    protected boolean instantFall;

    public BlockFallEvent(Block block, Entity fallingEntity, boolean instantFall) {
        super(Type.BLOCK_FALL, block);

        this.fallingEntity = fallingEntity;
        this.instantFall = instantFall;
    }

    /**
     * Get the instant fall flag of the falling block
     *
     * @return Whether the block would fall instantly
     */
    public boolean getInstantFall() {
        return this.instantFall;
    }

    /**
     * Get the entity into which the falling block would turn to.
     *
     * @return The entity into which the falling block would turn to
     */
    public Entity getFallingEntity() {
        return this.fallingEntity;
    }
}
