package org.bukkit.event.entity;

import org.bukkit.entity.EnderSignal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * This event is called when an Eye of Ender item is launched by a player
 * to become an Ender Signal entity.
 * <br>
 * This currently can only be initiated by a player.
 */
public class EnderSignalLaunchEvent extends EntityEvent implements Cancellable{
    private static final HandlerList handlers = new HandlerList();
    private HumanEntity launcher;
    private boolean cancel = false;
    
    public EnderSignalLaunchEvent(Entity what, HumanEntity shooter){
        super(what);
        this.launcher = shooter;
    }
    
    /**
     * Gets who launched the ender signal.
     * @return the launcher of the ender signal
     */
    public HumanEntity getLauncher(){
        return launcher;
    }
    
    @Override
    public EnderSignal getEntity(){
        return (EnderSignal) entity;
    }

    @Override
    public boolean isCancelled(){
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel){
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
