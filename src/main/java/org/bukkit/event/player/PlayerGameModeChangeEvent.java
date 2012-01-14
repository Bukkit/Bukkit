package org.bukkit.event.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

@SuppressWarnings("serial")
public class PlayerGameModeChangeEvent extends PlayerEvent implements Cancellable {

    private boolean cancelled;
    private GameMode newGameMode;

    public PlayerGameModeChangeEvent(Player player, GameMode newGameMode) {
        super(Type.PLAYER_GAME_MODE_CHANGE, player);
        this.newGameMode = newGameMode;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public GameMode getNewGameMode() {
        return newGameMode;
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
