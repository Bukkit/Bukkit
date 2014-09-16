package org.bukkit.chat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.ImmutableMap;

/**
 * Represents a part of a {@link Message}, a simple or localized String which
 * may have a {@link Click} and/or a {@link Hover} attached to it.
 */
@SerializableAs("ChatPart")
public final class Part implements ConfigurationSerializable {

    /**
     * Builds a message part consisting of text. The text may contain
     * {@link ChatColor}s as chars.
     * 
     * @param text the text that should be displayed by this part
     * @return the part consisting of the given text
     */
    public static Part of(String text) {
        Validate.notNull(text, "text can't be null");
        return new Part().setText(text);
    }

    /**
     * Builds a message part consisting of a localized text and localized text
     * parameters, if any.
     * 
     * @param id the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return the part consisting of the given localized text
     */
    public static Part ofLocalized(String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setLocalizedText(id, parameters);
    }

    /**
     * Builds a message part consisting of text, which shows another text when
     * hovered. The text and the hover text may contain {@link ChatColor}s as
     * chars.
     * 
     * @param text the text that should be displayed by this part
     * @param hoverText the text that should be displayed by this part when
     *        hovered, cannot be null or empty
     * @return the hoverable part consisting of the given text and the hover
     *         text
     */
    public static Part of(String text, String... hoverText) {
        Validate.notNull(text, "text can't be null");
        Validate.notEmpty(hoverText, "hoverText can't be empty");
        return new Part().setText(text).setHover(Hover.of(hoverText));
    }

    /**
     * Builds a message part consisting of a localized text and localized text
     * parameters, if any, which shows another text when hovered and executes
     * the given {@link Click}action when clicked. The hover text may contain
     * {@link ChatColor}s as chars.
     * 
     * @param hoverText the text that should be displayed by this part when
     *        hovered, cannot be null or empty
     * @param id the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return the hoverable part consisting of the given localized text and the
     *         hover text
     */
    public static Part ofLocalized(String[] hoverText, String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setHover(Hover.of(hoverText)).setLocalizedText(id, parameters);
    }

    /**
     * Builds a message part consisting of an {@link ItemStack}. The name of the
     * item can be hovered to show the item details.
     * 
     * @param item the item that should be displayed by this part, cannot be
     *        null
     * @return the hoverable part consisting of the given item
     */
    public static Part of(ItemStack item) {
        Validate.notNull(item, "item can't be null");
        return new Part().setHover(Hover.of(item));
    }

    /**
     * Builds a message part consisting of text, which shows the given item when
     * hovered. The text may contain {@link ChatColor}s as chars.
     * 
     * @param item the item that should be displayed by this part when hovered,
     *        cannot be null
     * @param text the text that should be displayed by this part
     * @return the hoverable part consisting of the given text and the hover
     *         item
     */
    public static Part of(ItemStack item, String text) {
        Validate.notNull(text, "text can't be null");
        return new Part().setHover(Hover.of(item)).setText(text);
    }

    /**
     * Builds a message part consisting of localized text, which shows the given
     * item when hovered.
     * 
     * @param item the item that should be displayed by this part when hovered,
     *        cannot be null
     * @param id the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return the hoverable part consisting of the given localized text and the
     *         hover item
     */
    public static Part ofLocalized(ItemStack item, String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setHover(Hover.of(item)).setLocalizedText(id, parameters);
    }

    /**
     * Builds a message part consisting of an {@link Achievement}. The name of
     * the achievement can be hovered to show the achievement details.
     * 
     * @param achievement the achievement that should be displayed by this part,
     *        cannot be null
     * @return the hoverable part consisting of the given achievement
     */
    public static Part of(Achievement achievement) {
        Validate.notNull(achievement, "achievement can't be null");
        return new Part().setHover(Hover.of(achievement));
    }

    /**
     * Builds a message part consisting of text, which shows the given
     * achievement when hovered. The text may contain {@link ChatColor}s as
     * chars.
     * 
     * @param achievement the achievement that should be displayed by this part
     *        when hovered, cannot be null
     * @param text the text that should be displayed by this part
     * @return the hoverable part consisting of the given text and the hover
     *         achievement
     */
    public static Part of(Achievement achievement, String text) {
        Validate.notNull(text, "text can't be null");
        return new Part().setHover(Hover.of(achievement)).setText(text);
    }

