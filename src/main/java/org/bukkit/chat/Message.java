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

    // TODO <insert 1 million more static builders here>

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

    // TODO <insert 1 million more insert methods here>

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
}
