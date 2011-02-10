package org.bukkit.event.block;

import org.bukkit.block.Dispenser;
import org.bukkit.event.Cancellable;

public class DispenserEjectEvent extends BlockEvent implements Cancellable
{
	private Dispenser dispenser;
	private boolean cancelled = false;
	
	
	public DispenserEjectEvent(Type type, Dispenser dispenser)
    {
	    super(type, dispenser.getBlock());
	    this.dispenser = dispenser;
    }
	
	public Dispenser getDispenser()
	{
		return dispenser;
	}

	public boolean isCancelled()
	{
		return cancelled;
	}

	public void setCancelled(boolean cancel)
	{
		cancelled = cancel;
	}
}
