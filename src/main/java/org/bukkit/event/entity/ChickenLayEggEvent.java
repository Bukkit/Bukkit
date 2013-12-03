package org.bukkit.event.entity;

import org.bukkit.Sound;
import org.bukkit.entity.Chicken;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ChickenLayEggEvent extends EntityEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private boolean canceled;

	private ItemStack egg;

	private Sound sound;

	private int ticksToNextLay;

	public ChickenLayEggEvent(final Chicken chicken, ItemStack egg, Sound sound, int ticksToNextLay) {
		super(chicken);
		canceled = false;
		this.egg = egg;
		this.sound = sound;
		setTicksToNextLay(ticksToNextLay);
	}

	/**
	 * Gets the egg item
	 * 
	 * @return the egg
	 */
	public ItemStack getEggItem() {
		return egg;
	}

	/**
	 * Sets the egg item
	 * 
	 * NOTE: set to null to disable item dropping
	 * 
	 * @param egg
	 *            the egg to set
	 */
	public void setEggItem(ItemStack egg) {
		this.egg = egg;
	}

	/**
	 * Gets the sound to play
	 * 
	 * @return the sound
	 */
	public Sound getSound() {
		return sound;
	}

	/**
	 * Sets the sound to play
	 * 
	 * NOTE: set to null to disable sound playing
	 * 
	 * @param sound
	 *            the sound to set
	 */
	public void setSound(Sound sound) {
		this.sound = sound;
	}

	/**
	 * @return the ticksToNextLay
	 */
	public int getTicksToNextLay() {
		return ticksToNextLay;
	}

	/**
	 * @param ticksToNextLay
	 *            the ticksToNextLay to set
	 */
	public void setTicksToNextLay(int ticksToNextLay) {
		this.ticksToNextLay = ticksToNextLay;
	}

	@Override
	public boolean isCancelled() {
		return canceled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		canceled = cancel;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
