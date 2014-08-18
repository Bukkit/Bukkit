package org.bukkit.chat;

import org.bukkit.Utility;

import java.util.Arrays;
import java.util.Map;

/**
 * Represents a customizable part of a {@link RichMessage}.
 * <p>
 * This is what you should use if you do not need an
 * {@link org.bukkit.inventory.ItemStack} nor an
 * {@link org.bukkit.Achievement} as tooltip.
 * This implementation of {@link RichMessagePart} allows you to use your own
 * tooltip instead.
 */
public class CustomMessagePart extends RichMessagePart {

    private String[] tooltipLines;

    /**
     * Builds a CustomMessagePart from a text and an optional tooltip.
     * <p>
     * Note that the text can contain {@link org.bukkit.ChatColor} codes.
     *
     * @param text the text of this CustomMessagePart
     * @param tooltipLines the text lines shown when the player hover on this
     *     CustomMessagePart
     */
    public CustomMessagePart(String text, String... tooltipLines) {
        super(Type.CUSTOM, text);
        this.tooltipLines = tooltipLines == null ? new String[0] : tooltipLines;
    }

    /**
     * Builds a CustomMessagePart from a text, a {@link ClickAction} and an
     * optional tooltip.
     * <p>
     * Note that the text can contain {@link org.bukkit.ChatColor} codes.
     *
     * @param text the text of this CustomMessagePart
     * @param clickAction the action executed when the player click on this
     *     CustomMessagePart
     * @param tooltipLines the text lines shown when the player hover on this
     *     CustomMessagePart
     */
    public CustomMessagePart(String text, ClickAction clickAction, String... tooltipLines) {
        super(Type.CUSTOM, text, clickAction);
        this.tooltipLines = tooltipLines == null ? new String[0] : tooltipLines;
    }

    /**
     * Builds a CustomMessagePart from a localized text and an optional
     * tooltip.
     *
     * @param localizedText the localized text of this CustomMessagePart
     * @param tooltipLines the text lines shown when the player hover on this
     *     CustomMessagePart
     */
    public CustomMessagePart(LocalizedText localizedText, String... tooltipLines) {
        super(Type.CUSTOM, localizedText);
        this.tooltipLines = tooltipLines == null ? new String[0] : tooltipLines;
    }

    /**
     * Builds a CustomMessagePart from a localized text, a
     * {@link ClickAction} and an optional tooltip.
     *
     * @param localizedText the localized text of this CustomMessagePart
     * @param clickAction the action executed when the player click on this
     *     CustomMessagePart
     * @param tooltipLines the text lines shown when the player hover on this
     *     CustomMessagePart
     */
    public CustomMessagePart(LocalizedText localizedText, ClickAction clickAction, String... tooltipLines) {
        super(Type.CUSTOM, localizedText, clickAction);
        this.tooltipLines = tooltipLines == null ? new String[0] : tooltipLines;
    }

    /**
     * Gets the tooltip lines associated with this CustomMessagePart. The
     * tooltip lines will be shown when the player hover on the text
     * represented by this {@link CustomMessagePart}.
     * <p>
     * Note that the tooltip can contain {@link org.bukkit.ChatColor} codes.
     * This method will never return null and will return an empty array
     * instead.
     *
     * @return the tooltip lines associated with this CustomMessagePart
     */
    public String[] getTooltipLines() {
        return tooltipLines;
    }

    /**
     * Sets the tooltip lines associated with this CustomMessagePart. The
     * tooltip lines will be shown when the player hover on the text
     * represented by this {@link CustomMessagePart}.
     * <p>
     * Note that the tooltip can contain {@link org.bukkit.ChatColor} codes.
     * Providing a <code>null</code> value to this method will actually set
     * the tooltip to an empty array.
     *
     * @param tooltipLines the new tooltip lines associated with this
     *     CustomMessagePart
     */
    public void setTooltipLines(String[] tooltipLines) {
        this.tooltipLines = tooltipLines == null ? new String[0] : tooltipLines;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tooltipLines != null ? Arrays.hashCode(tooltipLines) : 0);
        return result;
    }

    @Utility
    public Map<String, Object> serialize() {
        Map<String, Object> result = super.serialize();

        if (tooltipLines.length > 0) {
            result.put("tooltip", tooltipLines);
        }

        return result;
    }
}
