package org.bukkit.event.entity;

import org.bukkit.entity.Chicken;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Called when a chicken lays an egg
 */
public class ChickenLayEggEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private boolean playSound;
    private ItemStack droppedItem;
    private int timeUntilNextEgg;

    public ChickenLayEggEvent(final Chicken chicken, int ticksUntilNextEgg) {
        super(chicken);
        playSound = true;
        droppedItem = new ItemStack(344, 1); // The default item is an egg
        timeUntilNextEgg = ticksUntilNextEgg; // -1 signifies time until next egg is determined by vanilla server
    }

    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets the cancellation state of this event. A cancelled event will not be executed in the server,
     * but will still pass to other plugins. NOTE: the time until the next egg will still be updated with the value in this event,
     * even if it is cancelled.
     */
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public Chicken getEntity() {
        return (Chicken) entity;
    }

	/**
	 * Gets the item the chicken is laying. Modifying the returned ItemStack will have no effect;
	 * use {@link #setItem(org.bukkit.inventory.ItemStack)} instead.
	 * 
	 * @return The item being laid
	 */
    public ItemStack getItem() {
        return droppedItem.clone();
    }

	/**
	 * Sets the item to be laid by the chicken. Passing null will effectively cancel the event:
	 * no sound will be played but the time until the next egg is laid will be updated with the value set in the event.
	 * 
	 * @param item the item being laid
	 */
    public void setItem(ItemStack item) {
        droppedItem = item;
    }

	/**
	 * Gets the amount of time after the current event completes before the next egg is laid.
	 * By default, this value ranges from 6000 to 12000 ticks.
	 * 
	 * @return The time, in ticks, before the next egg drop
	 */
    public int getTicksUntilNextEgg() {
        return timeUntilNextEgg;
    }

	/**
	 * Sets the amount of time after the current event completes before the next egg is laid.
	 * This value cannot be less than zero.
	 * 
	 * @param ticks The time, in ticks, before the next egg drop
	 */
    public void setTicksUntilNextEgg(int ticks) {
        if (ticks < 0) // default to the current value, assigned by another plugin or by vanilla server code
            return;
        timeUntilNextEgg = ticks;
    }

    /**
     * @return whether the "mob.chicken.plop" sound will play when the egg is laid
     */
    public boolean willPlaySound() {
        return playSound;
    }

    /**
     * Sets whether the "mob.chicken.plop" sound will play when the egg is laid. This value will have no effect
     * if the event is cancelled.
     * 
     * @param play Whether the chicken should make a sound when laying the egg (defaults to true)
     */
    public void setWillPlaySound(boolean play) {
        playSound = play;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
