package org.bukkit.event.inventory;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class FurnaceExpEvent extends Event {

    public static final HandlerList handlers = new HandlerList();
    private float exp;
    private ItemStack result;

    public FurnaceExpEvent(ItemStack result, float expDropped) {
        this.exp = expDropped;
        this.result = result;
    }

    public ItemStack getResult() {
        return result;
    }

    public void setResult(ItemStack result) {
        this.result = result;
    }

    public float getAmount() {
        return exp;
    }

    public void setAmount(float amount) {
        this.exp = amount;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
