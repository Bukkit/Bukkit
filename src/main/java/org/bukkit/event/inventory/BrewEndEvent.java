package org.bukkit.event.inventory;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;

public class BrewEndEvent extends BlockEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private BrewerInventory contents;
    private boolean cancelled;
    private ItemStack ingredient;

    public BrewEndEvent(Block brewer, BrewerInventory contents, ItemStack ingredient) {
        super(brewer);
        this.contents = contents;
        this.ingredient = ingredient;
    }

    public BrewerInventory getContents() {
        return contents;
    }

    public Potion[] getPotions() {
        Potion[] potions = new Potion[3];
        if(contents.getContents()[0] != null)
            potions[0] = Potion.fromItemStack(contents.getContents()[0]);
        if(contents.getContents()[1] != null)
            potions[1] = Potion.fromItemStack(contents.getContents()[1]);
        if(contents.getContents()[2] != null)
            potions[2] = Potion.fromItemStack(contents.getContents()[2]);
        return potions;
    }

    public ItemStack getIngredient() {
        return ingredient;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}