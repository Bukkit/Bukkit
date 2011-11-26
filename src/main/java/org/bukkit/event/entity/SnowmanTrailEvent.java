package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

public class SnowmanTrailEvent extends EntityEvent implements Cancellable {
    private boolean cancel;
    private Block block;
    
    public SnowmanTrailEvent(Entity what, Block block) {
        super(Type.SNOWMAN_TRAIL, what);
        this.cancel = false;
        this.block = block;
    }
    
    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
    
    public Block getBlock() {
        return this.block;
    }
}