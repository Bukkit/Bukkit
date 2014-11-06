package org.bukkit.chat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.ImmutableMap;

/**
 * Represents a rich Message which can be sent to Players.
 * This is an API for Minecraft 1.7 "Json" chat messages features.
 */
@SerializableAs("ChatMessage")
public final class Message implements Iterable<Part>, ConfigurationSerializable {

    /**
     * Builds a Message from zero (empty Message), one or more Parts.
     *
     * @param parts parts of the Message, cannot be null and cannot contain null
     *     elements
     * @return the built Message
     */
    public static Message of(Part... parts) {
        Validate.notNull(parts, "Parts cannot be null!");
        final Message result = new Message();
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
        return Message.of(Part.ofLocalized(id, parameters));
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

    /**
     * Appends all Parts of the provided Message to this Message.
     *
     * @param message another Message
     * @return this Message for chain calls
     */
    public Message append(Message message) {
        Validate.notNull(message, "Message cannot be null!");
        this.parts.addAll(message.parts);
        return this;
    }

    /**
     * Appends the provided Part to this Message.
     *
     * @param part the Part
     * @return this Message for chain calls
     */
    public Message append(Part part) {
        Validate.notNull(part, "Part cannot be null!");
        this.parts.add(part);
        return this;
    }

    /**
     * Appends a Part built from the provided text.
     *
     * @param text the text
     * @return this Message for chain calls
     */
    public Message append(String text) {
        append(Part.of(text));
        return this;
    }

    /**
     * Appends a Part built from the provided localized text.
     *
     * @param id         the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return this Message for chain calls
     */
    public Message appendLocalized(String id, String... parameters) {
        append(Part.ofLocalized(id, parameters));
        return this;
    }

    /**
     * Appends a Part built from the provided text and hover text.
     *
     * @param text      the text
     * @param hoverText the hover text
     * @return this Message for chain calls
     */
    public Message append(String text, String... hoverText) {
        append(Part.of(text, hoverText));
        return this;
    }

    /**
     * Appends a Part built from the provided localized text and hover text.
     *
     * @param hoverText  the hover text, one or more lines
     * @param id         the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return this Message for chain calls
     */
    public Message appendLocalized(String[] hoverText, String id, String... parameters) {
        append(Part.ofLocalized(hoverText, id, parameters));
        return this;
    }

    /**
     * Appends a Part built from the provided ItemStack.
     *
     * @param item the itemstack
     * @return this Message for chain calls
     */
    public Message append(ItemStack item) {
        append(Part.of(item));
        return this;
    }

    /**
     * Appends a Part built from the provided text, using the provided
     * ItemStack description as hover text.
     *
     * @param item the itemstack
     * @param text the text
     * @return this Message for chain calls
     */
    public Message append(ItemStack item, String text) {
        append(Part.of(item, text));
        return this;
    }

    /**
     * Appends a Part built from the provided localized text, using the
     * provided ItemStack description as hover text.
     *
     * @param item       the itemstack
     * @param id         the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return this Message for chain calls
     */
    public Message appendLocalized(ItemStack item, String id, String... parameters) {
        append(Part.ofLocalized(item, id, parameters));
        return this;
    }

    /**
     * Appends a Part built from the provided Achievement.
     *
     * @param achievement the achievement
     * @return this Message for chain calls
     */
    public Message append(Achievement achievement) {
        append(Part.of(achievement));
        return this;
    }

    /**
     * Appends a Part built from the provided text, using the provided
     * Achievement description as hover text.
     *
     * @param achievement the achievement
     * @param text        the text
     * @return this Message for chain calls
     */
    public Message append(Achievement achievement, String text) {
        append(Part.of(achievement, text));
        return this;
    }

    /**
     * Appends a Part built from the provided localized text, using the
     * provided Achievement description as hover text.
     *
     * @param achievement the achievement
     * @param id          the localized text identifier
     * @param parameters  the localized text parameters, if any
     * @return this Message for chain calls
     */
    public Message appendLocalized(Achievement achievement, String id, String... parameters) {
        append(Part.ofLocalized(achievement, id, parameters));
        return this;
    }

    /**
     * Appends a Part built from the provided text, linking the provided
     * Click action to it.
     *
     * @param clickAction the click action
     * @param text        the text
     * @return this Message for chain calls
     */
    public Message append(Click clickAction, String text) {
        append(Part.of(clickAction, text));
        return this;
    }

    /**
     * Appends a Part built from the provided localized text, linking the
     * provided Click action to it.
     *
     * @param clickAction the click action
     * @param id          the localized text identifier
     * @param parameters  the localized text parameters, if any
     * @return this Message for chain calls
     */
    public Message appendLocalized(Click clickAction, String id, String... parameters) {
        append(Part.ofLocalized(clickAction, id, parameters));
        return this;
    }

    /**
     * Appends a Part built from the provided text and hover text, linking the
     * provided Click action to it.
     *
     * @param clickAction the click action
     * @param text        the text
     * @param hoverText   the hover text
     * @return this Message for chain calls
     */
    public Message append(Click clickAction, String text, String... hoverText) {
        append(Part.of(clickAction, text, hoverText));
        return this;
    }

    /**
     * Appends a Part built from the provided localized text and hover text,
     * linking the provided Click action to it.
     *
     * @param clickAction the click action
     * @param hoverText   the hover text, one or more lines
     * @param id          the localized text identifier
     * @param parameters  the localized text parameters, if any
     * @return this Message for chain calls
     */
    public Message appendLocalized(Click clickAction, String[] hoverText, String id, String... parameters) {
        append(Part.ofLocalized(clickAction, hoverText, id, parameters));
        return this;
    }

    /**
     * Appends a Part built from the provided ItemStack, linking the provided
     * Click action to it.
     *
     * @param clickAction the click action
     * @param item        the itemstack
     * @return this Message for chain calls
     */
    public Message append(Click clickAction, ItemStack item) {
        append(Part.of(clickAction, item));
        return this;
    }

    /**
     * Appends a Part built from the provided text, using the provided
     * ItemStack description as hover text, linking the provided Click action
     * to it.
     *
     * @param clickAction the click action
     * @param item        the itemstack
     * @param text        the text
     * @return this Message for chain calls
     */
    public Message append(Click clickAction, ItemStack item, String text) {
        append(Part.of(clickAction, item, text));
        return this;
    }

    /**
     * Appends a Part built from the provided localized text, using the
     * provided ItemStack description as hover text, linking the provided
     * Click action to it.
     *
     * @param clickAction the click action
     * @param item        the itemstack
     * @param id          the localized text identifier
     * @param parameters  the localized text parameters, if any
     * @return this Message for chain calls
     */
    public Message appendLocalized(Click clickAction, ItemStack item, String id, String... parameters) {
        append(Part.ofLocalized(clickAction, item, id, parameters));
        return this;
    }

    /**
     * Appends a Part built from the provided Achievement, linking the
     * provided Click action to it.
     *
     * @param clickAction the click action
     * @param achievement the achievement
     * @return this Message for chain calls
     */
    public Message append(Click clickAction, Achievement achievement) {
        append(Part.of(clickAction, achievement));
        return this;
    }

    /**
     * Appends a Part built from the provided text, using the provided
     * Achievement description as hover text, linking the provided Click
     * action to it.
     *
     * @param clickAction the click action
     * @param achievement the achievement
     * @param text        the text
     * @return this Message for chain calls
     */
    public Message append(Click clickAction, Achievement achievement, String text) {
        append(Part.of(clickAction, achievement, text));
        return this;
    }

    /**
     * Appends a Part built from the provided localized text, using the
     * provided Achievement description as hover text, linking the provided
     * Click action to it.
     *
     * @param clickAction the click action
     * @param achievement the achievement
     * @param id          the localized text identifier
     * @param parameters  the localized text parameters, if any
     * @return this Message for chain calls
     */
    public Message appendLocalized(Click clickAction, Achievement achievement, String id, String... parameters) {
        append(Part.ofLocalized(clickAction, achievement, id, parameters));
        return this;
    }

    /**
     * Inserts a Part at the provided position in this Message.
     *
     * @param pos  the position
     * @param part the Part
     * @return this Message for chain calls
     */
    public Message insert(int pos, Part part) {
        Validate.notNull(part, "Part cannot be null!");
        this.parts.add(pos, part);
        return this;
    }

    /**
     * Inserts a Part built from the provided text at the provided position
     * in this Message.
     *
     * @param pos  the position
     * @param text the text
     * @return this Message for chain calls
     */
    public Message insert(int pos, String text) {
        insert(pos, Part.of(text));
        return this;
    }

    /**
     * Inserts a Part built from the provided localized text at the provided
     * position in this Message.
     *
     * @param pos        the position
     * @param id         the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return this Message for chain calls
     */
    public Message insertLocalized(int pos, String id, String... parameters) {
        insert(pos, Part.ofLocalized(id, parameters));
        return this;
    }

    /**
     * Inserts a Part built from the provided text and hover text at the
     * provided position in this Message.
     *
     * @param pos       the position
     * @param text      the text
     * @param hoverText the hover text
     * @return this Message for chain calls
     */
    public Message insert(int pos, String text, String... hoverText) {
        insert(pos, Part.of(text, hoverText));
        return this;
    }

    /**
     * Inserts a Part built from the provided localized text and hover text
     * at the provided position in this Message.
     *
     * @param pos        the position
     * @param hoverText  the hover text, one or more lines
     * @param id         the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return this Message for chain calls
     */
    public Message insertLocalized(int pos, String[] hoverText, String id, String... parameters) {
        insert(pos, Part.ofLocalized(hoverText, id, parameters));
        return this;
    }

    /**
     * Inserts a Part built from the provided ItemStack at the provided
     * position in this Message.
     *
     * @param pos  the position
     * @param item the itemstack
     * @return this Message for chain calls
     */
    public Message insert(int pos, ItemStack item) {
        insert(pos, Part.of(item));
        return this;
    }

    /**
     * Inserts a Part built from the provided text, using the provided
     * ItemStack description as hover text, at the provided position in this
     * Message.
     *
     * @param pos  the position
     * @param item the itemstack
     * @param text the text
     * @return this Message for chain calls
     */
    public Message insert(int pos, ItemStack item, String text) {
        insert(pos, Part.of(item, text));
        return this;
    }

    /**
     * Inserts a Part built from the provided localized text, using the
     * provided ItemStack description as hover text, at the provided position
     * in this Message.
     *
     * @param pos        the position
     * @param item       the itemstack
     * @param id         the localized text identifier
     * @param parameters the localized text parameters, if any
     * @return this Message for chain calls
     */
    public Message insertLocalized(int pos, ItemStack item, String id, String... parameters) {
        insert(pos, Part.ofLocalized(item, id, parameters));
        return this;
    }

    /**
     * Inserts a Part built from the provided Achievement at the provided
     * position in this Message.
     *
     * @param pos         the position
     * @param achievement the achievement
     * @return this Message for chain calls
     */
    public Message insert(int pos, Achievement achievement) {
        insert(pos, Part.of(achievement));
        return this;
    }

    /**
     * Inserts a Part built from the provided text, using the provided
     * Achievement description as hover text, at the provided position in
     * this Message.
     *
     * @param pos         the position
     * @param achievement the achievement
     * @param text        the text
     * @return this Message for chain calls
     */
    public Message insert(int pos, Achievement achievement, String text) {
        insert(pos, Part.of(achievement, text));
        return this;
    }

    /**
     * Inserts a Part built from the provided localized text, using the
     * provided Achievement description as hover text, at the provided
     * position in this Message.
     *
     * @param pos         the position
     * @param achievement the achievement
     * @param id          the localized text identifier
     * @param parameters  the localized text parameters, if any
     * @return this Message for chain calls
     */
    public Message insertLocalized(int pos, Achievement achievement, String id, String... parameters) {
        insert(pos, Part.ofLocalized(achievement, id, parameters));
        return this;
    }

    /**
     * Inserts a Part built from the provided text, linking the provided
     * Click action to it, at the provided position in this Message.
     *
     * @param pos         the position
     * @param clickAction the click action
     * @param text        the text
     * @return this Message for chain calls
     */
    public Message insert(int pos, Click clickAction, String text) {
        insert(pos, Part.of(clickAction, text));
        return this;
    }

    /**
     * Inserts a Part built from the provided localized text, linking the
     * provided Click action to it, at the provided position in this Message.
     *
     * @param pos         the position
     * @param clickAction the click action
     * @param id          the localized text identifier
     * @param parameters  the localized text parameters, if any
     * @return this Message for chain calls
     */
    public Message insertLocalized(int pos, Click clickAction, String id, String... parameters) {
        insert(pos, Part.ofLocalized(clickAction, id, parameters));
        return this;
    }

    /**
     * Inserts a Part built from the provided text and hover text, linking
     * the provided Click action to it, at the provided position in this
     * Message.
     *
     * @param pos         the position
     * @param clickAction the click action
     * @param text        the text
     * @param hoverText   the hover text
     * @return this Message for chain calls
     */
    public Message insert(int pos, Click clickAction, String text, String... hoverText) {
        insert(pos, Part.of(clickAction, text, hoverText));
        return this;
    }

    /**
     * Inserts a Part built from the provided localized text and hover text,
     * linking the provided Click action to it, at the provided position in
     * this Message.
     *
     * @param pos         the position
     * @param clickAction the click action
     * @param hoverText   the hover text, one or more lines
     * @param id          the localized text identifier
     * @param parameters  the localized text parameters, if any
     * @return this Message for chain calls
     */
    public Message insertLocalized(int pos, Click clickAction, String[] hoverText, String id, String... parameters) {
        insert(pos, Part.ofLocalized(clickAction, hoverText, id, parameters));
        return this;
    }

    /**
     * Inserts a Part built from the provided ItemStack, linking the provided
     * Click action to it, at the provided position in this Message.
     *
     * @param pos         the position
     * @param clickAction the click action
     * @param item        the itemstack
     * @return this Message for chain calls
     */
    public Message insert(int pos, Click clickAction, ItemStack item) {
        insert(pos, Part.of(clickAction, item));
        return this;
    }

    /**
     * Inserts a Part built from the provided text, using the provided
     * ItemStack description as hover text, linking the provided Click action
     * to it, at the provided position in this Message.
     *
     * @param pos         the position
     * @param clickAction the click action
     * @param item        the itemstack
     * @param text        the text
     * @return this Message for chain calls
     */
    public Message insert(int pos, Click clickAction, ItemStack item, String text) {
        insert(pos, Part.of(clickAction, item, text));
        return this;
    }

    /**
     * Inserts a Part built from the provided localized text, using the
     * provided ItemStack description as hover text, linking the provided
     * Click action to it, at the provided position in this Message.
     *
     * @param pos         the position
     * @param clickAction the click action
     * @param item        the itemstack
     * @param id          the localized text identifier
     * @param parameters  the localized text parameters, if any
     * @return this Message for chain calls
     */
    public Message insertLocalized(int pos, Click clickAction, ItemStack item, String id, String... parameters) {
        insert(pos, Part.ofLocalized(clickAction, item, id, parameters));
        return this;
    }

    /**
     * Inserts a Part built from the provided Achievement, linking the
     * provided Click action to it, at the provided position in this Message.
     *
     * @param pos         the position
     * @param clickAction the click action
     * @param achievement the achievement
     * @return this Message for chain calls
     */
    public Message insert(int pos, Click clickAction, Achievement achievement) {
        insert(pos, Part.of(clickAction, achievement));
        return this;
    }

    /**
     * Inserts a Part built from the provided text, using the provided
     * Achievement description as hover text, linking the provided Click
     * action to it, at the provided position in this Message.
     *
     * @param pos         the position
     * @param clickAction the click action
     * @param achievement the achievement
     * @param text        the text
     * @return this Message for chain calls
     */
    public Message insert(int pos, Click clickAction, Achievement achievement, String text) {
        insert(pos, Part.of(clickAction, achievement, text));
        return this;
    }

    /**
     * Inserts a Part built from the provided localized text, using the
     * provided Achievement description as hover text, linking the provided
     * Click action to it, at the provided position in this Message.
     *
     * @param pos         the position
     * @param clickAction the click action
     * @param achievement the achievement
     * @param id          the localized text identifier
     * @param parameters  the localized text parameters, if any
     * @return this Message for chain calls
     */
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
     * @return this Message for chain calls
     */
    public Message set(int i, Part part) {
        Validate.notNull(part, "part can't be null");
        this.parts.set(i, part);
        return this;
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

    @Override
    public Message clone() {
        final Message message = new Message();
        for (Part part : parts) {
            message.append(part.clone());
        }
        return message;
    }

    @Override
    public Map<String, Object> serialize() {
        return ImmutableMap.<String, Object> of(
            "parts", parts
        );
    }

    /**
     * Converts the given {@link Map} to a chat {@link Message}.
     * 
     * @param the map to convert to a chat Message
     * @see ConfigurationSerializable
     */
    public static Message deserialize(Map<String, Object> map) {
        final Object parts = map.get("parts");
        if (parts == null) {
            throw new IllegalArgumentException("Message parts are missing");
        }
        if (parts instanceof Part) {
            return new Message().append((Part) parts);
        } else if (parts instanceof Collection) {
            final Collection<?> collection = (Collection<?>) parts;
            final Message message = new Message();
            for (Object part : collection) {
                if (part instanceof Part) {
                    message.append((Part) part);
                } else {
                    throw new IllegalArgumentException(part + " is not a valid Message Part");
                }
            }
            return message;
        } else {
            throw new IllegalArgumentException(parts + " is not a valid Message Part");
        }
    }
}
