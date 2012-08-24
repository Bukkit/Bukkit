package org.bukkit.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * 	This event is representing, an dynamical MaxHealth change of an instance of an Entity
 */
public class EntityChangeMaxHealthEvent extends EntityEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled = false;
	private int newMax;
	private boolean force;
	
	/**
	 * Constructor
	 * @param entity The entity
	 * @param max The new max health
	 * @param force If to set the current health of the instance to the maxHealth
	 */
	public EntityChangeMaxHealthEvent(final LivingEntity entity, int max, boolean force) {
		super(entity);
		this.newMax = max;
		this.force = force;
	}

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}
	
	/**
	 * @return boolean, if the current health of the entity shall be equal to the new max
	 */
	public boolean OverwriteIsForced() {
		return this.force;
	}
	
	/**
	 * Sets if the current health of the entity shall be equal to the new max
	 * @param boolean, see above
	 */
	public void setOverwriteIsForced(boolean force) {
		this.force = force;
	}
	
	/**
	 * Sets the (new) max health of the Entity
	 * @param max int, The new max health
	 */
	public void setMaxHealth(int max) {
		this.newMax = max;
	}
	
	/**
	 * Get the new max health
	 * @return int, The new max health
	 */
	public int getNewMaxHealth() {
		return this.newMax;
	}
	
	/**
	 * Get the current max health of the entity
	 * @return int, the current max health
	 */
	public int getCurrentMaxHealth() {
		return ((LivingEntity) this.entity).getMaxHealth();
	}
}
