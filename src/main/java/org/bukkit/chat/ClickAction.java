package org.bukkit.chat;

import org.bukkit.Utility;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents an action triggered client side by clicking the particular
 * {@link org.bukkit.chat.RichMessagePart} in the {@link RichMessage}.
 * The type of action triggered by this is specified by {@link Type}.
 * <p>
 * <b>Note:</b> You should not extend this class. Ready to use click action
 * classes are provided by Bukkit. Any feature that is not (yet)
 * covered by these classes cannot be used.
 *
 * @see org.bukkit.chat.ChatAction
 * @see org.bukkit.chat.OpenUrlAction
 * @see org.bukkit.chat.SuggestChatAction
 */
public abstract class ClickAction implements ConfigurationSerializable {

    /**
     * The supported types of click actions.
     */
    public enum Type {
        /**
         * Opens the url or the "do you really want to open that URL" prompt
         * on click. Depending on the player's chat settings.
         */
        OPEN_URL,

        /**
         * Runs/Sends a command/chat message on click.
         */
        CHAT,

        /**
         * Type a command/chat message to the chat input on click, but does
         * not send it to the server.
         * This actually replaces what's already in the client's chat input.
         */
        SUGGEST_CHAT,
    }

    protected final Type type;

    /**
     * Builds a ClickAction with the provided Type.
     *
     * @param type the type of this ClickAction
     */
    protected ClickAction(Type type) {
        this.type = type;
    }

    /**
     * Gets this ClickAction's type.
     *
     * @return this ClickAction's type
     */
    public Type getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    @Utility
    public Map<String, Object> serialize() {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("type", type.name());
        return result;
    }

    /**
     * Required method for configuration serialization.
     *
     * @param args map to deserialize
     * @return deserialized ClickAction
     * @see org.bukkit.configuration.serialization.ConfigurationSerializable
     */
    public static ClickAction deserialize(Map<String, Object> args) {
        ClickAction result = null;
        Type type = Type.valueOf((String) args.get("type"));
        switch (type) {
            case OPEN_URL:
                result = OpenUrlAction.deserialize(args);
                break;
            case CHAT:
                result = ChatAction.deserialize(args);
                break;
            case SUGGEST_CHAT:
                result = SuggestChatAction.deserialize(args);
        }
        return result;
    }
}
