package org.bukkit.merchant;

import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.MerchantInventory;

/**
 * Represents a Merchant that is able to trade items with Players.
 */
public interface Merchant extends InventoryHolder {

    /**
     * Gets this Merchant's inventory.
     *
     * @return he inventory of the merchant, that also contains all the
     *     TradeOffers that can be offered to a player
     */
    public MerchantInventory getInventory();

}
