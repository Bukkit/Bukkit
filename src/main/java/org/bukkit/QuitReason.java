package org.bukkit;

/**
 * Reasons logged in players leave the server (both voluntarily and forcibly)
 */
public enum QuitReason {
    /**
     * Reason player quit is unknown
     */
    UNKNOWN,
    /**
     * Player has quit the game using the ESC menu
     */
    QUITTING,
    /**
     * Player has stopped sending packets
     */
    END_OF_STREAM,
    /**
     * The player encountered an overflow
     */
    OVERFLOW,
    /**
     * No reason was given by the server
     */
    GENERIC_REASON,
    /**
     * The player did not send a response in a timely manner
     */
    TIMEOUT,
    /**
     * The player was disconnected for spamming
     */
    ILLEGAL_CHAT_RATE,
    /**
     * The player had an illegal stance
     */
    ILLEGAL_STANCE,
    /**
     * The player was disconnected for attempting to fly when disallowed (occurs if allow-flight is disabled in server.properties)
     */
    ILLEGAL_FLIGHT,
    /**
     * The player was disconnected being in a position they are not allowed
     */
    ILLEGAL_POSITION,
    /**
     * The player was dropping items too quickly
     */
    ILLEGAL_DROP_RATE,
    /**
     * The player was disconnected as the client sent a packet the server was not expecting
     */
    ILLEGAL_PROTOCOL,
    /**
     * The player tried to place an item that cannot be carried
     */
    ILLEGAL_ITEM,
    /**
     * The player sent an illegal character in chat
     */
    ILLEGAL_CHAT_CHARACTER,
    /**
     * The player tried to send a chat message that was too long
     */
    ILLEGAL_CHAT_LENGTH,
    /**
     * The player was disconnected for attempting to put illegal data into a book
     */
    ILLEGAL_BOOK_DATA,
    /**
     * The player was disconnected for attempting to perform a trade with illegal data
     */
    ILLEGAL_TRADE_DATA,
    /**
     * The player was disconnected for sending illegal Command Block data
     */
    ILLEGAL_COMMAND_BLOCK_DATA,
    /**
     * The player was disconnected for sending illegal Beacon data
     */
    ILLEGAL_BEACON_DATA,
    /**
     * The player attempted to attack an item, experience orb, or arrow (potential exploitaition)
     */
    ILLEGAL_ATTACK,
    /**
     * The player died in hardcore mode and was kicked
     */
    DIED_HARDCORE,
    /**
     * The player logged into the server from another client
     */
    LOGIN_LOCATION,
    /**
     * The player was kicked from the server as the server is shutting down
     */
    SERVER_SHUTDOWN,
    /**
     * The player was kicked from the server for other reasons (including via plugins)
     */
    KICKED;
}
