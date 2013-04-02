package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;

/**
 * Called when a TNT is ignited, leading to the creation of a primed TNT.
 * <p />
 * If a TNTIgniteEvent is cancelled, the {@link TNTPrimed} will not be spawned.
 */
public class TNTIgniteEvent extends FuseStartedEvent {
    private final Block block;

    public TNTIgniteEvent(final Block block, final TNTPrimed entity) {
        super(entity);
        this.block = block;
    }

    /**
     * Gets the {@link Block} of TNT that started the event.
     *
     * @return The TNT Block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Gets the {@link TNTPrimed} that will be spawned.
     *
     * @return The {@link TNTPrimed} that will be spawned
     */
    @Override
    public TNTPrimed getEntity() {
        return (TNTPrimed) entity;
    }
}
