package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

@SuppressWarnings("serial")
public class EntityEatEvent extends EntityEvent implements Cancellable {
    
    private boolean cancel;
    private Block block;
    
    public EntityEatEvent(Entity what, Block block) {
	super(Type.ENTITY_EAT, what);
	this.block = block;
	this.cancel = false;
    }
    
    public boolean isCancelled() {
	return cancel;
    }
    
    public void setCancelled(boolean cancel) {
	this.cancel = cancel;
    }
    
    /**
     * Gets the block the entity is about to eat
     * 
     * @return block the entity is about to eat
     */
    public Block getBlock() {
	return block;
    }

}
