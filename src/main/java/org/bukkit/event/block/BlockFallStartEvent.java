package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

/**
 * @author Xolsom
 */
public class BlockFallStartEvent extends BlockFallEvent implements Cancellable {
    private boolean cancelled;

    public BlockFallStartEvent(Block block, Entity fallingEntity, boolean instantFall) {
        super(block, fallingEntity, instantFall);

        this.cancelled = false;
    }

    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @return true if this event is cancelled
     */
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Setting the cancellation state of this event. This determines whether
     * the block should turn into an entity and fall or not
     *
     * @param cancelled True to prevent the block from falling
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Set the instant fall flag of the falling block
     *
     * @param instantFall Whether the block should fall instantly
     */
    public void setInstantFall(boolean instantFall) {
        this.instantFall = instantFall;
    }
}
