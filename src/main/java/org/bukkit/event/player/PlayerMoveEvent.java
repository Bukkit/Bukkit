package org.bukkit.event.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * This event is called whenever the Location of a Player changes.
 * Most plugins do not need the fine-grained control this event provides, and should listen to
 * {@link PlayerGridMoveEvent} instead.
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

    /**
     * Get the location of the player before this event.
     * If this event cancelled, the player will return to this location.
     *
     * @return Location the player moved from
     */
    @Override
    public Location getFrom() {
        return super.getFrom();
    }
}
