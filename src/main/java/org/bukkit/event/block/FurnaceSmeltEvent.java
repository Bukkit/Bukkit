package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;

/**
 * @author yetanotherx
 */
public class FurnaceSmeltEvent extends BlockEvent implements Cancellable {
    
    private int smeltedItemId;
    private boolean cancel;
    
    public FurnaceSmeltEvent(Block theBlock, int item) {
	super(Type.FURNACE_SMELT, theBlock);
	
        this.cancel = false;
        this.smeltedItemId = item;
        
    }
    
    /**
     * Gets the smelted item for this event
     *
     * @return Item smeltedItem
     */
    public int getItemId() {
        return smeltedItemId;
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

}
