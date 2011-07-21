package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * Represents a player animation event
 */
public class PlayerAnimationEvent extends PlayerEvent implements Cancellable{
    private boolean cancel = false;
    private PlayerAnimationType animationType;

    /**
     * Construct a new PlayerAnimation event
     *
     * @param player The player instance
     */
    public PlayerAnimationEvent(final Player player) {
        super(Type.PLAYER_ANIMATION, player);

        // Only supported animation type for now:
        animationType = PlayerAnimationType.ARM_SWING;
    }

    /**
     * Get the type of this animation event
     *
     * @return the animation type
     */
    public PlayerAnimationType getAnimationType() {
        return animationType;
    }

    public boolean isCancelled() {
        return cancel;
    }
    
    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins.
     * Animation is not seen by all surrounding players if cancelled      
     * 
     * @param cancel true if you wish to cancel this event     
     */
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
