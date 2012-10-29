package org.bukkit.event.hanging;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Hanging;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Triggered when a hanging is created in the world
 */
public class HangingPlaceEvent extends HangingEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Player player;
    private final Block block;
    private final BlockFace blockFace;

    public HangingPlaceEvent(final Hanging hanging, final Player player, final Block block, final BlockFace blockFace) {
        super(hanging);
        this.player = player;
        this.block = block;
        this.blockFace = blockFace;
    }

    /**
     * Returns the player placing the hanging
     *
     * @return Entity returns the player placing the hanging
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the block that the hanging was placed on
     *
     * @return Block returns the block the hanging was placed on
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Returns the face of the block that the hanging was placed on
     *
     * @return BlockFace returns the face of the block the hanging was placed on
     */
    public BlockFace getBlockFace() {
        return blockFace;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
