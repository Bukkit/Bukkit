package org.bukkit.inventory;

import java.util.List;


public interface MerchantOffers {
    /**
     * Returns the number of offers currently available.
     *
     * @return offers available
     */
    public int getTradeCount();

    /**
     * Returns a copy of the current offer list. Modifications have to
     * be added by calling the correct method.
     *
     * @return list of all offers
     */
    public List<VillagerTrade> getTrades();

    /**
     * Returns a matching offer for the two input stacks, if none are found
     * null is returned.
     *
     * @param leftInput The left input slot's item.
     * @param rightInput The right input slot's item.
     * @return A trade that matches the provided items or null for no match.
     */
    public VillagerTrade getMatchingOffer(ItemStack leftInput, ItemStack rightInput);

    /**
     * Add a new trade to the list of trades
     *
     * @param tradeRecipe - the {@link VillagerTrade} object representing the trading recipe
     */
    public void addTrade(VillagerTrade tradeRecipe);

    /**
     * Clears all the trades
     */
    public void clearTrades();
}
