package org.bukkit.event.furnace;

import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;

/**
 * This Event is called when there is a Material in the Furnace to Check 
 * if the Material can be Transformed to something else.
 * When this Event is canceled the _burnable flag is used to
 * determine whether the Material can be transformed or not
 * @Note If this Event returns true for a Material which doesn't have an
 * Result Material (usually defined over the @FurnaceTransformationEvent) the
 * Furnace will crash
 * @author sheepy
 *
 */
public class FurnaceMaterialEvent extends FurnaceEvent implements Cancellable {

    private boolean 	_cancel;
    private boolean 	_transformable;
    private ItemStack 	_materialItemStack;

	public FurnaceMaterialEvent(Type type) {
		super(type);
		_cancel = false;
	}
	
    public boolean isCancelled() {
        return _cancel;
    }

    public void setCancelled(boolean cancel) {
        _cancel = cancel;
    }
    
    public boolean isBurnable() {
        return _transformable;
    }

    public void setBurnable(boolean burnable) {
        _transformable = burnable;
    } 

	public void setRawMaterial(ItemStack itemStack) {
		_materialItemStack = itemStack;
	}
	
	public ItemStack getRawMaterial(){
		return _materialItemStack;
	}
}
