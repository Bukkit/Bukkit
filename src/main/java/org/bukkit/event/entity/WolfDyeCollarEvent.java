package org.bukkit.event.entity;

import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.HandlerList;

/**
 * Called when a wolf's collar is dyed
 */
public class WolfDyeCollarEvent extends EntityDyeEvent {
    private static final HandlerList handlers = new HandlerList();
    private Player dyer;

    public WolfDyeCollarEvent(final Wolf wolf, final DyeColor color, final Player dyer) {
        super(wolf, color);
        this.dyer = dyer;
    }

    @Override
    public Wolf getEntity() {
        return (Wolf) entity;
    }

    /**
     * Get the player who dyed this wolf's collar.
     *
     * @return the player who dyed this wolf's collar
     */
    public Player getDyer() {
        return dyer;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
