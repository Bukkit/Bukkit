package org.bukkit.event.painting;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Painting;

/**
 * Triggered when a painting is removed by an entity
 *
 * @author Tanel Suurhans
 */
public class PaintingRemoveByEntityEvent extends PaintingRemoveEvent {

    private Entity remover;

    public PaintingRemoveByEntityEvent(final Painting painting, final Entity remover) {
        super(painting, RemoveCause.ENTITY);
        this.remover = remover;
    }

    public Entity getRemover() {
        return remover;
    }

}
