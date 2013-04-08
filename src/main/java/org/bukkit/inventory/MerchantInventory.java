package org.bukkit.inventory;

import org.bukkit.entity.Merchant;

public interface MerchantInventory extends Inventory {

    /**
     * Get the merchant that this inventory is registered to
     *
     * @return the merchant that this inventory is registered to
     */
    public Merchant getMerchant();
}
