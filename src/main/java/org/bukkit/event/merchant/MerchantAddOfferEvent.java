package org.bukkit.event.merchant;

import java.util.List;

import org.apache.commons.lang.Validate;

import org.bukkit.merchant.Merchant;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.TradeOffer;

/**
 * This event is called whenever a TradeOffer is being added to a Merchant's
 * list of offers. If cancelled, the TradeOffer will not be added to the list.
 */
public class MerchantAddOfferEvent extends MerchantEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private List<TradeOffer> offers;
    private TradeOffer offerToAdd;
    private boolean cancelled = false;

    public MerchantAddOfferEvent(Merchant merchant, TradeOffer toAdd) {
        super(merchant);
        Validate.notNull(toAdd, "Cannot add a null TradeOffer!");
        this.offers = merchant.getInventory().getOffers();
        this.offerToAdd = toAdd;
    }

    /**
     * Gets an immutable copy of the list of TradeOffers the merchant currently has.
     *
     * @return a copy of the current offers from the merchant
     */
    public List<TradeOffer> getCurrentOffers() {
        return offers;
    }

    /**
     * Gets the immutable TradeOffer being added to the Merchant's current offers.
     *
     * @return the offer to add
     */
    public TradeOffer getOfferToAdd() {
        return offerToAdd;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
