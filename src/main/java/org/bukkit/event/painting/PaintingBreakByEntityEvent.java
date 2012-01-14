package org.bukkit.event.painting;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Painting;
import org.bukkit.event.DelegateRegistration;

/**
 * Triggered when a painting is removed by an entity
 */
@SuppressWarnings("serial")
@DelegateRegistration(PaintingBreakEvent.class)
public class PaintingBreakByEntityEvent extends PaintingBreakEvent {
    private Entity remover;

    public PaintingBreakByEntityEvent(final Painting painting, final Entity remover) {
        super(painting, RemoveCause.ENTITY);
        this.remover = remover;
    }

    /**
     * Gets the entity that removed the painting
     *
     * @return the entity that removed the painting.
     */
    public Entity getRemover() {
        return remover;
    }
}
