package org.bukkit.event.furnance;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event.Type;
import org.bukkit.inventory.ItemStack;

public class FurnanceProduceResultEvent extends FurnanceEvent implements Cancellable {

	private boolean _cancel;
	private ItemStack _rawItemStack;
	private ItemStack _resultItemStack;

	public FurnanceProduceResultEvent(Type type) {
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
