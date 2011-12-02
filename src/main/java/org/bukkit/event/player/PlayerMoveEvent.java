package org.bukkit.event.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Type;

/**
 * Holds information for player movement events
 */
public class PlayerMoveEvent extends PlayerChangePositionEvent {
    /**
     * Create a new player move event.
     *
     * @param player the player that's moving
     * @param from the old location
     * @param to the new location
     */
    public PlayerMoveEvent(Player player, Location from, Location to) {
        super(Type.PLAYER_MOVE, player, from, to);
    }
}
