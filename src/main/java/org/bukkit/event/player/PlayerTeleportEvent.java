package org.bukkit.event.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Holds information for player teleport events
 */
public class PlayerTeleportEvent extends PlayerChangePositionEvent {
    /**
     * Create a new player move event.
     *
     * @param player the player that's moving
     * @param from the old location
     * @param to the new location
     */
    public PlayerTeleportEvent(Player player, Location from, Location to) {
        super(Type.PLAYER_TELEPORT, player, from, to);
    }
}
