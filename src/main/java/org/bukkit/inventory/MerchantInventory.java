package org.bukkit.inventory;

public interface MerchantInventory extends Inventory {
    /**
     * Returns a copy of the MerchantOffers object that performs all operations on
     * the trades provided.
     *
     * @return The merchant offers
     */
    public MerchantOffers getOffers();

    /**
     * Returns a matching offer for the two input stacks, if none are found
     * null is returned.
     *
     * @return A trade that matches the current inventory or null if no match is found.
     */
    public VillagerTrade getMatchingOffer();
}
