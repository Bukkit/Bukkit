package org.bukkit.inventory;

/**
 * Represents one of the villager's trading pages.
 */
public class VillagerTrade {
    private ItemStack result;
    private ItemStack leftCost;
    private ItemStack rightCost;
    
    /**
     * Creates a VillagerTrade with the specified result. To set the two cost
     * slots you will need to call the appropriate methods.
     * 
     * @param result The item you want to give the player in return for the trade.
     * @see VillagerTrade#setLeftCost(ItemStack)
     * @see VillagerTrade#setRightCost(ItemStack)
     */
    public VillagerTrade(ItemStack result) {
        this.result = new ItemStack(result);
    }
    
    /**
     * Set the left input item required to complete the trade.
     * 
     * @param leftCost The left cost item.
     */
    public void setLeftCost(ItemStack leftCost) {
        this.leftCost = new ItemStack(leftCost);
    }
    
    /**
     * Set the right input item required to complete the trade.
     * 
     * @param rightCost The right cost item.
     */
    public void setRightCost(ItemStack rightCost) {
        this.rightCost = new ItemStack(rightCost);
    }
    
    /**
     * Returns the left input item required to complete the trade.
     * 
     * @return The left cost
     */
    public ItemStack getLeftCost() {
        return leftCost;
    }
    
    /**
     * Returns the right input item required to complete the trade.
     * 
     * @return The right cost
     */
    public ItemStack getRightCost() {
        return rightCost;
    }
    
    /**
     * Returns the resulting item for completing the trade.
     * 
     * @return The result of the trade
     */
    public ItemStack getResult() {
        return result;
    }
    
}
