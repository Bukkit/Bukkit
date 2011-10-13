package org.bukkit.event.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.Container;
import org.bukkit.inventory.Inventory;

public class PlayerOpenContainerEvent extends PlayerContainerEvent implements Cancellable {

    private boolean cancelled;
    public PlayerOpenContainerEvent(Player player, Container container) {
        super(Type.CONTAINER_OPEN, player, container);
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
