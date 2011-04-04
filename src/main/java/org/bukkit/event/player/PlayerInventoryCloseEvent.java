
package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Represents a player related inventory event
 */
public class PlayerInventoryCloseEvent extends PlayerInventoryEvent {    
    public PlayerInventoryCloseEvent(Player player, Inventory inventory) {
        super(Type.INVENTORY_CLOSE, player, inventory);
    }
}
