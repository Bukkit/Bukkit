package org.bukkit.event.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * 
 * Called when an Player clicks on an Slot in Inventory
 *
 */
public class InventoryClickEvent extends Event implements Cancellable {
	private boolean cancel;
	private ItemStack itemstack;
	private int slot;
	private Event.Inventory inventoryType;
	private Player player;
	
	
	public InventoryClickEvent(Player player, ItemStack e, int slot, Event.Inventory inventoryType){
		super(Type.INVENTORY_CLICK);
		this.cancel = false;
		this.itemstack = e;
		this.slot = slot;
		this.inventoryType = inventoryType;
		this.player = player;
	}
	public boolean isCancelled() {
		return cancel;
	}

	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}
	
	/**
	 * Gets the Itemstack which is at the Slot, the player clicked.
	 */
	
	public ItemStack getItemStack(){
		return this.itemstack;
	}
	
	/**
	 * Gets the Slot-ID the Player clicked. -999 means click in the nothing
	 */
	
	public int slot(){
		return this.slot;
	}
	
	/**
	 * Gets the Player who clicked
	 */
	public Player getPlayer(){
		return this.player;
	}
	
	/**
	 * Gets the Inventorytype the user clicked
	 * Event.Inventory.NONE for click in the nothing
	 * Event.Inventory.CONTAINER for click in the container
	 * Event.Inventory.PLAYER for click in the own inventory
	 */
	public Event.Inventory getInventoryType(){
		return this.inventoryType;
	}
	
    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
