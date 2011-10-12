package org.bukkit.event.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.container.Container;

/**
 * 
 * @author Meaglin
 * 
 * This is the first Basic Bare inventory event which just displays the SlotClick packet info.
 * while being such a simple event it allows plugin owners to do whatever they want.
 *
 */
public class PlayerClickContainerEvent extends PlayerContainerEvent implements Cancellable{

    private int slot;
    private boolean cancelled, rightclick, shiftclick;
    private ItemStack item;
    
    public PlayerClickContainerEvent(Player player, int slot, boolean rightclick, boolean shiftclick, Container container, ItemStack holdingitem) {
        super(Type.CONTAINER_CLICK, player, container);
        this.slot = slot;
        this.rightclick = rightclick;
        this.shiftclick = shiftclick;
        this.item = holdingitem;
    }

    /**
     * @return the slot
     */
    public int getSlot() {
        return slot;
    }

    /**
     * @param slot the slot to set
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * @return if the player is rightclicking
     */
    public boolean isRightclick() {
        return rightclick;
    }

    /**
     * @param sets the rightclick
     */
    public void setRightclick(boolean rightclick) {
        this.rightclick = rightclick;
    }

    /**
     * @return if the player is shiftclicking
     */
    public boolean isShiftclick() {
        return shiftclick;
    }

    /**
     * @param sets the shiftclick
     */
    public void setShiftclick(boolean shiftclick) {
        this.shiftclick = shiftclick;
    }

    /**
     * @return the item
     */
    public ItemStack getHoldingItem() {
        return item;
    }
    
    /**
     * 
     * @param item
     */
    public void setHoldingItem(ItemStack item) {
        this.item = item;
    }
    
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
