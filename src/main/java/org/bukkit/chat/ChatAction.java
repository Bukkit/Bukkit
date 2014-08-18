package org.bukkit.chat;

import org.apache.commons.lang.Validate;
import org.bukkit.Utility;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents the action to send the specified chat message or command to the
 * server as the Player when clicked client side.
 */
public class ChatAction extends ClickAction {

    private String text;

    /**
     * Builds a new ChatAction, which will send the given text to the server
     * as the Player when clicked client side.
     * <p>
     * The provided text can be either a simple chat message or a command
     * (starting with a <b>/</b>).
     *
     * @param text the text that should be sent to the server
     */
    public ChatAction(String text) {
        super(Type.CHAT);
        Validate.notNull(text, "ChatAction's text can't be null");
        this.text = text;
    }

    /**
     * Gets the text that is sent to the server as the Player when clicked
     * client side.
     *
     * @return the text to send to the server
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text that is send to the server as the Player when clicked
     * client side.
     *
     * @param text the new text send to the server
     */
    public void setText(String text) {
        Validate.notNull(text, "ChatAction's text can't be null");
        this.text = text;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }

    @Utility
    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("text", text);
        return result;
    }

    /**
     * Required method for configuration serialization.
     *
     * @param args map to deserialize
     * @return deserialized ChatAction
     * @see org.bukkit.configuration.serialization.ConfigurationSerializable
     */
    public static ChatAction deserialize(Map<String, Object> args) {
        return new ChatAction((String) args.get("text"));
    }
}
