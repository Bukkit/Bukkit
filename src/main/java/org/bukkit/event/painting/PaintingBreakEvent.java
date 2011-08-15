package org.bukkit.event.painting;

import java.util.List;

import org.bukkit.entity.Painting;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;

/**
 * Triggered when a painting is removed
 */
public class PaintingBreakEvent extends PaintingEvent implements Cancellable {

    private boolean cancelled;
    private RemoveCause cause;
    private List<ItemStack> drops;

    public PaintingBreakEvent(final Painting painting, RemoveCause cause, List<ItemStack> drops) {
        super(Type.PAINTING_BREAK, painting);
        this.cause = cause;
        this.drops = drops;
    }

    /**
     * Gets the cause for the painting's removal
     *
     * @return the RemoveCause for the painting's removal
     */
    public RemoveCause getCause() {
        return cause;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public List<ItemStack> getDrops() {
        return drops;
    }

    /**
     * An enum to specify the cause of the removal
     */
    public enum RemoveCause {
        /**
         * Removed by an entity
         */
        ENTITY,
        /**
         * Removed by fire
         */
        FIRE,
        /**
         * Removed by placing a block on it
         */
        OBSTRUCTION,
        /**
         * Removed by water flowing over it
         */
        WATER,
        /**
         * Removed by destroying the block behind it, etc
         */
        PHYSICS,
    }
}
