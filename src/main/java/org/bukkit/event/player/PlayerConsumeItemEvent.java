package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a player eats food or is drinking a potion or milk
 */
public class PlayerConsumeItemEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private int nutrition;
    private ItemStack item;

    public PlayerConsumeItemEvent(final Player who, int nutrition, ItemStack item) {
        super(who);
        this.nutrition = nutrition;
        this.item = item;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * Returns the amount of restored food points
     * (nutrition fact)
     * 0 if a potion is consumed.
     */
    public int getNutrition() {
        return nutrition;
    }

    /**
     * Sets the amount of restored food points
     */
    public void setNutrition(int nutrition) {
        this.nutrition = nutrition;
    }

    /**
     * Returns the item the player is consuming 
     */
    public ItemStack getItem(){
        return item;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}