package org.bukkit.chat;

import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Represents a rich Message which can be sent to Players.
 * This is an API for Minecraft 1.7 "Json" chat messages features.
 */
public final class Message implements Iterable<Part> {

    /**
     * Builds a Message from zero (empty Message), one or more Parts.
     *
     * @param parts parts of the Message
     * @return the built Message
     */
    public static Message of(Part... parts) {
        Message result = new Message();
        for (Part part : parts) {
            result.append(part);
        }
        return result;
    }

    /**
     * Builds a Message with a text as first Part.
     *
     * @param string the text
     * @return the built Message
     */
    public static Message of(String string) {
        return Message.of(Part.of(string));
    }

    /**
     * Builds a Message with a localized text as first Part.
     *
     * @param id         the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return the built Message
     */
    public static Message ofLocalized(String id, String... parameters) {
        return Message.of(Part.of(id, parameters));
    }

    /**
     * Builds a Message with a text as first Part, with one or more lines of
     * hover text.
     *
     * @param text      the text
     * @param hoverText the hover text
     * @return the built Message
     */
    public static Message of(String text, String... hoverText) {
        return Message.of(Part.of(text, hoverText));
    }

    /**
     * Builds a Message with an hover text and a localized text as first Part.
     *
     * @param hoverText  the hover text, one or more lines
     * @param id         the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return the built Message
     */
    public static Message ofLocalized(String[] hoverText, String id, String... parameters) {
        return Message.of(Part.ofLocalized(hoverText, id, parameters));
    }

    /**
     * Builds a Message with an ItemStack as first Part.
     *
     * @param item the itemstack
     * @return the built Message
     */
    public static Message of(ItemStack item) {
        return Message.of(Part.of(item));
    }

    /**
     * Builds a Message with a text as first Part, using an ItemStack
     * description as hover text.
     *
     * @param item the itemstack
     * @param text the text
     * @return the built Message
     */
    public static Message of(ItemStack item, String text) {
        return Message.of(Part.of(item, text));
    }

    /**
     * Builds a Message with a localized text as first Part, using an
     * ItemStack description as hover text.
     *
     * @param item       the itemstack
     * @param id         the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return the built Message
     */
    public static Message ofLocalized(ItemStack item, String id, String... parameters) {
        return Message.of(Part.ofLocalized(item, id, parameters));
    }

    /**
     * Builds a Message with an Achievement as first Part.
     *
     * @param achievement the achievement
     * @return the built Message
     */
    public static Message of(Achievement achievement) {
        return Message.of(Part.of(achievement));
    }

    /**
     * Builds a Message with a text as first Part, using an Achievement
     * description as hover text.
     *
     * @param achievement the achievement
     * @param text        the text
     * @return the built Message
     */
    public static Message of(Achievement achievement, String text) {
        return Message.of(Part.of(achievement, text));
    }

    /**
     * Builds a Message with a localized text as first Part, using an
     * Achievement description as hover text.
     *
     * @param achievement the achievement
     * @param id          the localized text identifier
     * @param parameters  the localized text parameters, if any
     * @return the built Message
     */
    public static Message ofLocalized(Achievement achievement, String id, String... parameters) {
        return Message.of(Part.ofLocalized(achievement, id, parameters));
    }

    /**
     * Builds a Message with a text as first Part, linking the provided Click
     * action to it.
     *
     * @param clickAction the click action
     * @param text        the text
     * @return the built Message
     */
    public static Message of(Click clickAction, String text) {
        return Message.of(Part.of(clickAction, text));
    }

    /**
     * Builds a Message with a localized text as first Part, linking the
     * provided Click action to it.
     *
     * @param clickAction the click action
     * @param id          the localized text identifier
     * @param parameters  the localized text parameters, if any
     * @return the built Message
     */
    public static Message ofLocalized(Click clickAction, String id, String... parameters) {
        return Message.of(Part.ofLocalized(clickAction, id, parameters));
    }

    /**
     * Builds a Message with a text as first Part, with one or more lines of
     * hover text, linking the provided Click action to it.
     *
     * @param clickAction the click action
     * @param text        the text
     * @param hoverText   the hover text
     * @return the built Message
     */
    public static Message of(Click clickAction, String text, String... hoverText) {
        return Message.of(Part.of(clickAction, text, hoverText));
    }

    /**
     * Builds a Message with a localized text as first Part, with one or more
     * lines of hover text, linking the provided Click action to it.
     *
     * @param clickAction the click action
     * @param hoverText   the hover text, one or more lines
     * @param id          the localized text identifier
     * @param parameters  the localized text parameters, if any
     * @return the built Message
     */
    public static Message ofLocalized(Click clickAction, String[] hoverText, String id, String... parameters) {
        return Message.of(Part.ofLocalized(clickAction, hoverText, id, parameters));
    }