    /**
     * Builds a message part consisting of localized text, which shows the given
     * achievement when hovered.
     * 
     * @param achievement the achievement that should be displayed by this part
     *        when hovered, cannot be null
     * @param id the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return the hoverable part consisting of the given localized text and the
     *         hover achievement
     */
    public static Part ofLocalized(Achievement achievement, String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setHover(Hover.of(achievement)).setLocalizedText(id, parameters);
    }

    /**
     * Builds a message part consisting of text, which executes the given
     * {@link Click}action when clicked. The text may contain {@link ChatColor}s
     * as chars.
     * 
     * @param clickAction the action that should be performed when the text is
     *        clicked
     * @param text the text that should be displayed by this part
     * @return the clickable part consisting of the given text
     */
    public static Part of(Click clickAction, String text) {
        Validate.notNull(text, "text can't be null");
        return new Part().setClickAction(clickAction).setText(text);
    }

    /**
     * Builds a message part consisting of a localized text and localized text
     * parameters, if any, which executes the given {@link Click}action when
     * clicked.
     * 
     * @param clickAction the action that should be performed when the text is
     *        clicked
     * @param id the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return the clickable part consisting of the given localized text
     */
    public static Part ofLocalized(Click clickAction, String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setClickAction(clickAction).setLocalizedText(id, parameters);
    }

    /**
     * Builds a message part consisting of text, which shows another text when
     * hovered and executes the given {@link Click}action when clicked. The text
     * and the hover text may contain {@link ChatColor}s as chars.
     * 
     * @param clickAction the action that should be performed when the text is
     *        clicked
     * @param text the text that should be displayed by this part
     * @param hoverText The text that should be displayed by this part when
     *        hovered, cannot be null or empty
     * @return the hoverable and clickable part consisting of the given text and
     *         the hover text
     */
    public static Part of(Click clickAction, String text, String... hoverText) {
        Validate.notNull(text, "text can't be null");
        Validate.notEmpty(hoverText, "hoverText can't be empty");
        return new Part().setClickAction(clickAction).setText(text).setHover(Hover.of(hoverText));
    }

    /**
     * Builds a message part consisting of a localized text and localized text
     * parameters, if any, which shows another text when hovered and executes
     * the given {@link Click}action when clicked. The hover text may contain
     * {@link ChatColor}s as chars.
     * 
     * @param clickAction the action that should be performed when the text is
     *        clicked
     * @param hoverText The text that should be displayed by this part when
     *        hovered, cannot be null or empty
     * @param id the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return the hoverable and clickable part consisting of the given
     *         localized text and the hover text
     */
    public static Part ofLocalized(Click clickAction, String[] hoverText, String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setClickAction(clickAction).setHover(Hover.of(hoverText)).setLocalizedText(id, parameters);
    }

    /**
     * Builds a message part consisting of an {@link ItemStack}. The name of the
     * item can be hovered to show the item details and executes the given
     * {@link Click}action when clicked.
     * 
     * @param clickAction the action that should be performed when the item's
     *        name is clicked
     * @param item the item that should be displayed by this part, cannot be
     *        null
     * @return the hoverable and clickable part consisting of the given item
     */
    public static Part of(Click clickAction, ItemStack item) {
        Validate.notNull(item, "item can't be null");
        return new Part().setClickAction(clickAction).setHover(Hover.of(item));
    }

    /**
     * Builds a message part consisting of text, which shows the given item when
     * hovered and executes the given {@link Click}action when clicked. The text
     * may contain {@link ChatColor}s as chars.
     * 
     * @param clickAction the action that should be performed when the text is
     *        clicked
     * @param item the item that should be displayed by this part when hovered,
     *        cannot be null
     * @param text the text that should be displayed by this part
     * @return the hoverable and clickable part consisting of the given text and
     *         the hover item
     */
    public static Part of(Click clickAction, ItemStack item, String text) {
        Validate.notNull(text, "text can't be null");
        return new Part().setClickAction(clickAction).setHover(Hover.of(item)).setText(text);
    }

    /**
     * Builds a message part consisting of localized text, which shows the given
     * item when hovered and executes the given {@link Click}action when
     * clicked.
     * 
     * @param clickAction the action that should be performed when the text is
     *        clicked
     * @param item the item that should be displayed by this part when hovered,
     *        cannot be null
     * @param id the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return the hoverable and clickable part consisting of the given
     *         localized text and the hover item
     */
    public static Part ofLocalized(Click clickAction, ItemStack item, String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setClickAction(clickAction).setHover(Hover.of(item)).setLocalizedText(id, parameters);
    }

