package org.bukkit.event.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.Container;
import org.bukkit.inventory.Inventory;

/**
 * Represents a player related inventory event
 */
public class PlayerContainerEvent extends PlayerEvent {
    private final Container container;

    public PlayerContainerEvent(final Type type, final Player player, final Container container) {
        super(type, player);
        this.container = container;
    }

    /**
     * @return the container
     */
    public Container getContainer() {
        return container;
    }

}
