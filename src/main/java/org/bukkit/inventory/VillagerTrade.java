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

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((leftInput == null) ? 0 : leftInput.hashCode());
		result = prime * result + maxUses;
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + ((rightInput == null) ? 0 : rightInput.hashCode());
		result = prime * result + useCount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof VillagerTrade)) {
			return false;
		}
		VillagerTrade other = (VillagerTrade) obj;
		if (leftInput == null) {
			if (other.leftInput != null) {
				return false;
			}
		} else if (!leftInput.equals(other.leftInput)) {
			return false;
		}
		if (maxUses != other.maxUses) {
			return false;
		}
		if (result == null) {
			if (other.result != null) {
				return false;
			}
		} else if (!result.equals(other.result)) {
			return false;
		}
		if (rightInput == null) {
			if (other.rightInput != null) {
				return false;
			}
		} else if (!rightInput.equals(other.rightInput)) {
			return false;
		}
		if (useCount != other.useCount) {
			return false;
		}
		return true;
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
