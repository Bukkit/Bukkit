package org.bukkit.event.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventorySlotEvent extends Event implements Cancellable{
   private static final HandlerList handlers = new HandlerList();
   private int slot;
   private Inventory inventory;
   private ItemStack old;
   private ItemStack afterOperation;
   private boolean cancelled;
   private boolean playerInventory;
   private Player player;
   
    public InventorySlotEvent(Inventory inventory, int slot, ItemStack old, ItemStack result) {
       this.inventory = inventory;
       this.slot = slot;
       this.old = old;
       this.afterOperation = result;
    }
    public InventorySlotEvent(Inventory inventory, int slot, ItemStack old, ItemStack result, Player player) {
        this(inventory, slot, old, result);
        playerInventory = true;
        this.player = player;
     }
    
    public int getSlot() { 
       return slot;
    }   
    
    public Inventory getInventory() {
       return inventory;
    }

    public ItemStack getCurrentState() {
       return old; 
    }

    public ItemStack getResult() {
       return afterOperation;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setResult(ItemStack result) {
        this.afterOperation = result;
    }

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}
	
	public boolean isPlayerInventory() {
		return playerInventory;
	}
	
	public Player getPlayer() {
		return player;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
}
