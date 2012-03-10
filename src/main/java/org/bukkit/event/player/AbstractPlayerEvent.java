package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 * Abstract class representing a player related event
 */
public abstract class AbstractPlayerEvent extends Event implements PlayerEvent {
    protected Player player;

    public AbstractPlayerEvent(final Player who) {
        player = who;
    }

    public final Player getPlayer() {
        return player;
    }
}
