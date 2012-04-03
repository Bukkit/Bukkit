package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class PlayerInvalidInteractEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final int target;
    private final boolean action;

    public PlayerInvalidInteractEvent(final Player player, final int target, final int action) {
        super(player);
        this.target = target;
        this.action = (action == 1);
    }

    /**
     * Gets the entity ID that the player interacted with
     *
     * @return Entity ID of the target
     */
    public int getTarget() {
        return target;
    }

    /**
     * Gets whether the player left- or right-clicked
     *
     * @return True is left-click, False is right-click
     */
    public boolean getAction() {
        return action;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
