package org.bukkit.event.furnance;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event.Type;
import org.bukkit.inventory.ItemStack;

/**
 * If the Event is Cancelled a Value must put in if the the MAterial can be 
 * used as burn Material or not
 * @author sheepy
 *
 */
public class FurnanceAddRawMaterial extends FurnanceEvent implements Cancellable {

    private boolean 	_cancel;
    private boolean 	_burnable;
    private ItemStack 	_burnMaterial;

	public FurnanceAddRawMaterial(Type type) {
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
        return _burnable;
    }

    public void setBurnable(boolean burnable) {
        _burnable = burnable;
    } 

	public void setRawMaterial(ItemStack itemStack) {
		_burnMaterial = itemStack;
	}
	
	public ItemStack getRawMaterial(){
		return _burnMaterial;
	}
}
