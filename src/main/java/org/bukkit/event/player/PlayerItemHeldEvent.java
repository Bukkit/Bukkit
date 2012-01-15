package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Fired when a player changes their currently held item
 */
@SuppressWarnings("serial")
public class PlayerItemHeldEvent extends PlayerEvent {
    private int previous;
    private int current;

    public PlayerItemHeldEvent(final Player player, final int previous, final int current) {
        super(Type.PLAYER_ITEM_HELD, player);
        this.previous = previous;
        this.current = current;
    }

    /**
     * Gets the previous held slot index
     *
     * @return Previous slot index
     */
    public int getPreviousSlot() {
        return previous;
    }

    /**
     * Gets the new held slot index
     *
     * @return New slot index
     */
    public int getNewSlot() {
        return current;
    }
    
    private static final HandlerList handlers = new HandlerList();
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
