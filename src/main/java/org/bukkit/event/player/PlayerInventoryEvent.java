package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.event.Cancellable;

/**
 * Represents a player related inventory event
 */
public class PlayerInventoryEvent extends PlayerEvent implements Cancellable {
    protected Inventory inventory;
    private boolean cancel;

    public PlayerInventoryEvent(final Player player, final Inventory inventory) {
        super(Type.PLAYER_INVENTORY, player);
        this.inventory = inventory;
        this.cancel = false;
    }
    public boolean isCancelled(){
        return this.cancel;
    }
    
    public void setCancelled(boolean cancel){
        this.cancel = cancel;
    }

    /**
     * Gets the Inventory involved in this event
     *
     * @return Inventory
     */
    public Inventory getInventory() {
        return inventory;
    }
}
