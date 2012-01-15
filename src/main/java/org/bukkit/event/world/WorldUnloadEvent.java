package org.bukkit.event.world;

import org.bukkit.World;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Called when a World is unloaded
 */
@SuppressWarnings("serial")
public class WorldUnloadEvent extends WorldEvent implements Cancellable {
    private boolean isCancelled;

    public WorldUnloadEvent(World world) {
        super(Type.WORLD_UNLOAD, world);
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }
    
    private static final HandlerList handlers = new HandlerList();
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
