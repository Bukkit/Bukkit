package org.bukkit.inventory;

public interface MerchantInventory extends Inventory {
    /**
     * Returns a copy of the MerchantOffers object that performs all operations on
     * the trades provided.
     * 
     * @return The merchant offers
     */
    public MerchantOffers getOffers();
}
