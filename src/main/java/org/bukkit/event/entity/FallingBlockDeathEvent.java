package org.bukkit.event.entity;

import org.bukkit.entity.FallingBlock;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FallingBlockDeathEvent extends Event implements Cancellable {

    private static HandlerList handlers = new HandlerList();
    private FallingBlock fallingBlock;
    private boolean isCancelled = false;

    public FallingBlockDeathEvent(FallingBlock fallingBlock) {
        this.fallingBlock = fallingBlock;
    }

    public FallingBlock getFallingBlock() {
        return fallingBlock;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