    /**
     * Builds a message part consisting of an {@link Achievement}. The name of
     * the achievement can be hovered to show the achievement details and
     * executes the given {@link Click}action when clicked.
     * 
     * @param clickAction the action that should be performed when the
     *        achievement's name is clicked
     * @param achievement the achievement that should be displayed by this part,
     *        cannot be null
     * @return the hoverable and clickable part consisting of the given
     *         achievement
     */
    public static Part of(Click clickAction, Achievement achievement) {
        Validate.notNull(achievement, "achievement can't be null");
        return new Part().setClickAction(clickAction).setHover(Hover.of(achievement));
    }

    /**
     * Builds a message part consisting of text, which shows the given
     * achievement when hovered and executes the given {@link Click}action when
     * clicked. The text may contain {@link ChatColor}s as chars.
     * 
     * @param clickAction the action that should be performed when the text is
     *        clicked
     * @param achievement the achievement that should be displayed by this part
     *        when hovered, cannot be null
     * @param text the text that should be displayed by this part
     * @return the hoverable and clickable part consisting of the given text and
     *         the hover achievement
     */
    public static Part of(Click clickAction, Achievement achievement, String text) {
        Validate.notNull(text, "text can't be null");
        return new Part().setClickAction(clickAction).setHover(Hover.of(achievement)).setText(text);
    }

    /**
     * Builds a message part consisting of localized text, which shows the given
     * achievement when hovered and executes the given {@link Click}action when
     * clicked.
     * 
     * @param clickAction the action that should be performed when the text is
     *        clicked
     * @param achievement the achievement that should be displayed by this part
     *        when hovered, cannot be null
     * @param id the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return the hoverable and clickable part consisting of the given
     *         localized text and the hover achievement
     */
    public static Part ofLocalized(Click clickAction, Achievement achievement, String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        return new Part().setClickAction(clickAction).setHover(Hover.of(achievement)).setLocalizedText(id, parameters);
    }

    /**
     * A String or a localized String identifier if
     * {@link #isLocalizedText()} is true
     */
    private String text;

    /**
     * Determines what is stored in {@link #getText()}, a localized String
     * identifier if true, a simple String otherwise.
     */
    private boolean localizedText;

    /**
     * Parameters for this localized String
     */
    private String[] localizedTextParameters;

    /**
     * The Hover effect for this Part, if any
     */
    private Hover hover;

    /**
     * The Click action for this Part, if any
     */
    private Click clickAction;

    /**
     * Private constructor, Part instances should be built using available
     * static constructors.
     */
    private Part() {}

    /**
     * Gets the text of this Part.
     * <p>
     * May be a simple String or a localized String identifier. Check
     * {@link #isLocalizedText()}.
     *
     * @return the text of this Part
     */
    public String getText() {
        return this.text;
    }

    /**
     * Sets the text of this Part.
     *
     * @param text the new text of this Part
     * @return this Part for chain calls
     * @see #setLocalizedText(String, String...)
     */
    public Part setText(String text) {
        Validate.notNull(text, "text can't be null");
        this.text = text;
        this.localizedText = false;
        return this;
    }

    /**
     * Checks if this Part contains a localized text.
     *
     * @return true if this Part contains a localized text, false otherwise
     */
    public boolean isLocalizedText() {
        return this.localizedText;
    }

    /**
     * Sets the localized text identifier and eventual parameters of this Part.
     *
     * @param id         the localized text identifier
     * @param parameters the localized text parameters
     * @return this Part for chain calls
     */
    public Part setLocalizedText(String id, String... parameters) {
        Validate.notNull(id, "id can't be null");
        this.text = id;
        this.localizedText = true;
        this.setLocalizedTextParameters(parameters);
        return this;
    }

    /**
     * Gets this Part's localized text's parameters.
     *
     * @return this Part's localized text's parameters
     */
    public String[] getLocalizedTextParameters() {
        return this.localizedTextParameters;
    }

    /**
     * Sets this Part's localized text's parameters.
     *
     * @param parameters this Part's new localized text's parameters
     * @return this Part for chain calls
     */
    public Part setLocalizedTextParameters(String... parameters) {
        this.localizedTextParameters = parameters == null || parameters.length == 0 ? null : parameters;
        return this;
    }

