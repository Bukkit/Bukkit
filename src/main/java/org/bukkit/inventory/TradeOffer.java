package org.bukkit.inventory;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.Validate;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

/**
 * Represents a Merchant's trade offer that always will have the following:
 * <ul>
 *     <li>
 *         An {@link ItemStack} being bought.
 *     </li><li>
 *         A possible second ItemStack being bought.
 *     </li><li>
 *         An ItemStack being sold
 *     </li>
 *     <li>
 *         The maxmium amount of times this trade can be used
 *     </li>
 * </ul>
 *
 * Each TradeOffer has a limited amount of uses. Once the uses have reached
 * their maximum, the TradeOffer is no longer valid for a player's trade.
 */
public final class TradeOffer implements ConfigurationSerializable {

    /**
     * Gets a builder for building a TradeOffer.
     *
     * @return a utility object for building a TradeOffer
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Gets a new builder object with this TradeOffer as a template.
     * The builder can create a new TradeOffer, but it will be a new instance
     * of the TradeOffer, not the same TradeOffer.
     *
     * @return a new Builder with the TradeOffer as a template for default
     *     values
     */
    public Builder builderOf() {
        return builder()
                .withFirstItem(firstItem)
                .withSecondItem(secondItem)
                .withResultingItem(resultingOffer)
                .withSetUses(uses)
                .withMaxUses(maxUses);
    }

    /**
     * This is a builder that allows customizing a TradeOffer before building into
     * an immutable TradeOffer.
     *
     * @see TradeOffer#builder()
     */
    public static final class Builder {
        ItemStack firstItem;
        ItemStack secondItem;
        ItemStack resultingOffer;
        int maxUses = 7;
        int uses = 0;

        Builder() {}

        /**
         * Sets the first buying item for the trade offer. The first item in a
         * TradeOffer cannot be {@link Material#AIR} or null.
         *
         * @param itemStack the first buying item
         * @return this object, for chaining
         */
        public Builder withFirstItem(ItemStack itemStack) {
            Validate.notNull(itemStack, "Cannot have a null item for a TradeOffer's first item!");
            ItemStack temp = new ItemStack(itemStack);
            Validate.isTrue(temp.getType() != Material.AIR, "Cannot have an AIR item for a TradeOffer's first item!");
            this.firstItem = temp;
            return this;
        }

        /**
         * Sets the second buying item for the trade offer. The second item in a
         * TradeOffer can be null.
         *
         * @param itemStack the second buying item
         * @return this object, for chaining
         */
        public Builder withSecondItem(ItemStack itemStack) {
            if (itemStack == null) {
                this.secondItem = null;
                return this;
            }
            ItemStack temp = new ItemStack(itemStack);
            Validate.isTrue(temp.getType() != Material.AIR, "Cannot have an AIR item for a TradeOffer's second item!");
            this.secondItem = new ItemStack(itemStack);
            return this;
        }

        /**
         * Sets the offered item for the trade offer.
         *
         * @param itemStack the resulting offered item
         * @return this object, for chaining
         */
        public Builder withResultingItem(ItemStack itemStack) {
            Validate.notNull(itemStack, "Cannot have a null item for a TradeOffer's resulting item!");
            ItemStack temp = new ItemStack(itemStack);
            Validate.isTrue(temp.getType() != Material.AIR, "Cannot have an AIR item for a TradeOffer's resulting item!");
            this.resultingOffer = temp;
            return this;
        }

        /**
         * Sets the maximum uses of the trade offer.
         *
         * @param uses the maximum uses this TradeOffer will have
         * @return this object, for chaining
         */
        public Builder withMaxUses(int uses) {
            Validate.isTrue(uses > 0, "Cannot have zero or less than zero uses!");
            this.maxUses = uses;
            return this;
        }

        /**
         * Sets the current uses of the trade offer.
         *
         * @param uses the current uses this TradeOffer will have
         * @return this object, for chaining
         */
        public Builder withSetUses(int uses) {
            Validate.isTrue(uses >= 0, "Cannot have less than zero uses!");
            this.uses = uses;
            return this;
        }

