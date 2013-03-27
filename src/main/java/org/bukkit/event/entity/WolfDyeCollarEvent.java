package org.bukkit.event.entity;

import org.bukkit.DyeColor;
import org.bukkit.entity.Wolf;

/**
 * Called when a wolf's collar is dyed
 */
public class WolfDyeCollarEvent extends EntityDyeEvent {
    public WolfDyeCollarEvent(final Wolf wolf, DyeColor color) {
        super(wolf, color);
    }

    @Override
    public Wolf getEntity() {
        return (Wolf) entity;
    }
}
