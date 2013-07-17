package org.bukkit.event.entity;

import org.bukkit.entity.EnderSignal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Thrown when an ender signal (eye of ender) is launched by a human
 */
public class EnderSignalLaunchEvent extends EntityEvent implements Cancellable{
    private static final HandlerList handlers = new HandlerList();
	private HumanEntity shooter;
	private boolean cancel = false;
	
	/**
	 * Creates a new EnderSignalLaunchEvent
	 * @param what the {@link org.bukkit.entity.EnderSignal} object
	 * @param shooter the shooter, likely a player
	 */
	public EnderSignalLaunchEvent(Entity what, HumanEntity shooter){
		super(what);
	}
	
	/**
	 * Gets who shot the ender signal
	 * @return the shooter
	 */
	public HumanEntity getShooter(){
		return shooter;
	}
	
	@Override
	public EnderSignal getEntity(){
		return (EnderSignal)entity;
	}

	@Override
	public boolean isCancelled(){
		return cancel;
	}

	@Override
	public void setCancelled(boolean cancel){
		this.cancel=cancel;
	}

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
