package org.bukkit.event.merchant;

import org.apache.commons.lang.Validate;

import org.bukkit.event.Event;
import org.bukkit.merchant.Merchant;

/**
 * Represents a Merchant related event.
 */
public abstract class MerchantEvent extends Event {

    protected Merchant merchant;

    MerchantEvent(Merchant merchant) {
        Validate.notNull(merchant, "Cannot have a null Merchant!");
        this.merchant = merchant;
    }

    /**
     * Gets the merchant involved with this event.
     *
     * @return The merchant
     */
    public Merchant getMerchant() {
        return this.merchant;
    }
}
