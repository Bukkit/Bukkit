package org.bukkit.event.player;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Thrown when a player changes chunks
 */
public class PlayerChangedChunkEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private Chunk from;
    private Chunk to;

    public PlayerChangedChunkEvent(final Player player, final Chunk from, final Chunk to) {
        super(player);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     * <p>
     * If a move or teleport event is cancelled, the player will be moved or
     * teleported back to the Location as defined by getFrom(). This will not
     * fire an event
     *
     * @return true if this event is cancelled
     */
    public boolean isCancelled() {
        return cancel;
    }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     * <p>
     * If a move or teleport event is cancelled, the player will be moved or
     * teleported back to the Location as defined by getFrom(). This will not
     * fire an event
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Gets the chunk this player moved from
     *
     * @return Chunk the player moved from
     */
    public Chunk getFrom() {
        return from;
    }

    /**
     * Sets the chunk to mark as where the player moved from
     *
     * @param from New Chunk to mark as the players previous chunk
     */
    public void setFrom(Chunk from) {
        this.from = from;
    }

    /**
     * Gets the location this player moved to
     *
     * @return Location the player moved to
     */
    public Chunk getTo() {
        return to;
    }

    /**
     * Sets the chunk that this player will move to
     *
     * @param to New chunk this player will move to
     */
    public void setTo(Chunk to) {
        this.to = to;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}