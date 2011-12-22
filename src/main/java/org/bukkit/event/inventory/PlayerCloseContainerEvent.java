package org.bukkit.event.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.container.Container;

public class PlayerCloseContainerEvent extends PlayerContainerEvent implements Cancellable {

    private boolean cancelled;
    
    public PlayerCloseContainerEvent(Player player, Container container) {
        super(Type.CONTAINER_CLOSE, player, container);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
