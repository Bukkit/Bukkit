package org.bukkit.event.player;

import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang.Validate;
import org.bukkit.chat.Message;
import org.bukkit.chat.Part;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * This event will sometimes fire synchronously, depending on how it was
 * triggered.
 * <p>
 * The constructor provides a boolean to indicate if the event was fired
 * synchronously or asynchronously. When asynchronous, this event can be
 * called from any thread, sans the main thread, and has limited access to the
 * API.
 * <p>
 * If a player is the direct cause of this event by an incoming packet, this
 * event will be asynchronous. If a plugin triggers this event by compelling a
 * player to chat, this event will be synchronous.
 * <p>
 * Care should be taken to check {@link #isAsynchronous()} and treat the event
 * appropriately.
 */
public class AsyncPlayerChatEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private Message senderDetails;
    private Message message;
    private String format = "<%1$s> %2$s";
    private final Set<Player> recipients;

    /**
     * Creates a new AsyncPlayerChatEvent for the given player with the given
     * message sent to all recipients after the event finished being processed.
     * 
     * @param async This changes the event to a synchronous state.
     * @param who the chat sender
     * @param message the message sent
     * @param players the players to receive the message. This may be a lazy or
     *     unmodifiable collection.
     */
    public AsyncPlayerChatEvent(final boolean async, final Player who, final Message message, final Set<Player> players) {
        super(who, async);
        Validate.notNull(message, "Message cannot be null!");
        this.senderDetails = Message.of(who.getDisplayName(), who.getUniqueId().toString());
        this.message = message;
        recipients = players;
    }

    /**
     * Creates a new AsyncPlayerChatEvent for the given player with the given
     * message sent to all recipients after the event finished being processed.
     * 
     * @param async This changes the event to a synchronous state.
     * @param who the chat sender
     * @param message the message sent
     * @param players the players to receive the message. This may be a lazy or
     *     unmodifiable collection.
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #AsyncPlayerChatEvent(boolean, Player, Message, Set)} instead.
     */
    @Deprecated
    public AsyncPlayerChatEvent(final boolean async, final Player who, final String message, final Set<Player> players) {
        this(async, who, message == null ? null : Message.of(message), players);
    }

    /**
     * Gets the sender details that the will be shown along with the message.
     * This sender details will by default contain the player name and will show
     * the senders {@link UUID} on hover.
     *
     * @return Message the player details to show
     */
    public Message getSenderDetails() {
        return senderDetails;
    }

    /**
     * Sets the sender details that the will be shown along with the message.
     * The player's name will <b>NOT</b> be injected automatically and should be
     * set before this method is called. Must not be null.
     *
     * @param senderDetails the player details to show
     */
    public void setSenderDetails(Message senderDetails) {
        Validate.notNull(senderDetails, "SenderDetails cannot be null!");
        this.senderDetails = senderDetails;
    }

    /**
     * Gets the message that the player is attempting to send. This message will
     * be used with {@link #getFormat()}. May contain color codes.
     *
     * @return the player is attempting to send
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #getChatMessage()} instead.
     */
    @Deprecated
    public String getMessage() {
        return message.toString();
    }

    /**
     * Sets the message that the player will send. This message will be used
     * with {@link #getFormat()}. Can contain color codes.
     *
     * @param message new message that the player will send
     * @deprecated This event now uses {@link Message} to send the message. Use
     *     {@link #setChatMessage(Message)} instead.
     */
    @Deprecated
    public void setMessage(String message) {
        Validate.notNull("Message cannot be null!");
        this.message = Message.of(message);
    }

    /**
     * Gets the message that the player is attempting to send. This message will
     * be used with {@link #getFormat()}.
     *
     * @return Message the player is attempting to send
     */
    public Message getChatMessage() {
        return message;
    }

    /**
     * Sets the message that the player will send. This message will be used
     * with {@link #getFormat()}.
     *
     * @param message new message that the player will send
     */
    public void setChatMessage(Message message) {
        Validate.notNull(message, "Message cannot be null!");
        this.message = message;
    }

    /**
     * Gets the format to use to display this chat message.
     * <p>
     * When this event finishes execution, the first format parameter %1$s is
     * the {@link #getSenderDetails()} and the second parameter %2$s is
     * {@link #getMessage()}. Defaults to "&lt%1$s&gt %2$s".
     *
     * @return the format string used to format the message
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the format used to display this chat message.
     * <p>
     * When this event finishes execution, the first format parameter %1$s is
     * the {@link #getSenderDetails()} and the second parameter %1$s is
     * {@link #getMessage()}. It is not required to have those parameters in the
     * format, but their use is recommended.
     *
     * @param format the message format to use. Cannot be null.
     */
    public void setFormat(final String format) {
        Validate.notNull(format, "Format cannot be null!");
        this.format = format;
    }

    private final static Pattern FORMATSPLITPATTERN = Pattern.compile("(\\%[12]\\$s)|(\\%s)");

    /**
     * Gets the formated message like it would be shown to the player. Modifying
     * the resulting {@link Message} or its elements does not have any impact on
     * the message being shown.
     * 
     * @return the formated message
     */
    public Message getFormatedMessage() {
        final String[] split = FORMATSPLITPATTERN.split(format, -1);
        final Message formatedMessage = Message.of(split[0]);
        int index = split[0].length();
        boolean showSender = true;
        for (int i = 1; i < split.length; i++) {
            final char id = format.charAt(index + 1);
            if (id == '1') {
                index += 4;
                formatedMessage.append(senderDetails.clone());
            } else if (id == '2') {
                index += 4;
                formatedMessage.append(message.clone());
            } else if (id == 's') {
                index += 2;
                if (showSender) {
                    formatedMessage.append(senderDetails.clone());
                } else {
                    formatedMessage.append(message.clone());
                }
                showSender = !showSender;
            }
            formatedMessage.append(split[i]);
            index += split[i].length();
        }
        return formatedMessage;
    }

    /**
     * Gets a set of recipients that this chat message will be displayed to.
     * <p>
     * The set returned is not guaranteed to be mutable and may auto-populate
     * on access. Any listener accessing the returned set should be aware that
     * it may reduce performance for a lazy set implementation.
     * <p>
     * Listeners should be aware that modifying the list may throw {@link
     * UnsupportedOperationException} if the event caller provides an
     * unmodifiable set.
     *
     * @return All Players who will see this chat message
     */
    public Set<Player> getRecipients() {
        return recipients;
    }

    public boolean isCancelled() {
        return cancel ;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
