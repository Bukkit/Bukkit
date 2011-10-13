package org.bukkit.event.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.Container;
import org.bukkit.inventory.Inventory;

public class PlayerCloseContainerEvent extends PlayerContainerEvent {

    public PlayerCloseContainerEvent(Player player, Container container) {
        super(Type.CONTAINER_CLOSE, player, container);
    }
}
