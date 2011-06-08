package org.bukkit.event.world;

import org.bukkit.block.Block;
import org.bukkit.World;
import org.bukkit.event.Cancellable;
import java.util.ArrayList;

/**
 * Called on leaves decaying
 */
public class PortalCreateEvent extends WorldEvent implements Cancellable {
    private boolean cancel = false;
    private ArrayList<Block> blocks = new ArrayList<Block>(); 

    public PortalCreateEvent(final ArrayList<Block> blocks, final World world) {
        super(Type.PORTAL_CREATE, world);
        this.blocks = blocks;
    }

    public ArrayList<Block> getBlocks(){
        return this.blocks;
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