        /**
         * Gets a new TradeOffer from the current state of this builder.
         * </p>
         * To successfully build, you must have specified at least the
         * {@link #withFirstItem(ItemStack)} and
         * {@link #withResultingItem(ItemStack)}
         *
         * @return the respective TradeOffer
         * @throws IllegalStateException if the builder was not set up with the
         *         proper variables
         */
        public TradeOffer build() {
            if (firstItem == null || resultingOffer == null) {
                throw new IllegalStateException("Cannot have a null first item or resulting offer item in a TradeOffer!");
            }
            return new TradeOffer(this);
        }
    }

    private static final String FIRST_ITEM = "first-item";
    private static final String SECOND_ITEM = "second-item";
    private static final String BUYING_ITEM = "buying-item";
    private static final String CURRENT_USES = "current-uses";
    private static final String MAX_USES = "max-uses";

    private final ItemStack firstItem;
    private final ItemStack secondItem;
    private final ItemStack resultingOffer;
    private final int maxUses;
    private final int uses;

    TradeOffer(Builder builder) {
        this.firstItem = builder.firstItem;
        this.secondItem = builder.secondItem;
        this.resultingOffer = builder.resultingOffer;
        this.uses = builder.uses;
        this.maxUses = builder.maxUses;
    }

    /**
     * Gets the first item required by this offer.
     *
     * @return the first input item (or left item)
     */
    public ItemStack getFirstItem() {
        return firstItem.clone();
    }

    /**
     * Gets a copy of the second ItemStack belonging to this TradeOffer. If there
     * is no second item, returns null.
     *
     * @return the second input item (or right item)
     * @throws IllegalStateException if the item is null
     */
    public ItemStack getSecondItem() {
        if (!hasSecondItem()) {
            return null;
        } else {
            return secondItem.clone();
        }
    }

    /**
     * Gets the result item for this trade offer.
     *
     * @return the item granted by fulfilling this offer
     */
    public ItemStack getResultingOffer() {
        return resultingOffer.clone();
    }

    /**
     * Returns true if this offer has a second item requirement.
     *
     * @return True if this offer has a second item requirement
     */
    public boolean hasSecondItem() {
        return this.secondItem != null;
    }

    /**
     * Returns the current amount of times this offer has been used.
     *
     * @return the amount of times this offer has been used
     */
    public int getUses() {
        return this.uses;
    }

    /**
     * Returns the maximum times this offer can be used before this offer is
     * no longer valid.
     *
     * @return the maximum uses for this offer
     */
    public int getMaxUses() {
        return this.maxUses;
    }

    /**
     * Returns true if this offer is still usable. If false, the offer will no
     * longer be usable to players and the Merchant will not benefit from this
     * offer.
     *
     * @return true if this offer's uses have exceeded the maximum uses
     */
    public boolean hasOfferExpired() {
        return this.uses >= this.maxUses;
    }

    /**
     * @see ConfigurationSerializable
     */
    public static ConfigurationSerializable deserialize(Map<String, Object> map) {
        return builder()
                .withFirstItem((ItemStack) map.get(FIRST_ITEM))
                .withSecondItem((ItemStack) map.get(SECOND_ITEM))
                .withResultingItem((ItemStack) map.get(BUYING_ITEM))
                .withSetUses((Integer) map.get(CURRENT_USES))
                .withMaxUses((Integer) map.get(MAX_USES))
                .build();
    }

    @Override
    public Map<String, Object> serialize() {
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object>builder()
                .put(FIRST_ITEM, firstItem.clone())
                .put(BUYING_ITEM, resultingOffer.clone())
                .put(CURRENT_USES, uses)
                .put(MAX_USES, maxUses);
        if (secondItem != null) {
            builder.put(SECOND_ITEM, secondItem.clone());
        }
        return builder.build();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int hash = 1;
        hash = hash * PRIME + firstItem.hashCode();
        hash = hash * PRIME + (secondItem == null ? 0 : secondItem.hashCode());
        hash = hash * PRIME + resultingOffer.hashCode();
        hash = hash * PRIME + uses;
        hash = hash * PRIME + maxUses;
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof TradeOffer)) {
            return false;
        }
        TradeOffer toCheck = (TradeOffer) o;
        return (this.firstItem.equals(toCheck.firstItem))
                && (this.resultingOffer.equals(toCheck.resultingOffer))
                && (this.hasSecondItem() == toCheck.hasSecondItem())
                && (this.hasSecondItem() ? toCheck.hasSecondItem() && this.secondItem.equals(toCheck.secondItem) : !toCheck.hasSecondItem())
                && (this.getMaxUses() == toCheck.getMaxUses());
    }
}
