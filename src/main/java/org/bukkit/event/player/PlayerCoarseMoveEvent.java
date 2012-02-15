package org.bukkit.event.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Holds information for player movement events occurring ONLY when the player changes block position.
 */
public class PlayerCoarseMoveEvent extends PlayerMoveEvent {

    public PlayerCoarseMoveEvent(final Player player, final Location from, final Location to) {
        super(player, from, to);
    }

    public PlayerCoarseMoveEvent(final Type type, final Player player, final Location from, final Location to) {
        super(type, player, from, to);
    }
}
