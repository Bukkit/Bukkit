package org.bukkit.inventory;

import java.util.List;

import org.bukkit.entity.Villager;

public interface MerchantInventory extends Inventory {
    /**
     * Gets a list of all VillagerTrades being offered by this merchant.
     *
     * @return A list of all availible merchant offers
     */
    public List<VillagerTrade> getTrades();

    /**
     * Sets the list of offered trades to the provided list.
     *
     * @param newTrades The trades that will replace what the villager previously 
     *                  offered
     */
    public void setTrades(List<VillagerTrade> newTrades);

    /**
     * Gets the matching VillagerTrade object for the current input items
     *
     * @return A trade that matches the current inventory or null if no match is
     *         found.
     */
    public VillagerTrade getMatchingTrade();

    /**
     * Gets the Villager this MerchantInventory belongs to.
     *
     * @return the villager entity
     */
    public Villager getHolder();

    /**
     * Gets the item in the left input slot.
     *
     * @return left slot item
     */
    public ItemStack getLeftItem();

    /**
     * Gets the item in the right input slot.
     *
     * @return right slot item
     */
    public ItemStack getRightItem();

    /**
     * Gets the item in the result slot.
     *
     * @return result slot item
     * @see #getMatchingTrade()
     */
    public ItemStack getResultItem();
}
