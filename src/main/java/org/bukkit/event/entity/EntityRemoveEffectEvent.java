package org.bukkit.event.entity;

import org.bukkit.EntityEffect;
import org.bukkit.entity.Entity;

/**
 * @author zml2008
 */
public class EntityRemoveEffectEvent extends EntityEvent {
    private final EntityEffect effect;
    public EntityRemoveEffectEvent(final Entity what, EntityEffect effect) {
        super(Type.ENTITY_REMOVE_EFFECT, what);
        this.effect = effect;
    }

    public EntityEffect getEffect() {
        return effect;
    }
}
