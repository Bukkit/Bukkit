package org.bukkit.event.world;

import org.bukkit.World;
import org.bukkit.event.Cancellable;

/**
* Called when the Time in a World changes
*/
public class TimeChangeEvent extends WorldEvent implements Cancellable {
	
	private World world;
	private boolean cancel;
	private ChangeReason changeReason;
	private Long time;
	
    public TimeChangeEvent(World world, ChangeReason changeReason, Long newTime) {
        super(Type.TIME_CHANGE, world);
        this.world = world;
        this.cancel = false;
        this.changeReason = changeReason;
        this.time = newTime;
    }
    
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

	public boolean isCancelled() {
		return this.cancel;
	}
	
	public ChangeReason getChangeReason() {
		return this.changeReason;
	}
	
	public Long getNewTime() {
		return time;
	}
	
	public Long getOldTime() {
		return world.getFullTime();
	}
	
	public void setNewTime(Long time) {
		this.time = time;
	}
	
	public enum ChangeReason {
		/**
         * When a Worldtick happens
         */
		TICK,
		/**
         * When a night is skipped
         */
		BED,
		/**
         * Other (like time command or World.setTime())
         */
		CUSTOM;
	}
}