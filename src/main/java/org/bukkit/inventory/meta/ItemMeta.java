package org.bukkit.inventory.meta;

import java.util.List;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.enchantments.Enchantment;

/**
 * This type represents the storage mechanism for auxiliary item data.
 * <p>
 * An implementation will handle the creation and application for ItemMeta.
 * This class should not be implemented by a plugin in a live environment.
 */
public interface ItemMeta extends Cloneable, ConfigurationSerializable {

    /**
     * Checks for existence of a display name.
     *
     * @return true if this has a display name
     */
    boolean hasDisplayName();

    /**
     * Gets the display name that is set.
     * <p>
     * Plugins should check that hasDisplayName() returns <code>true</code>
     * before calling this method.
     *
     * @return the display name that is set
     */
    String getDisplayName();

    /**
     * Sets the display name.
     *
     * @param name the name to set
     */
    void setDisplayName(String name);

    /**
     * Checks for existence of lore.
     *
     * @return true if this has lore
     */
    boolean hasLore();

    /**
     * Gets the lore that is set.
     * <p>
     * Plugins should check if hasLore() returns <code>true</code> before
     * calling this method.
     * 
     * @return a list of lore that is set
     */
    List<String> getLore();

    /**
     * Sets the lore for this item. 
     * Removes lore when given null.
     *
     * @param lore the lore that will be set
     */
    void setLore(List<String> lore);

    /**
     * Check to see if this Item has any custom or unknown data.
     * <p>
     * This is normally data that has been put here by a Plugin.
     *
     * @return true if this Item has any custom data
     */
    boolean hasCustomData();

    /**
     * Retrieve a read/write accessor fo this Item's custom data.
     * <p>
     * Changes made to this object will affect the Item's custom
     * data directly.
     * <p>
     * These objects should not be retained, it is best to get a
     * new reference each time you want to modify the item, since
     * items may get copied as they move around or spawn and get
     * picked up.
     * <p>
     * If this item has no custom data, calling this will initialize
     * custom data for the item.
     * <p>
     * Use hasData for an efficient pre-check if you
     * only getting data to look for a specific key or value.
     *
     * @return Access to the Item's custom data
     */
    ConfigurationSection getCustomData();

    /**
     * Checks for the existence of any enchantments.
     *
     * @return true if an enchantment exists on this meta
     */
    boolean hasEnchants();

    /**
     * Checks for existence of the specified enchantment.
     *
     * @param ench enchantment to check
     * @return true if this enchantment exists for this meta
     */
    boolean hasEnchant(Enchantment ench);

    /**
     * Checks for the level of the specified enchantment.
     *
     * @param ench enchantment to check
     * @return The level that the specified enchantment has, or 0 if none
     */
    int getEnchantLevel(Enchantment ench);

    /**
     * Returns a copy the enchantments in this ItemMeta.<br />
     * Returns an empty map if none.
     *
     * @return An immutable copy of the enchantments
     */
    Map<Enchantment, Integer> getEnchants();

    /**
     * Adds the specified enchantment to this item meta.
     *
     * @param ench Enchantment to add
     * @param level Level for the enchantment
     * @param ignoreLevelRestriction this indicates the enchantment should be
     *     applied, ignoring the level limit
     * @return true if the item meta changed as a result of this call, false
     *     otherwise
     */
    boolean addEnchant(Enchantment ench, int level, boolean ignoreLevelRestriction);

    /**
     * Removes the specified enchantment from this item meta.
     *
     * @param ench Enchantment to remove
     * @return true if the item meta changed as a result of this call, false
     *     otherwise
     */
    boolean removeEnchant(Enchantment ench);

   /**
    * Checks if the specified enchantment conflicts with any enchantments in
    * this ItemMeta.
    *
    * @param ench enchantment to test
    * @return true if the enchantment conflicts, false otherwise
    */
    boolean hasConflictingEnchant(Enchantment ench);

    @SuppressWarnings("javadoc")
    ItemMeta clone();
}
