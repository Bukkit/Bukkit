package org.bukkit.event.player;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

@SuppressWarnings("serial")
public class PlayerChangedWorldEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    private final World from;

    public PlayerChangedWorldEvent(Player player, World from) {
        super(Type.PLAYER_CHANGED_WORLD, player);
        this.from = from;
    }

    public World getFrom() {
        return from;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
