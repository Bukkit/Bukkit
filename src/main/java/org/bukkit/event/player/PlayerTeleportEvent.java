package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.location.DirectionalLocation;

/**
 * Holds information for player teleport events
 */
public class PlayerTeleportEvent extends PlayerMoveEvent {
    public PlayerTeleportEvent(Player player, DirectionalLocation from, DirectionalLocation to) {
        super(Type.PLAYER_TELEPORT, player, from, to);
    }

    public PlayerTeleportEvent(final Event.Type type, Player player, DirectionalLocation from, DirectionalLocation to) {
        super(type, player, from, to);
    }
}
