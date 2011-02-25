package org.bukkit.event.painting;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Painting;
import org.bukkit.event.Cancellable;

/**
 * Triggered when a painting is placed by an entity
 *
 * @author Tanel Suurhans
 */
public class PaintingPlaceEvent extends PaintingEvent implements Cancellable {

    private boolean cancel;
    private Entity placer;

    public PaintingPlaceEvent(final Type type, final Painting painting, final Entity entity) {
        super(type, painting);
        this.placer = entity;
    }

    public Entity getPlacer() {
        return placer;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
