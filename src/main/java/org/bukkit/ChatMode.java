package org.bukkit;

/**
 * This Enum represent the current Chat Setting from the Client.
 */
public enum ChatMode {
    /**
     * The Client is able to receive all Messages through the chat and will display them like "normal".
     */
    FULL,

    /**
     * The Output of the Chat is disabled, but the Client is able to send of Commands
     */
    COMMAND_ONLY,

    /**
     * The Chat in the Client is completely hidden, so the Client can't get any Messages or send off Commands
     */
    HIDDEN
}
