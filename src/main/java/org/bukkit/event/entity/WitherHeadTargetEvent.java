package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wither.WitherHead;
import org.bukkit.event.Cancellable;

/**
 * Called when a wither head targets or untargets an entity for its wither
 * skull attack.
 */
public class WitherHeadTargetEvent extends EntityTargetLivingEntityEvent implements Cancellable {
    private boolean cancel = false;
    private WitherHead head;

    public WitherHeadTargetEvent(Entity entity, LivingEntity target, TargetReason reason, WitherHead head) {
        super(entity, target, reason);
        this.head = head;
    }

    /**
     * Gets which head of the wither is targeting an entity.
     *
     * @return Which wither head is targeting an entity
     */
    public WitherHead getWitherHead() {
        return head;
    }

    public boolean isCancelled() {
        return cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
