package org.bukkit.event.weather;

import org.bukkit.World;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TimeChangeEvent extends Event implements Cancellable {
	
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private long timeBefore;
    private long currentTime;
    private World world;
    
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
    
    public TimeChangeEvent(World world, long l, long n) {
    	this.timeBefore = l;
    	this.currentTime = n;
    	this.world = world;
    }
    
	public boolean isCancelled() {
		return cancelled;
	}
	
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public long getTimeBefore() {
		return timeBefore;
	}
	
	public long getCurrentTime() {
		return currentTime;
	}
	
	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
		world.setTime(currentTime);
	}
	
	public World getWorld() {
		return world;
	}
}
