package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 *
 * @author HaxtorMoogle
 * 
 */
public class BlockUpdateTickEvent extends BlockEvent implements Cancellable {

   // private Player player;
    private boolean cancel;

    public BlockUpdateTickEvent(final Block theBlock) {
        super(Event.Type.BLOCK_UPDATE_TICK, theBlock);
        //this.player = player;
        this.cancel = false;
    }


    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
