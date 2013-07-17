package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wither.WitherHead;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class WitherHeadTargetEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private WitherHead head;
    private LivingEntity target;

    public WitherHeadTargetEvent(Entity entity, LivingEntity target, WitherHead head) {
        super(entity);
        this.head = head;
        this.target = target;
    }

    /**
     * Get the enum representation of which head is targeting an entity
     *
     * @return Which wither head
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

    /**
     * Get the entity that this is targeting.
     *
     * @return The entity
     */
    public LivingEntity getTarget() {
        return target;
    }

    /**
     * Set the entity that you want the mob to target instead.
     * It is possible to be null, null will cause the entity to be
     * target-less.
     * <p>
     * Setting this to null is essentially the same as cancelling the event
     *
     * @param target The entity to target
     */
    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
