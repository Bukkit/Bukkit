package org.bukkit.event.anvil;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.HumanEntity;

/**
 * Called when items are placed in an anvil, determines the result and cost of repair with them.
 * May be called triggered times when an item is placed as the client sends multiple updates.
 */
public class PrepareItemRepairEvent extends InventoryEvent implements Cancellable {
    private final HumanEntity repairer;
    private static final HandlerList handlers = new HandlerList();
    private final Block anvil;
    private final ItemStack item1;
    private final ItemStack item2;
    private ItemStack resultItem;
    private int levelCost;
    private boolean cancelled;

    public PrepareItemRepairEvent(final HumanEntity repairer, final InventoryView view, final Block anvil, final ItemStack item1, final ItemStack item2, final ItemStack resultItem, final int levelCost) {
        super(view);
        this.repairer = repairer;
        this.anvil = anvil;
        this.item1 = item1;
        this.item2 = item2;
        this.resultItem = resultItem;
        this.levelCost = levelCost;
        this.cancelled = false;
    }
    
    public HumanEntity getRepairer() {
        return repairer;
    }

    /**
     * Gets the anvil being used to repair the item
     *
     * @return the anvil block used
     */
    public Block getAnvilBlock() {
        return anvil;
    }

    /**
     * Gets the first item to be repaired
     *
     * @return ItemStack of item
     */
    public ItemStack getFirstItem() {
        return item1;
    }

    /**
     * Gets the second item used to repair
     *
     * @return ItemStack of item
     */
    public ItemStack getSecondItem() {
        return item2;
    }

    /**
     * Gets the item that will be produced by repairing.
     * Will be null if no item would normally be produced.
     *
     * @return ItemStack of item
     */
    public ItemStack getResultItem() {
        return resultItem;
    }

    /**
     * Changes the item that will be produced by repairing.
     *
     * @return ItemStack of item
     */
    public void setResultItem(ItemStack resultItem) {
        this.resultItem = resultItem;
    }

    /**
     * Get cost in exp levels of the repair
     * @return experience level cost
     */
    public int getExpLevelCost() {
        return levelCost;
    }

    /**
     * Set cost in exp levels of the repair.
     * This may not show correctly on the client as the level cost is also calculated client-side.
     * @param level - cost in levels
     */
    public void setExpLevelCost(int levelCost) {
        this.levelCost = levelCost;
    }
    
    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
