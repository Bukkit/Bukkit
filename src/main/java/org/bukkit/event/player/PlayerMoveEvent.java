package org.bukkit.event.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Holds information for player movement events that occur any time the player makes any kind of movement including looking about.
 */
public class PlayerMoveEvent extends PlayerGridMoveEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    public PlayerMoveEvent(final Player player, final Location from, final Location to) {
        super(player, from, to);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
