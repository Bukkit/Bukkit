package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Fired when a player changes their currently held item
 */
public class PlayerItemBreakEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final ItemStack brokenItem;

    public PlayerItemBreakEvent(final Player player, final ItemStack brokenItem){
        super(player);
        this.brokenItem = brokenItem;
    }

    /**
     * Gets the item that broke. This is a dummy ItemStack
     * 
     * @return The broken item
     */
    public ItemStack getBrokenItem(){
        return brokenItem;
    }

    @Override
    public HandlerList getHandlers(){
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }
}
