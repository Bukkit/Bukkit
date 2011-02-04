package org.bukkit.event.furnace;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event.Type;
import org.bukkit.inventory.ItemStack;

/**
 * This Event is called when a Material should be transformed into a Result ItemStack
 * When this Event is canceled the _rawItemStack.count will be reduced by 1
 * and the _resultItemStack will be produced with the count given in this ItemStack
 * @author sheepy 
 */
public class FurnaceTransformationEvent extends FurnaceEvent implements Cancellable {

	private boolean 	_cancel;
	private ItemStack 	_rawItemStack;
	private ItemStack 	_resultItemStack;

	public FurnaceTransformationEvent(Type type) {
		super(type);
		_cancel = false;
	}

	public ItemStack getResultItemStack() {
		return _resultItemStack;
	}
	
	public void setResultItemStack(ItemStack itemStack) {
		_resultItemStack = itemStack;
	}

	public boolean isCancelled() {
        return _cancel;
    }

    public void setCancelled(boolean cancel) {
        _cancel = cancel;
    }

	public void setRawItemStack(ItemStack itemStack) {
		_rawItemStack = itemStack;
	}
	
	public ItemStack getRawMaterial() {
		return _rawItemStack;
	}

}
