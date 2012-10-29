package org.bukkit.event.hanging;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Hanging;

/**
 * Triggered when a hanging is removed by an entity
 */
public class HangingBreakByEntityEvent extends HangingBreakEvent {
    private final Entity remover;

    public HangingBreakByEntityEvent(final Hanging hanging, final Entity remover) {
        super(hanging, RemoveCause.ENTITY);
        this.remover = remover;
    }

    /**
     * Gets the entity that removed the hanging
     *
     * @return the entity that removed the hanging.
     */
    public Entity getRemover() {
        return remover;
    }
}
