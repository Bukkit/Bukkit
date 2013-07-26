package org.bukkit.inventory;

public interface MerchantInventory extends Inventory {
    /**
     * Returns the number of pages currently exposed to the player.
     * 
     * @return The number of trade pages exposed to the player.
     */
    public int getExposedPageCount();
    
    /**
     * Returns if the given page is locked.
     *
     * @param pageNumber The page number to check.
     * @return True if the page is locked, false if it is not.
     */
    public boolean isPageLocked(int pageNumber);
    
    /**
     * Returns the specified trading page. A value of null is given for an
     * invalid page number.
     * 
     * @param pageNumber The trading page to return
     * @return A {@link VillagerTrade} representing the trading page.
     */
    public VillagerTrade getTrade(int pageNumber);
}
