package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Called early in the command handling process. This event is only
 * for very exceptional cases and you should not normally use it.
 */
@SuppressWarnings("serial")
public class PlayerCommandPreprocessEvent extends PlayerChatEvent {
    public PlayerCommandPreprocessEvent(final Player player, final String message) {
        super(Type.PLAYER_COMMAND_PREPROCESS, player, message);
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
