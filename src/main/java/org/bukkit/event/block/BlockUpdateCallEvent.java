package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 *
 * @author HaxtorMoogle
 * 
 */
public class BlockUpdateCallEvent extends BlockEvent implements Cancellable {

   // private Player player;
    private boolean cancel;

    public BlockUpdateCallEvent(final Block theBlock) {
        super(Event.Type.BLOCK_UPDATE, theBlock);
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
