package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Stores data for damage events
 */
@SuppressWarnings("serial")
public class EntityDamageEvent extends EntityEvent implements Cancellable {

    private int damage;
    private boolean cancelled;
    private DamageCause cause;

    public EntityDamageEvent(Entity damagee, DamageCause cause, int damage) {
        this(Event.Type.ENTITY_DAMAGE, damagee, cause, damage);
    }

    protected EntityDamageEvent(Event.Type type, Entity damagee, DamageCause cause, int damage) {
        super(type, damagee);
        this.cause = cause;
        this.damage = damage;

        damagee.setLastDamageCause(this);
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    /**
     * Gets the amount of damage caused by the Block
     *
     * @return The amount of damage caused by the Block
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets the amount of damage caused by the Block
     *
     * @param damage The amount of damage caused by the Block
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Gets the cause of the damage.
     *
     * @return A DamageCause value detailing the cause of the damage.
     */
    public DamageCause getCause() {
        return cause;
    }
    
    private static final HandlerList handlers = new HandlerList();
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * An enum to specify the cause of the damage
     */
    public enum DamageCause {

        /**
         * Damage caused when an entity contacts a block such as a Cactus.
         *
         * Damage: 1 (Cactus)
         */
        CONTACT,
        /**
         * Damage caused when an entity attacks another entity.
         *
         * Damage: variable
         */
        ENTITY_ATTACK,
        /**
         * Damage caused when attacked by a projectile.
         *
         * Damage: variable
         */
        PROJECTILE,
        /**
         * Damage caused by being put in a block
         *
         * Damage: 1
         */
        SUFFOCATION,
        /**
         * Damage caused when an entity falls a distance greater than 3 blocks
         *
         * Damage: fall height - 3.0
         */
        FALL,
        /**
         * Damage caused by direct exposure to fire
         *
         * Damage: 1
         */
        FIRE,
        /**
         * Damage caused due to burns caused by fire
         *
         * Damage: 1
         */
        FIRE_TICK,
        /**
         * Damage caused by direct exposure to lava
         *
         * Damage: 4
         */
        LAVA,
        /**
         * Damage caused by running out of air while in water
         *
         * Damage: 2
         */
        DROWNING,
        /**
         * Damage caused by being in the area when a block explodes.
         *
         * Damage: variable
         */
        BLOCK_EXPLOSION,
        /**
         * Damage caused by being in the area when an entity, such as a Creeper, explodes.
         *
         * Damage: variable
         */
        ENTITY_EXPLOSION,
        /**
         * Damage caused by falling into the void
         *
         * Damage: 4 for players
         */
        VOID,
        /**
         * Damage caused by being struck by lightning
         *
         * Damage: 5
         */
        LIGHTNING,
        /**
         * Damage caused by committing suicide using the command "/kill"
         *
         * Damage: 1000
         */
        SUICIDE,
        /**
         * Damage caused by starving due to having an empty hunger bar
         *
         * Damage: 1
         */
        STARVATION,
        /**
         * Custom damage.
         *
         * Damage: variable
         */
        CUSTOM
    }
}
