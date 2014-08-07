package org.bukkit.chat;

import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public final class Message implements Iterable<Part> {

    public static Message empty() {
        return new Message();
    }

    public static Message of(Part part) {
        Message result = new Message();
        result.append(part);
        return result;
    }

    public static Message of(String string) {
        return Message.of(Part.of(string));
    }

    public static Message ofLocalized(String id, String... parameters) {
        return Message.of(Part.of(id, parameters));
    }

    public static Message of(String text, String... hoverText) {
        return Message.of(Part.of(text, hoverText));
    }


    public static Message ofLocalized(String[] hoverText, String id, String... parameters) {
        return Message.of(Part.ofLocalized(hoverText, id, parameters));
    }

    public static Message of(ItemStack item) {
        return Message.of(Part.of(item));
    }

    public static Message of(ItemStack item, String text) {
        return Message.of(Part.of(item, text));
    }

    public static Message ofLocalized(ItemStack item, String id, String... parameters) {
        return Message.of(Part.ofLocalized(item, id, parameters));
    }

    public static Message of(Achievement achievement) {
        return Message.of(Part.of(achievement));
    }

    public static Message of(Achievement achievement, String text) {
        return Message.of(Part.of(achievement, text));
    }

    public static Message ofLocalized(Achievement achievement, String id, String... parameters) {
        return Message.of(Part.ofLocalized(achievement, id, parameters));
    }

    public static Message of(Click clickAction, String text) {
        return Message.of(Part.of(clickAction, text));
    }

    public static Message ofLocalized(Click clickAction, String id, String... parameters) {
        return Message.of(Part.ofLocalized(clickAction, id, parameters));
    }

    public static Message of(Click clickAction, String text, String... hoverText) {
        return Message.of(Part.of(clickAction, text, hoverText));
    }

    public static Message ofLocalized(Click clickAction, String[] hoverText, String id, String... parameters) {
        return Message.of(Part.ofLocalized(clickAction, hoverText, id, parameters));
    }

    public static Message of(Click clickAction, ItemStack item) {
        return Message.of(Part.of(clickAction, item));
    }

    public static Message of(Click clickAction, ItemStack item, String text) {
        return Message.of(Part.of(clickAction, item, text));
    }

    public static Message ofLocalized(Click clickAction, ItemStack item, String id, String... parameters) {
        return Message.of(Part.ofLocalized(clickAction, item, id, parameters));
    }

    public static Message of(Click clickAction, Achievement achievement) {
        return Message.of(Part.of(clickAction, achievement));
    }

    public static Message of(Click clickAction, Achievement achievement, String text) {
        return Message.of(Part.of(clickAction, achievement, text));
    }

    public static Message ofLocalized(Click clickAction, Achievement achievement, String id, String... parameters) {
        return Message.of(Part.ofLocalized(clickAction, achievement, id, parameters));
    }

    private final List<Part> parts;

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

    public Part get(int i) {
        return this.parts.get(i);
    }

    public void set(int i, Part part) {
        Validate.notNull(part, "part can't be null");
        this.parts.set(i, part);
    }

    @Override
    public ListIterator<Part> iterator() {
        return this.parts.listIterator();
    }

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
