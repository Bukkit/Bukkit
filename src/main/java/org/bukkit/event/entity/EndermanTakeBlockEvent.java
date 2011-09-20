package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

public class EndermanTakeBlockEvent extends EntityEvent implements Cancellable
{
    private boolean canceled = false;
    private Block   block;
    
    public EndermanTakeBlockEvent(Entity enderman, Block block)
    {
        super(Type.ENDERMAN_TAKE_BLOCK, enderman);
    }
    
    public boolean isCancelled()
    {
        return canceled;
    }
    
    public void setCancelled(boolean cancel)
    {
        this.canceled = cancel;
    }
    
    public Block getBlock()
    {
        return block;
    }
    
    public void setBlock(Block block)
    {
        this.block = block;
    }
    
}
