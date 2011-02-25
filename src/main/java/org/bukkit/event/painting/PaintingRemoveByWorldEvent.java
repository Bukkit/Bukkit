package org.bukkit.event.painting;

import org.bukkit.entity.Painting;

/**
 * Triggered when a painting is removed by the world (water flowing over it, block damaged behind it)
 *
 * @author Tanel Suurhans
 */
public class PaintingRemoveByWorldEvent extends PaintingRemoveEvent {

    public PaintingRemoveByWorldEvent(final Painting painting) {
        super(painting, RemoveCause.WORLD);
    }

}
