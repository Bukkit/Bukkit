package org.bukkit.chat;

import org.bukkit.Achievement;
import org.bukkit.Utility;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a part of a RichMessage.
 */
public abstract class RichMessagePart implements ConfigurationSerializable {

    /**
     * Types of RichMessagePart
     */
    public enum Type {

        /**
         * Show an Achievement as a tooltip
         */
        ACHIEVEMENT,

        /**
         * Show an ItemStack as a tooltip
         */
        ITEM,

        /**
         * Customizable text-based RichMessagePart
         */
        CUSTOM,
    }

    private final Type type;
    private String text;
    private LocalizedText localizedText;
    private ClickAction clickAction;

    /**
     * Builds a RichMessagePart with a simple text.
     *
     * @param type the type of this RichMessagePart
     * @param text the text of this RichMessagePart
     */
    protected RichMessagePart(Type type, String text) {
        this.type = type;
        this.text = text;
        this.localizedText = null;
        this.clickAction = null;
    }

    /**
     * Builds a RichMessagePart with a localized text.
     *
     * @param type          the type of this RichMessagePart
     * @param localizedText the localized text of this RichMessagePart
     */
    protected RichMessagePart(Type type, LocalizedText localizedText) {
        this.type = type;
        this.text = null;
        this.localizedText = localizedText;
        this.clickAction = null;
    }

    /**
     * Builds a RichMessagePart with a simple text and a click action.
     *
     * @param type        the type of this RichMessagePart
     * @param text        the text of this RichMessagePart
     * @param clickAction the clickAction of this RichMessagePart
     */
    protected RichMessagePart(Type type, String text, ClickAction clickAction) {
        this.type = type;
        this.text = text;
        this.localizedText = null;
        this.clickAction = clickAction;
    }

    /**
     * Builds a RichMessagePart with a localized text and a click action.
     *
     * @param type          the type of this RichMessagePart
     * @param localizedText the localized text of this RichMessagePart
     * @param clickAction   the clickAction of this RichMessagePart
     */
    protected RichMessagePart(Type type, LocalizedText localizedText, ClickAction clickAction) {
        this.type = type;
        this.text = null;
        this.localizedText = localizedText;
        this.clickAction = clickAction;
    }

    /**
     * Gets this RichMessagePart's type.
     *
     * @return this RichMessagePart's type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Gets this RichMessagePart's text.
     *
     * @return this RichMessagePart's text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets this RichMessagePart's text.
     *
     * @param text this RichMessagePart's new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets this RichMessagePart's localized text.
     * <p>
     * Note that this will only be used if this RichMessagePart's text
     * is null.
     *
     * @return this RichMessagePart's localized text
     */
    public LocalizedText getLocalizedText() {
        return localizedText;
    }

    /**
     * Sets this RichMessagePart's localized text.
     * <p>
     * Note that this will only be used if this RichMessagePart's text
     * is null.
     *
     * @param localizedText this RichMessagePart's localized text
     */
    public void setLocalizedText(LocalizedText localizedText) {
        this.localizedText = localizedText;
    }

    /**
     * Gets this RichMessagePart's click action.
     *
     * @return this RichMessagePart's click action
     */
    public ClickAction getClickAction() {
        return clickAction;
    }

    /**
     * Sets this RichMessagePart's click action.
     *
     * @param clickAction this RichMessagePart's new click action
     */
    public void setClickAction(ClickAction clickAction) {
        this.clickAction = clickAction;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (localizedText != null ? localizedText.hashCode() : 0);
        result = 31 * result + (clickAction != null ? clickAction.hashCode() : 0);
        return result;
    }

    @Utility
    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<String, Object>();

        result.put("type", type.name());
        if (clickAction != null) {
            result.put("clickAction", clickAction.serialize());
        }
        if (text != null) {
            result.put("text", text);
        } else if (localizedText != null) {
            result.put("localizedText", localizedText.serialize());
        }

        return result;
    }

    /**
     * Required method for configuration serialization
     *
     * @param args map to deserialize
     * @return deserialized rich message
     * @see ConfigurationSerializable
     */
    public static RichMessagePart deserialize(Map<String, Object> args) {
        RichMessagePart result = null;
        Type type = Type.valueOf((String) args.get("type"));

        ClickAction clickAction = null;
        if (args.containsKey("clickAction")) {
            clickAction = ClickAction.deserialize((Map<String, Object>) args.get("clickAction"));
        }

        String text = null;
        LocalizedText localizedText = null;
        if (args.containsKey("text")) {
            text = (String) args.get("text");
        } else if (args.containsKey("localizedText")) {
            localizedText = LocalizedText.deserialize((Map<String, Object>) args.get("localizedText"));
        }

        switch (type) {
            case ACHIEVEMENT:
                Achievement achievement = Achievement.valueOf((String) args.get("achievement"));
                if (text != null) {
                    result = new AchievementMessagePart(achievement, text, clickAction);
                } else if (localizedText != null) {
                    result = new AchievementMessagePart(achievement, localizedText, clickAction);
                } else {
                    result = new AchievementMessagePart(achievement, clickAction);
                }
                break;
            case ITEM:
                ItemStack item = ItemStack.deserialize((Map<String, Object>) args.get("item"));
                if (text != null) {
                    result = new ItemMessagePart(item, text, clickAction);
                } else if (localizedText != null) {
                    result = new ItemMessagePart(item, localizedText, clickAction);
                } else {
                    result = new ItemMessagePart(item, clickAction);
                }
                break;
            case CUSTOM:
                String[] tooltipLines = new String[0];
                if (args.containsKey("tooltip")) {
                    tooltipLines = ((List<String>) args.get("tooltip")).toArray(tooltipLines);
                }
                if (text != null) {
                    result = new CustomMessagePart(text, clickAction, tooltipLines);
                } else if (localizedText != null) {
                    result = new CustomMessagePart(localizedText, clickAction, tooltipLines);
                } else {
                    throw new IllegalArgumentException("Missing text/localizedText for CustomMessagePart");
                }
        }

        return result;
    }
}
