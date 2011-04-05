
package org.bukkit.event.entity;

import java.util.List;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

/**
 * Thrown whenever a LivingEntity dies
 */
public class EntityDeathEvent extends EntityEvent {
    private List<ItemStack> drops;
    private Entity killer;

    /**
     * Construct an EntityDeathEvent for a victim with an unknown causing entity
     * @param victim the entity who died
     * @param drops the items left behind by the dead entity
     */
    public EntityDeathEvent(final Entity victim, final List<ItemStack> drops) {
        super(Type.ENTITY_DEATH, victim);
        this.drops = drops;
        this.killer = null;
    }
    
    /**
     * Construct and EntityDeathEvent for a victim with a known causing entity
     * @param victim the entity who died
     * @param killer the entity causing the death
     * @param drops the items left behind by the dead entity
     */
    public EntityDeathEvent(final Entity victim, final Entity killer, final List<ItemStack> drops) {
        this(victim, drops);
        this.killer = killer;
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
     * Set the items that will be dropped after the entity's death
     * @param drops a {@link List} of {@link ItemStack} to be dropped
     */
    public void setDrops(List<ItemStack> drops) {
        this.drops = drops;
    }
    
    /**
     * Retrieve the entity that caused the death, if known.
     * @return the entity that caused the death or null if it's not known
     */
    public Entity getKiller() {
        return killer;
    }
}
