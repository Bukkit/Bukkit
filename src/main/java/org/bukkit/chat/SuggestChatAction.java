package org.bukkit.chat;

import org.apache.commons.lang.Validate;
import org.bukkit.Utility;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * The suggest chat action will open the player chat and fill it,
 * when clicked client side
 */
public class SuggestChatAction extends ClickAction {

    private String text;

    /**
     * Builds a new SuggestChatAction, which will open the player chat and fill it,
     * when clicked client side
     *
     * @param text the text to fill the player's chat with
     */
    public SuggestChatAction(String text) {
        super(Type.SUGGEST_CHAT);
        Validate.notNull(text, "SuggestChatAction's text can't be null");
        this.text = text;
    }

    /**
     * Gets the text that should fill the client chat when clicked client
     * side.
     *
     * @return the text that should fill the player's chat
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text that should fill the client chat when clicked client
     * side
     *
     * @param text the text that should fill the player's chat
     */
    public void setText(String text) {
        Validate.notNull(text, "SuggestChatAction's text can't be null");
        this.text = text;
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Utility
    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("text", text);
        return result;
    }

    /**
     * Required method for configuration serialization
     *
     * @param args map to deserialize
     * @return deserialized SuggestChatAction
     * @see org.bukkit.configuration.serialization.ConfigurationSerializable
     */
    public static SuggestChatAction deserialize(Map<String, Object> args) {
        return new SuggestChatAction((String) args.get("text"));
    }
}