    /**
     * Builds a Message with an ItemStack as first Part, linking the provided
     * Click action to it.
     *
     * @param clickAction the click action
     * @param item        the itemstack
     * @return the built Message
     */
    public static Message of(Click clickAction, ItemStack item) {
        return Message.of(Part.of(clickAction, item));
    }

    /**
     * Builds a Message with a text as first Part, using an ItemStack as
     * hover text, linking the provided Click action to it.
     *
     * @param clickAction the click action
     * @param item        the itemstack
     * @param text        the text
     * @return the built Message
     */
    public static Message of(Click clickAction, ItemStack item, String text) {
        return Message.of(Part.of(clickAction, item, text));
    }

    /**
     * Builds a Message with a localized text as first Part, using an
     * ItemStack as hover text, linking the provided Click action to it.
     *
     * @param clickAction the click action
     * @param item        the itemstack
     * @param id          the localized text identifier
     * @param parameters  the localized text parameters, if any
     * @return the built Message
     */
    public static Message ofLocalized(Click clickAction, ItemStack item, String id, String... parameters) {
        return Message.of(Part.ofLocalized(clickAction, item, id, parameters));
    }

    /**
     * Builds a Message with an Achievement as first Part, linking the
     * provided Click action to it.
     *
     * @param clickAction the click action
     * @param achievement the achievement
     * @return the built Message
     */
    public static Message of(Click clickAction, Achievement achievement) {
        return Message.of(Part.of(clickAction, achievement));
    }

    /**
     * Builds a Message with a text as first Part, using an Achievement as
     * hover text, linking the provided Click action to it.
     *
     * @param clickAction the click action
     * @param achievement the achievement
     * @param text        the text
     * @return the built Message
     */
    public static Message of(Click clickAction, Achievement achievement, String text) {
        return Message.of(Part.of(clickAction, achievement, text));
    }

    /**
     * Builds a Message with a localized text as firstPart, using an
     * Achievement as hover text, linking the provided Click action to it.
     *
     * @param clickAction the click action
     * @param achievement the achievement
     * @param id          the localized text identifier
     * @param parameters  the localized text parameters, if any
     * @return the built Message
     */
    public static Message ofLocalized(Click clickAction, Achievement achievement, String id, String... parameters) {
        return Message.of(Part.ofLocalized(clickAction, achievement, id, parameters));
    }

    /**
     * The Parts of this Message
     */
    private final List<Part> parts;

    /**
     * Private constructor, Message instances should be built using available
     * static constructors.
     */
    private Message() {
        this.parts = new ArrayList<Part>();
    }

    public Message append(Message message) {
        this.parts.addAll(message.parts);
        return this;
    }

    public Message append(Part part) {
        this.parts.add(part);
        return this;
    }

    public Message append(String text) {
        append(Part.of(text));
        return this;
    }

    public Message appendLocalized(String id, String... parameters) {
        append(Part.ofLocalized(id, parameters));
        return this;
    }

    public Message append(String text, String... hoverText) {
        append(Part.of(text, hoverText));
        return this;
    }

    public Message appendLocalized(String[] hoverText, String id, String... parameters) {
        append(Part.ofLocalized(hoverText, id, parameters));
        return this;
    }

    public Message append(ItemStack item) {
        append(Part.of(item));
        return this;
    }

    public Message append(ItemStack item, String text) {
        append(Part.of(item, text));
        return this;
    }

    public Message appendLocalized(ItemStack item, String id, String... parameters) {
        append(Part.ofLocalized(item, id, parameters));
        return this;
    }

    public Message append(Achievement achievement) {
        append(Part.of(achievement));
        return this;
    }

    public Message append(Achievement achievement, String text) {
        append(Part.of(achievement, text));
        return this;
    }

    public Message appendLocalized(Achievement achievement, String id, String... parameters) {
        append(Part.ofLocalized(achievement, id, parameters));
        return this;
    }

    public Message append(Click clickAction, String text) {
        append(Part.of(clickAction, text));
        return this;
    }

    public Message appendLocalized(Click clickAction, String id, String... parameters) {
        append(Part.ofLocalized(clickAction, id, parameters));
        return this;
    }

    public Message append(Click clickAction, String text, String... hoverText) {
        append(Part.of(clickAction, text, hoverText));
        return this;
    }

    public Message appendLocalized(Click clickAction, String[] hoverText, String id, String... parameters) {
        append(Part.ofLocalized(clickAction, hoverText, id, parameters));
        return this;
    }

    public Message append(Click clickAction, ItemStack item) {
        append(Part.of(clickAction, item));
        return this;
    }

