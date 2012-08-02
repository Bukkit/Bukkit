package org.bukkit.event.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when an item is dispensed from a block.
 * <p />
 * If a Block Dispense event is cancelled, the block will not dispense the item.
 */
public class BlockDispenseLiquidEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private Material material;
    private Block liquidBlock;
    private boolean dispensing;

    public BlockDispenseLiquidEvent(final Block dispenserBlock, final Block liquidBlock, final Material material, final boolean dispensing) {
        super(dispenserBlock);
        this.liquidBlock = liquidBlock;
        this.material = material;
        this.dispensing = dispensing;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
    
    public Material getMaterial() {
    	return material;
    }
    
    public Block getLiquidBlock() {
    	return liquidBlock;
    }
    
    public boolean isDispensing() {
    	return dispensing;
    }
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
