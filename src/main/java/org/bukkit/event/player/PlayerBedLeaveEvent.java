package org.bukkit.event.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * This event is fired when the player is leaving a bed.
 */
public class PlayerBedLeaveEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Block bed;
    private boolean bedSpawnChange;

    @Deprecated
    public PlayerBedLeaveEvent(final Player who, final Block bed) {
        this(who, bed, true);
    }

    public PlayerBedLeaveEvent(final Player who, final Block bed, boolean bedSpawnChange) {
        super(who);
        this.bed = bed;
        this.bedSpawnChange = bedSpawnChange;
    }

    /**
     * Returns where the player left bed.
     *
     * @return where the player left bed
     */
    public Block getBed() {
        return bed;
    }

    /**
     * Indicates if the bed spawn location for player will change to {@link
     * #getBed()} after this event is processed.
     *
     * @return true if player spawn location will change; otherwise false
     */
    public boolean isBedSpawnChange() {
        return bedSpawnChange;
    }

    /**
     * Control if the bed spawn will be changed for player to {@link
     * #getBed()} after this event is processed.
     *
     * @param bedSpawnChange true to cause the bed spawn to change; false to
     * keep the current bed spawn for Player
     */
    public void setBedSpawnChange(boolean bedSpawnChange) {
        this.bedSpawnChange = bedSpawnChange;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
