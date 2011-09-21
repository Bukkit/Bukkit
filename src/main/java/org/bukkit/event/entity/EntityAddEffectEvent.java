package org.bukkit.event.entity;

import org.bukkit.EntityEffect;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

/**
 * @author zml2008
 */
public class EntityAddEffectEvent extends EntityEvent implements Cancellable {
    private final EntityEffect effect;
    private byte amplitude;
    private short duration;
    private boolean cancelled = false;
    public EntityAddEffectEvent(final Entity what, EntityEffect effect, byte amplitude, short duration) {
        super(Type.ENTITY_ADD_EFECT, what);
        this.effect = effect;
        this.amplitude = amplitude;
        this.duration = duration;
    }

    public EntityEffect getEffect() {
        return effect;
    }

    public byte getAmplitude() {
        return amplitude;
    }

    public short getDuration() {
        return duration;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancelled;
    }
}
