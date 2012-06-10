package org.bukkit.block;

import org.bukkit.inventory.InventoryHolder;

public interface DoubleChest extends Chest {

    public InventoryHolder getLeftSide();

    public InventoryHolder getRightSide();
    
}
