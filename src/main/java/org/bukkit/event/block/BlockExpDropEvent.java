package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;

public class BlockExpDropEvent extends BlockEvent {

    public static final HandlerList handlers = new HandlerList();
    private int exp;

    public BlockExpDropEvent(final Block block, final int expDropped) {
        super(block);
        exp = expDropped;
    }

    public int getAmount() {
        return exp;
    }

    public void setAmount(int amount) {
        exp = amount;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
