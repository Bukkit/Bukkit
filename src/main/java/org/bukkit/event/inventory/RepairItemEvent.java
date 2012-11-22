package org.bukkit.event.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.InventoryView;

/**
 * Called whenever an Item is repaired/renamed.
 */
public class RepairItemEvent extends InventoryClickEvent{
    
    private static HandlerList handlers = new HandlerList();
    
    public RepairItemEvent(InventoryView what, SlotType type, int slot, boolean right, boolean shift) {
        super(what, type, slot, right, shift);
    }
   
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
