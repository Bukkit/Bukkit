package org.bukkit.inventory;

/**
 * Represents one of the villager's trading pages.
 */
public class VillagerTrade {
    private ItemStack result;
    private ItemStack leftInput;
    private ItemStack rightInput;
    private int useCount;
    private int maxUses;

    /**
     * Creates a VillagerTrade with the specified result.
     *
     * @param result The item you want to give the player in return for the trade.
     * @param leftInput The left input item required for the completed trade.
     */
    public VillagerTrade(ItemStack result, ItemStack leftInput) {
        this(result, leftInput, null, 7, 0);
    }

    /**
     * Creates a VillagerTrade with the specified result.
     *
     * @param result The item you want to give the player in return for the trade.
     * @param leftInput The left input item required for the completed trade.
     * @param maxUses The maximum number of times this trade can be used before becoming locked.
     */
    public VillagerTrade(ItemStack result, ItemStack leftInput, int maxUses) {
        this(result, leftInput, null, maxUses, 0);
    }

    /**
     * Creates a VillagerTrade with the specified result.
     *
     * @param result The item you want to give the player in return for the trade.
     * @param leftInput The left input item required for the completed trade.
     * @param maxUses The maximum number of times this trade can be used before becoming locked.
     * @param useCount How many times the trade has already been used.
     */
    public VillagerTrade(ItemStack result, ItemStack leftInput, int maxUses, int useCount) {
        this(result, leftInput, null, maxUses, useCount);
    }

    /**
     * Creates a VillagerTrade with the specified result.
     *
     * @param result The item you want to give the player in return for the trade.
     * @param leftInput The left input item required for the completed trade.
     * @param rightInput The right input item required for the completed trade.
     */
    public VillagerTrade(ItemStack result, ItemStack leftInput, ItemStack rightInput) {
        this(result, leftInput, rightInput, 7, 0);
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
        this(result, leftInput, rightInput, maxUses, 0);
    }

    /**
     * Creates a VillagerTrade with the specified result.
     *
     * @param result The item you want to give the player in return for the trade.
     * @param leftInput The left input item required for the completed trade.
     * @param rightInput The right input item required for the completed trade.
     * @param maxUses The maximum number of times this trade can be used before becoming locked.
     * @param useCount How many times the trade has already been used.
     */
    public VillagerTrade(ItemStack result, ItemStack leftInput, ItemStack rightInput, int maxUses, int useCount) {
        this.result = new ItemStack(result);
        this.leftInput = new ItemStack(leftInput);
        this.rightInput = new ItemStack(rightInput);
        this.maxUses = maxUses;
        this.useCount = useCount;
    }

    /**
     * Sets the left input item required to complete the trade.
     *
     * @param leftInput The left input item.
     */
    public void setLeftInput(ItemStack leftInput) {
        this.leftInput = new ItemStack(leftInput);
    }

    /**
     * Sets the right input item required to complete the trade.
     *
     * @param rightInput The right input item.
     */
    public void setRightInput(ItemStack rightInput) {
        this.rightInput = new ItemStack(rightInput);
    }

    /**
     * Sets the number of times this trade has been used.
     *
     * @param uses The new use count.
     */
    public void setUseCount(int uses) {
        this.useCount = uses;
    }

    /**
     * Sets the maximum number of uses.
     *
     * @param maximumUses The new max use count.
     */
    public void setMaximumUses(int maximumUses) {
        this.maxUses = maximumUses;
    }

    /**
     * Gets how many times this trade has been used.
     *
     * @return The number of trades that have been made with this recipe.
     */
    public int getUses() {
        return useCount;
    }

    /**
     * Gets the maximum number of trade uses.
     *
     * @return The maximum number of trades that can be made with this recipe.
     */
    public int getMaximumUses() {
        return maxUses;
    }

    /**
     * Gets the left input item required to complete the trade.
     *
     * @return The left input item
     */
    public ItemStack getLeftInput() {
        return leftInput;
    }

    /**
     * Gets the right input item required to complete the trade.
     *
     * @return The right input item
     */
    public ItemStack getRightInput() {
        return rightInput;
    }

    /**
     * Gets the resulting item for completing the trade.
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
