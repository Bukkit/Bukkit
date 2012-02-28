package org.bukkit.event.entity;

import java.util.Random;

import org.bukkit.entity.Entity;

/**
 * Called when an entity is damaged by an entity
 */
public class EntityDamageByEntityEvent extends EntityDamageEvent {
    private final Entity damager;
    private boolean critical;

    public EntityDamageByEntityEvent(final Entity damager, final Entity damagee, final DamageCause cause, final int damage, boolean critical) {
        this(damager, damagee, cause, damage);
        this.critical = critical;
    }
    
    public EntityDamageByEntityEvent(final Entity damager, final Entity damagee, final DamageCause cause, final int damage) {
    	super(damagee, cause, damage);
        this.damager = damager;
    }

    /**
     * Returns the entity that damaged the defender.
     *
     * @return Entity that damaged the defender.
     */
    public Entity getDamager() {
        return damager;
    }
    /**
     * Returns the WHOLE damage
     * @returns if crit, normal damage + crit damage else normal damage
     */
    @Override
    public int getDamage() {
    	if(critical) 
    		return this.damage + new Random().nextInt(this.damage / 2 + 2);
    	else
    		return this.damage;
    }
    /**
     * Sets whether this was an crit
     * @param crit TRUE if this was an crit
     */
    public void setCritical(boolean crit) {
    	this.critical = crit;
    }
    /**
     * Whether this is an critical strike/hit
     * @return Whether this is an crit
     */
    public boolean isCritical() {
    	return this.critical;
    }
}
