package org.bukkit.event.furnance;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event.Type;
import org.bukkit.inventory.ItemStack;

public class FurnanceAddBurnMaterialEvent extends FurnanceEvent implements Cancellable {

	private boolean 	_cancel;
	private ItemStack 	_burnItemStack;
	private int 		_burnTime;
	
	public FurnanceAddBurnMaterialEvent(Type type) {
		super(type);
		_cancel = false;
	}

	public boolean isCancelled() {
		return _cancel;
	}
	
	 public void setCancelled(boolean cancel) {
	        _cancel = cancel;
	 }

	public int getBurnTime() {
		return _burnTime;
	}
	
	public void setBurnTime(int value){
		_burnTime = value;
	}

	public void setBurnItemStack(ItemStack itemStack) {
		_burnItemStack = itemStack;
	}
	
	public ItemStack getBurnItemStack(){
		return _burnItemStack;
	}

}
