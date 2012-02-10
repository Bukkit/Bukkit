package org.bukkit.event.entity;

import org.bukkit.entity.Entity;

public class CreeperFuseEvent extends EntityEvent {
    
    public CreeperFuseEvent(Entity creeper) {
        super(Type.CREEPER_FUSE, creeper);
    }
}
