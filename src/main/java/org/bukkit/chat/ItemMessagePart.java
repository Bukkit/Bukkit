package org.bukkit.chat;

import org.apache.commons.lang.Validate;
import org.bukkit.Utility;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * Represents a part of a {@link RichMessage} that shows an {@link ItemStack}
 * as tooltip when the player hover the text of this {@link RichMessagePart}.
 * <p>
 * Note that the tooltip is fixed to the {@link ItemStack} and cannot be
 * altered independently.
 */
public class ItemMessagePart extends RichMessagePart {

    private ItemStack item;

    /**
     * Builds an ItemMessagePart with an {@link ItemStack} and the
     * ItemStack's name for text.
     *
     * @param item the {@link ItemStack} that should be displayed
     */
    public ItemMessagePart(ItemStack item) {
        this(item, (String) null);
    }

    /**
     * Builds an ItemMessagePart with an {@link ItemStack} and the
     * ItemStack's name for text.
     *
     * @param item the {@link ItemStack} that should be displayed
     * @param clickAction the action executed when the player click on this
     *     {@link RichMessagePart}
     */
    public ItemMessagePart(ItemStack item, ClickAction clickAction) {
        this(item, (String) null, clickAction);
    }

    /**
     * Builds an ItemMessagePart with an {@link ItemStack} and the provided
     * text.
     * <p>
     * Note that the text can contain {@link org.bukkit.ChatColor} codes.
     *
     * @param item the {@link ItemStack} that should be displayed
     * @param customText the text of this {@link RichMessagePart}
     */
    public ItemMessagePart(ItemStack item, String customText) {
        super(Type.ITEM, customText);
        Validate.notNull(item, "ItemMessagePart's item can't be null");
        this.item = item;
    }

    /**
     * Builds an ItemMessagePart with an {@link ItemStack}, the provided text
     * and a {@link ClickAction}.
     * <p>
     * Note that the text can contain {@link org.bukkit.ChatColor} codes.
     *
     * @param item the {@link ItemStack} that should be displayed
     * @param customText the text of this {@link RichMessagePart}
     * @param clickAction the action executed when the player click on this
     *     {@link RichMessagePart}
     */
    public ItemMessagePart(ItemStack item, String customText, ClickAction clickAction) {
        super(Type.ITEM, customText, clickAction);
        Validate.notNull(item, "ItemMessagePart's item can't be null");
        this.item = item;
    }

    /**
     * Builds an ItemMessagePart with an {@link ItemStack} and the provided
     * localized text.
     *
     * @param item the {@link ItemStack} that should be displayed
     * @param customText the localized text of this {@link RichMessagePart}
     */
    public ItemMessagePart(ItemStack item, LocalizedText customText) {
        super(Type.ITEM, customText);
        Validate.notNull(item, "ItemMessagePart's item can't be null");
        this.item = item;
    }

    /**
     * Builds an ItemMessagePart with an {@link ItemStack}, the provided
     * localized text and a {@link ClickAction}.
     *
     * @param item the {@link ItemStack} that should be displayed
     * @param customText the localized text of this {@link RichMessagePart}
     * @param clickAction the action executed when the player click on this
     *     {@link RichMessagePart}
     */
    public ItemMessagePart(ItemStack item, LocalizedText customText, ClickAction clickAction) {
        super(Type.ITEM, customText, clickAction);
        Validate.notNull(item, "ItemMessagePart's item can't be null");
        this.item = item;
    }

    /**
     * Gets the item for this ItemMessagePart.
     * <p>
     * Note that editing the item will directly affect this RichMessagePart,
     * but it will not affect any message already sent to the client.
     *
     * @return the item for this ItemStackPart
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * Sets the item for this ItemMessagePart.
     * <p>
     * Note that editing the item will directly affect this RichMessagePart,
     * but it will not affect any message already sent to the client.
     *
     * @param item the new item for this ItemStackPart
     */
    public void setItem(ItemStack item) {
        Validate.notNull(item, "ItemMessagePart's item can't be null");
        this.item = item;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + item.hashCode();
        return result;
    }

    @Utility
    public Map<String, Object> serialize() {
        Map<String, Object> result = super.serialize();
        result.put("item", item.serialize());
        return result;
    }
}
