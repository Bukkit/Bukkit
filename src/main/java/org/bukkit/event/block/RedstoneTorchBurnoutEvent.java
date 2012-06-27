package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;

public class RedstoneTorchBurnoutEvent extends BlockRedstoneEvent {

    private static HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public RedstoneTorchBurnoutEvent(Block block, int oldCurrent, int newCurrent) {
        super(block, oldCurrent, newCurrent);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
