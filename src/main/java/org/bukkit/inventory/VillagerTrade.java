package org.bukkit.inventory;

/**
 * Represents one of the villager's trading pages.
 */
public class VillagerTrade {
    private ItemStack result;
    private ItemStack leftInput;
    private ItemStack rightInput;
    private int useCount = 0;
    private int maxUses = 7;
    
    /**
     * Creates a VillagerTrade with the specified result.
     * 
     * @param result The item you want to give the player in return for the trade.
     * @param leftInput The left input item required for the completed trade.
     */
    public VillagerTrade(ItemStack result, ItemStack leftInput) {
        this.result = new ItemStack(result);
        this.leftInput = new ItemStack(leftInput);
    }
    
    /**
     * Creates a VillagerTrade with the specified result.
     * 
     * @param result The item you want to give the player in return for the trade.
     * @param leftInput The left input item required for the completed trade.
     * @param maxUses The maximum number of times this trade can be used before becoming locked.
     */
    public VillagerTrade(ItemStack result, ItemStack leftInput, int maxUses) {
        this.result = new ItemStack(result);
        this.leftInput = new ItemStack(leftInput);
        this.maxUses = maxUses;
    }
    
    /**
     * Creates a VillagerTrade with the specified result.
     * 
     * @param result The item you want to give the player in return for the trade.
     * @param leftInput The left input item required for the completed trade.
     * @param rightInput The right input item required for the completed trade.
     */
    public VillagerTrade(ItemStack result, ItemStack leftInput, ItemStack rightInput) {
        this.result = new ItemStack(result);
        this.leftInput = new ItemStack(leftInput);
        this.rightInput = new ItemStack(rightInput);
    }
    
    /**
     * Creates a VillagerTrade with the specified result.
     * 
     * @param result The item you want to give the player in return for the trade.
     * @param leftInput The left input item required for the completed trade.
     * @param rightInput The right input item required for the completed trade.
     * @param maxUses The maximum number of times this trade can be used before becoming locked.
     */
    public VillagerTrade(ItemStack result, ItemStack leftInput, ItemStack rightInput, int maxUses) {
        this.result = new ItemStack(result);
        this.leftInput = new ItemStack(leftInput);
        this.rightInput = new ItemStack(rightInput);
        this.maxUses = maxUses;
    }
    
    /**
     * Set the left input item required to complete the trade.
     * 
     * @param leftInput The left input item.
     */
    public void setLeftInput(ItemStack leftInput) {
        this.leftInput = new ItemStack(leftInput);
    }
    
    /**
     * Set the right input item required to complete the trade.
     * 
     * @param rightInput The right input item.
     */
    public void setRightInput(ItemStack rightInput) {
        this.rightInput = new ItemStack(rightInput);
    }
    
    /**
     * Set the current number of uses.
     * 
     * @param uses The new use count.
     */
    public void setUseCount(int uses) {
        this.useCount = uses;
    }
    
    /**
     * Set the maximum number of uses.
     * 
     * @param maxUses The new max use count.
     */
    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }
    
    /**
     * Get the current number of trade uses.
     * 
     * @return The current number of trades made with this recipe.
     */
    public int getUseCount() {
        return useCount;
    }
    
    /**
     * Get the maximum number of trade uses.
     * 
     * @return The maximum number of trades that can be made with this recipe.
     */
    public int getMaxUses() {
        return maxUses;
    }
    
    /**
     * Returns the left input item required to complete the trade.
     * 
     * @return The left input item
     */
    public ItemStack getLeftInput() {
        return leftInput;
    }
    
    /**
     * Returns the right input item required to complete the trade.
     * 
     * @return The right input item
     */
    public ItemStack getRightInput() {
        return rightInput;
    }
    
    /**
     * Returns the resulting item for completing the trade.
     * 
     * @return The result of the trade
     */
    public ItemStack getResult() {
        return result;
    }
    
    /**
     * Checks if the two provided ItemStacks are vaild to complete the trade
     * 
     * @param leftInput Input for the left side
     * @param rightInput Input for the right side
     * @return True if this completes the trade, false otherwise.
     */
    public boolean completesTrade(ItemStack leftInput, ItemStack rightInput) {
        return compareNulls(leftInput, this.leftInput) && compareNulls(rightInput, this.rightInput);
    }
    
    /**
     * Compare two trades to check equality
     * 
     * @param compareTradeObj The trade to compare this one to.
     * @return True for trades that have the same inputs and output, false otherwise.
     */
    @Override
    public boolean equals(Object compareTradeObj) {
        if (compareTradeObj instanceof VillagerTrade) {
            VillagerTrade compareTrade = (VillagerTrade) compareTradeObj;
            
            return compareNulls(leftInput, compareTrade.leftInput) && compareNulls(rightInput, compareTrade.rightInput) &&
                    compareNulls(result, compareTrade.result);
        } else {
            return false;
        }
    }
    
    /**
     * Create a unique hashcode that represents the data in this trade.
     * @return The hashcode of this object
     */
    @Override
    public int hashCode() {
        // I am lost on this.
        return super.hashCode();
    }
    
    /*
     * Compare two ItemStacks which may be null for equality
     */
    private boolean compareNulls(ItemStack stack1, ItemStack stack2) {
        if (stack1 == null) {
            if (stack2 == null) {
                return true;
            } else {
                return false;
            }
        } else if (stack2 == null) {
            return false;
        } else {
            return stack1.equals(stack2);
        }
    }
    
}
