package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 *
 * @author Meaglin
 */
public class BlockBreakEvent extends BlockEvent implements Cancellable {

    private Player player;
    private boolean cancel;
    private boolean canDestroy;
    
    public BlockBreakEvent(final Block theBlock, Player player, boolean canDestroy) {
        super(Type.BLOCK_BREAK, theBlock);
        this.player = player;
        this.cancel = false;
        this.canDestroy = canDestroy;
    }

    /**
     * Returns the player destroying the block
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @return true if this event is cancelled
     */
    public boolean isCancelled() {
        return cancel;
    }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
    
    /**
     * Gets the value whether the player would be allowed to destroy here.
     * Defaults to spawn if the server was going to stop them (such as, the
     * player is in Spawn). 
     *
     * @return boolean whether the server would allow a player to destroy here
     */
    public boolean canDestroy() {
        return this.canDestroy;
    }

    /**
     * Sets the canDestroy state of this event. Set to true if you want the
     * player to be able to destroy.
     */
    public void setDestroy(boolean canDestroy) {
        this.canDestroy = canDestroy;
    }
}
