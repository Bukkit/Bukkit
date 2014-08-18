package org.bukkit.event.entity;

import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;

/**
 * Thrown when an entity picks up and equips an item from the ground
 */
public class EntityEquipEvent extends EntityPickupItemEvent {
    private static final HandlerList handlers = new HandlerList();
    private final int equipSlot;

    public EntityEquipEvent(final LivingEntity entity, final Item item, final int equipSlot) {
        super(entity, item, item.getPickupDelay());
        this.equipSlot = equipSlot;
    }

    /**
     * Gets the living entity involved in this event.
     *
     * @return LivingEntity
     */
    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) entity;
    }

    /**
     * Gets the raw slot to equip the item.
     *
     * @return raw slot to equip the item.
     */
    public int getEquipSlot() {
        return equipSlot;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
