package org.bukkit.event.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a liquid is dispensed from a block.
 * <p />
 * If a Block Dispense Liquid event is cancelled, the block will not dispense the item.
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

    /**
     * Returns the material of the dispensed liquid.
     * This should be Material.WATER_BUCKET or Material.LAVA_BUCKET
     * 
     * @return The material of the dispensed liquid
     */
    public Material getMaterial() {
    	return material;
    }

    /**
     * @return The block where the liquid will be placed.
     */
    public Block getLiquidBlock() {
    	return liquidBlock;
    }

    /**
     * @return Returns whether the dispenser is dispensing or sucking.
     */
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
