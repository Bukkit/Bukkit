package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 * Represents a player related event
 */
@SuppressWarnings("serial")
public abstract class PlayerEvent extends Event {
    protected Player player;

    public PlayerEvent(final Event.Type type, final Player who) {
        super(type);
        player = who;
    }

    /**
     * Returns the player involved in this event
     *
     * @return Player who is involved in this event
     */
    public final Player getPlayer() {
        return player;
    }
}
