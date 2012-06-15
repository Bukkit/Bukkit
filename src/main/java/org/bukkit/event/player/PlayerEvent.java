package org.bukkit.event.player;

import org.bukkit.entity.Player;

/**
 * Represents a player related event
 */
public interface PlayerEvent {
    /**
     * Returns the player involved in this event
     *
     * @return Player who is involved in this event
     */
    public Player getPlayer();
}
