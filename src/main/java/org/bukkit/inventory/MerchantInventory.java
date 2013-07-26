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
}