    public Message append(Click clickAction, ItemStack item, String text) {
        append(Part.of(clickAction, item, text));
        return this;
    }

    public Message appendLocalized(Click clickAction, ItemStack item, String id, String... parameters) {
        append(Part.ofLocalized(clickAction, item, id, parameters));
        return this;
    }

    public Message append(Click clickAction, Achievement achievement) {
        append(Part.of(clickAction, achievement));
        return this;
    }

    public Message append(Click clickAction, Achievement achievement, String text) {
        append(Part.of(clickAction, achievement, text));
        return this;
    }

    public Message appendLocalized(Click clickAction, Achievement achievement, String id, String... parameters) {
        append(Part.ofLocalized(clickAction, achievement, id, parameters));
        return this;
    }

    public Message insert(int pos, Part part) {
        this.parts.add(pos, part);
        return this;
    }

    public Message insert(int pos, String text) {
        insert(pos, Part.of(text));
        return this;
    }

    public Message insertLocalized(int pos, String id, String... parameters) {
        insert(pos, Part.ofLocalized(id, parameters));
        return this;
    }

    public Message insert(int pos, String text, String... hoverText) {
        insert(pos, Part.of(text, hoverText));
        return this;
    }

    public Message insertLocalized(int pos, String[] hoverText, String id, String... parameters) {
        insert(pos, Part.ofLocalized(hoverText, id, parameters));
        return this;
    }

    public Message insert(int pos, ItemStack item) {
        insert(pos, Part.of(item));
        return this;
    }

    public Message insert(int pos, ItemStack item, String text) {
        insert(pos, Part.of(item, text));
        return this;
    }

    public Message insertLocalized(int pos, ItemStack item, String id, String... parameters) {
        insert(pos, Part.ofLocalized(item, id, parameters));
        return this;
    }

    public Message insert(int pos, Achievement achievement) {
        insert(pos, Part.of(achievement));
        return this;
    }

    public Message insert(int pos, Achievement achievement, String text) {
        insert(pos, Part.of(achievement, text));
        return this;
    }

    public Message insertLocalized(int pos, Achievement achievement, String id, String... parameters) {
        insert(pos, Part.ofLocalized(achievement, id, parameters));
        return this;
    }

    public Message insert(int pos, Click clickAction, String text) {
        insert(pos, Part.of(clickAction, text));
        return this;
    }

    public Message insertLocalized(int pos, Click clickAction, String id, String... parameters) {
        insert(pos, Part.ofLocalized(clickAction, id, parameters));
        return this;
    }

    public Message insert(int pos, Click clickAction, String text, String... hoverText) {
        insert(pos, Part.of(clickAction, text, hoverText));
        return this;
    }

    public Message insertLocalized(int pos, Click clickAction, String[] hoverText, String id, String... parameters) {
        insert(pos, Part.ofLocalized(clickAction, hoverText, id, parameters));
        return this;
    }

    public Message insert(int pos, Click clickAction, ItemStack item) {
        insert(pos, Part.of(clickAction, item));
        return this;
    }

    public Message insert(int pos, Click clickAction, ItemStack item, String text) {
        insert(pos, Part.of(clickAction, item, text));
        return this;
    }

    public Message insertLocalized(int pos, Click clickAction, ItemStack item, String id, String... parameters) {
        insert(pos, Part.ofLocalized(clickAction, item, id, parameters));
        return this;
    }

    public Message insert(int pos, Click clickAction, Achievement achievement) {
        insert(pos, Part.of(clickAction, achievement));
        return this;
    }

    public Message insert(int pos, Click clickAction, Achievement achievement, String text) {
        insert(pos, Part.of(clickAction, achievement, text));
        return this;
    }

    public Message insertLocalized(int pos, Click clickAction, Achievement achievement, String id, String... parameters) {
        insert(pos, Part.ofLocalized(clickAction, achievement, id, parameters));
        return this;
    }

    /**
     * Gets the Part at the specified position in this Message's Parts list.
     *
     * @param i the index of the wanted Part
     * @return the Part at the specified position
     */
    public Part get(int i) {
        return this.parts.get(i);
    }

    /**
     * Sets the Part at the specified position in this Message's Parts list.
     *
     * @param i    the index
     * @param part the Part
     */
    public void set(int i, Part part) {
        Validate.notNull(part, "part can't be null");
        this.parts.set(i, part);
    }

    @Override
    public ListIterator<Part> iterator() {
        return this.parts.listIterator();
    }

    /**
     * This implementation of toString is used to send Message to non-Player
     * CommandSender, like ConsoleCommandSender.
     *
     * @return a String representation of this Message
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Part p : this.parts) {
            builder.append(p.toString());
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Message parts1 = (Message) o;

        if (!parts.equals(parts1.parts)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return parts.hashCode();
    }
}