    /**
     * Gets this Part's Hover effect.
     *
     * @return this Part's Hover effect
     */
    public Hover getHover() {
        return this.hover;
    }

    /**
     * Sets this Part's Hover effect.
     *
     * @param hover this Part's new Hover effect
     * @return this Part for chain calls
     */
    public Part setHover(Hover hover) {
        this.hover = hover;
        return this;
    }

    /**
     * Gets this Part's Click action.
     *
     * @return this Part's Click action
     */
    public Click getClickAction() {
        return this.clickAction;
    }

    /**
     * Sets this Part's Click action.
     *
     * @param clickAction this Part's new Click action
     * @return this Part for chain calls
     */
    public Part setClickAction(Click clickAction) {
        this.clickAction = clickAction;
        return this;
    }

    /**
     * This implementation of toString is used to send Message to non-Player
     * CommandSender, like ConsoleCommandSender.
     *
     * @return a String representation of this Part
     */
    @Override
    public String toString() {
        if (this.text == null) {
            // text can be null only if this is an Achievement or Item part
            if (this.hover.getType() == Hover.Type.SHOW_ACHIEVEMENT) {
                return this.hover.getAchievement().name();
            } else { // SHOW_ITEM
                return this.hover.getItem().getType().name();
            }
        } else {
            return this.text;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Part part = (Part) o;

        if (localizedText != part.localizedText) {
            return false;
        }
        if (clickAction != null ? !clickAction.equals(part.clickAction) : part.clickAction != null) {
            return false;
        }
        if (hover != null ? !hover.equals(part.hover) : part.hover != null) {
            return false;
        }
        if (!Arrays.equals(localizedTextParameters, part.localizedTextParameters)) {
            return false;
        }
        if (text != null ? !text.equals(part.text) : part.text != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (localizedText ? 1 : 0);
        result = 31 * result + (localizedTextParameters != null ? Arrays.hashCode(localizedTextParameters) : 0);
        result = 31 * result + (hover != null ? hover.hashCode() : 0);
        result = 31 * result + (clickAction != null ? clickAction.hashCode() : 0);
        return result;
    }

    @Override
    public Part clone() {
        final Part part = new Part();
        part.clickAction = clickAction;
        part.hover = hover;
        part.localizedText = localizedText;
        part.localizedTextParameters = localizedTextParameters == null ? null : localizedTextParameters.clone();
        part.text = text;
        return part;
    }

    @Override
    public Map<String, Object> serialize() {
        final ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        if (clickAction != null) {
            builder.put("click", clickAction);
        }
        if (hover != null) {
            builder.put("hover", hover);
        }
        builder.put("localizedText", localizedText);
        if (localizedTextParameters != null) {
            builder.put("localizedTextParameters", localizedTextParameters);
        }
        if (text != null) {
            builder.put("text", text);
        }
        return builder.build();
    }

    /**
     * Converts the given {@link Map} to a chat message Part.
     * 
     * @param the map to convert to a chat message Part
     * @see ConfigurationSerializable
     */
    public static Part deserialize(Map<String, Object> map) {
        Part part = new Part();
        part.clickAction = (Click) map.get("click");
        part.hover = (Hover) map.get("hover");

        final Boolean localizedText = (Boolean) map.get("localizedText");
        part.localizedText = localizedText == null ? false : localizedText.booleanValue();

        final Object localizedTextParameters = map.get("localizedTextParameters");
        if (localizedTextParameters != null) {
            if (localizedTextParameters instanceof String) {
                part.localizedTextParameters = new String[] { (String) localizedTextParameters };
            } else if (localizedTextParameters instanceof String[]) {
                part.localizedTextParameters = (String[]) localizedTextParameters;
            } else if (localizedTextParameters instanceof Collection) {
                final Object[] collection = ((Collection<?>) localizedTextParameters).toArray();
                final int length = collection.length;
                final String[] array = new String[length];
                for (int i = 0; i < length; i++) {
                    if (collection[i] instanceof String) {
                        array[i] = (String) collection[i];
                    } else {
                        throw new IllegalArgumentException(collection + " is not a valid Part.localizedTextParameters");
                    }
                }
                part.localizedTextParameters = array;
            } else {
                throw new IllegalArgumentException(localizedTextParameters + " is not a valid Part.localizedTextParameters");
            }
        }

        part.text = (String) map.get("text");
        return part;
    }

}
