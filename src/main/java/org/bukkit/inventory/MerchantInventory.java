package org.bukkit.inventory;

import java.util.List;

import org.bukkit.merchant.Merchant;

/**
 * Represents the inventory of a merchant which contains a list of {@link TradeOffer}s.
 */
public interface MerchantInventory extends Inventory {

    /**
     * Gets the merchant belonging to this inventory
     *
     * @return the merchant
     */
    public Merchant getMerchant();

    /**
     * Gets a copy of the offers presented to a Player.
     *
     * @return a copied list of TradeOffers being given to the Player
     */
    public List<TradeOffer> getOffers();

    /**
     * Adds the given TradeOffer to this Inventory's list of trade
     * offers.
     *
     * @param offer to add
     */
    public void addOffer(TradeOffer offer);

    /**
     * Removes the specified offer if it is contained within the list of
     * offers by this inventory.
     *
     * @param offer to remove
     */
    public void removeOffer(TradeOffer offer);
}
