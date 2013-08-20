package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

/**
 * This event is called when a {@link org.bukkit.entity.Item} receives damage
 * and is about to be removed.
 * <p>
 * Canceling this event will result in the item remaining in the world and not
 * dying. If you cancel this event, it is advisable to examine the DamageCause
 * and attempt to remove the Item from the "situation" by means of teleport().
 * <p>
 * Items whose ItemStack are of the NETHER_STAR Material have innate
 * resistance to explosions. This may change in the future.
 */
public class ItemDeathEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final Location location;
    private final DamageCause cause;
    private boolean cancelled = false;

    public ItemDeathEvent(final Item despawnee, final Location loc, final DamageCause cause) {
        super(despawnee);
        location = loc;
        this.cause = cause;
    }

    @Override
    public Item getEntity() {
        return (Item) entity;
    }

    /**
     * Gets the location at which the item is dying.
     *
     * @return The location at which the item is dying
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Gets the source of the damage that killed this Item
     */
    public DamageCause getCause() {
        return cause;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
