package org.bukkit.inventory;

import java.util.List;

import org.bukkit.entity.Villager;

public interface MerchantInventory extends Inventory {
    /**
     * Returns a list of all VillagerTrades being offered by this merchant.
     *
     * @return The merchant offers
     */
    public List<VillagerTrade> getTrades();

    /**
     * Change the list of offered trades to the provided list.
     *
     * @param newTrades
     */
    public void setTrades(List<VillagerTrade> newTrades);

    /**
     * Get whichever VillagerTrade is currently being used to display the result
     * item (slot 2).
     *
     * @return A trade that matches the current inventory or null if no match is
     *         found.
     */
    public VillagerTrade getMatchingTrade();

    /**
     * Get the Villager this MerchantInventory belongs to.
     *
     * @return the villager
     */
    public Villager getHolder();

    /**
     * Get the item in the left slot. Identical to getItem(0).
     *
     * @return left slot item
     */
    public ItemStack getLeftItem();

    /**
     * Get the item in the right slot. Identical to getItem(1).
     *
     * @return right slot item
     */
    public ItemStack getRightItem();

    /**
     * Get the item in the result slot. Identical to getItem(2).
     *
     * @return result slot item
     * @see #getMatchingTrade()
     */
    public ItemStack getResultItem();
}
