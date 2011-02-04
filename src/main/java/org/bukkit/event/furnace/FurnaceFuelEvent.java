package org.bukkit.event.furnace;

import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;

/**
 * This Event is called when there is a Material in the Furnace and
 * a Fuel is added to determine the time the fuel can work
 * If this Event is canceled the _fuelTime is used
 * The Item which is put in is saved in the field _fuelItemStack
 * @author sheepy
 */
public class FurnaceFuelEvent extends FurnaceEvent implements Cancellable {

	private boolean 	_cancel;
	private ItemStack 	_fuelItemStack;
	private int 		_fuelTime;
	
	public FurnaceFuelEvent(Type type) {
		super(type);
		_cancel = false;
		_fuelTime = 0;
	}

	public boolean isCancelled() {
		return _cancel;
	}
	
	 public void setCancelled(boolean cancel) {
	        _cancel = cancel;
	 }

	public int getBurnTime() {
		return _fuelTime;
	}
	
	public void setBurnTime(int value){
		_fuelTime = value;
	}

	public void setBurnItemStack(ItemStack itemStack) {
		_fuelItemStack = itemStack;
	}
	
	public ItemStack getBurnItemStack(){
		return _fuelItemStack;
	}

}
