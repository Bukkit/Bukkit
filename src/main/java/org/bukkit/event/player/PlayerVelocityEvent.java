package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.location.Location;

public class PlayerVelocityEvent extends PlayerEvent implements Cancellable {

    /**
     * Holds information for player velocity events
     */
    private boolean cancel = false;
    private Location velocity;

    public PlayerVelocityEvent(final Player player, final Location velocity) {
        super(Type.PLAYER_VELOCITY, player);
        this.velocity = velocity;
    }

    PlayerVelocityEvent(final Event.Type type, final Player player, final Location velocity) {
        super(type, player);
        this.velocity = velocity;
    }

    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @return true if this event is cancelled
     */
    public boolean isCancelled() {
        return cancel;
    }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Gets the velocity vector that will be sent to the player
     *
     * @return Vector the player will get
     */
    public Location getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity vector that will be sent to the player
     *
     * @param velocity The velocity vector that will be sent to the player
     */
    public void setVelocity(Location velocity) {
        this.velocity = velocity;
    }
}
