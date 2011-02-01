
package org.bukkit.event.player;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 *
 * @author Meaglin
 */
public class PlayerPickupItemEvent extends PlayerEvent implements Cancellable {
    private final Item pickup;
    private boolean cancel = false;

    public PlayerPickupItemEvent(final Type type, final Player player, final Item pickup) {
        super(type, player);
        this.pickup = pickup;
    }

    /**
     * Gets the ItemDrop created by the player
     *
     * @return ItemDrop
     */
    public Item getItem() {
        return pickup;
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
}