package org.bukkit.inventory;

import java.util.List;

public interface MerchantInventory extends Inventory {
    /**
     * Returns the number of offers currently available.
     * 
     * @return offers available
     */
    public int getOfferCount();
    
    /**
     * Returns a copy of the current offer list. Modifications have to
     * be added by calling the correct method.
     * 
     * @return list of all offers
     */
    public List<VillagerTrade> getOffers();
    
    /**
     * Returns a matching offer for the two input stacks, if none are found
     * null is returned.
     * 
     * @param leftInput The left input slot's item.
     * @param rightInput The right input slot's item.
     */
    public VillagerTrade getMatchingOffer(ItemStack leftInput, ItemStack rightInput);
}
