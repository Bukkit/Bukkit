package org.bukkit.event.entity;

import java.util.List;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * Thrown whenever a LivingEntity dies
 */
public class EntityDeathEvent extends EntityEvent {
    private static final HandlerList handlers = new HandlerList();
    private List<ItemStack> drops;
    private int dropExp = 0;

    public EntityDeathEvent(final LivingEntity entity, final List<ItemStack> drops) {
        this(entity, drops, 0);
    }

    public EntityDeathEvent(final LivingEntity what, final List<ItemStack> drops, final int droppedExp) {
        super(what);
        this.drops = drops;
        this.dropExp = droppedExp;
    }

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) entity;
    }

    /**
     * Gets how much EXP should be dropped from this death.
     * <p />
     * This does not indicate how much EXP should be taken from the entity in question,
     * merely how much should be created after its death.
     *
     * @return Amount of EXP to drop.
     */
    public int getDroppedExp() {
        return dropExp;
    }

    /**
     * Sets how much EXP should be dropped from this death.
     * <p />
     * This does not indicate how much EXP should be taken from the entity in question,
     * merely how much should be created after its death.
     *
     * @param exp Amount of EXP to drop.
     */
    public void setDroppedExp(int exp) {
        this.dropExp = exp;
    }

    /**
     * Gets all the items which will drop when the entity dies
     *
     * @return Items to drop when the entity dies
     */
    public List<ItemStack> getDrops() {
        return drops;
    }
    
    /**
     * Sets the list of items that the entity will drop when it dies.
     * 
     * @param items Items that will be dropped by the entity upon death.
     */
    public void setDrops(List<ItemStack> items){
        this.drops = items;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
