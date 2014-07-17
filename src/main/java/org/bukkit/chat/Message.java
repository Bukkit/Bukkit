package org.bukkit.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Message implements Iterable<Message.Part> {

    public static Message empty() {
        return new Message();
    }

    public static Message of(String string) {
        Message result = new Message();
        result.append(string);
        return result;
    }

    public static Message localized(String id, String... params) {
        Message result = new Message();
        result.appendLocalized(id, params);
        return result;
    }

    // <insert 1 million more static builders here>

    private final List<Part> parts;

    private Message() {
        this.parts = new ArrayList<Part>();
    }

    public Message append(Part part) {
        this.parts.add(part);
        return this;
    }

    public Message append(String text) {
        this.parts.add(Part.of(text));
        return this;
    }

    public Message appendLocalized(String id, String... params) {
        this.parts.add(Part.localized(id, params));
        return this;
    }

    // <insert 1 million more append methods here>

    public Message insert(int pos, Part part) {
        this.parts.add(pos, part);
        return this;
    }

    public Message insert(int pos, String text) {
        this.parts.add(pos, Part.of(text));
        return this;
    }

    public Message insertLocalized(int pos, String id, String... params) {
        this.parts.add(pos, Part.localized(id, params));
        return this;
    }

    // <insert 1 million more insert methods here>

    @Override
    public ListIterator<Part> iterator() {
        return this.parts.listIterator();
    }

    public static class Part {

        public static Part of(String text) {
            Part result = new Part();
            result.text = text;
            return result;
        }

        public static Part localized(String id, String... params) {
            Part result = new Part();
            result.localizedTextId = id;
            result.localizedTextParams = params;
            return result;
        }

        // <insert 1 million more static builders here>

        private Part() {}

        private String text;
        private String localizedTextId;
        private String[] localizedTextParams;
        private MessageClick click;
        private MessageHover hover;

        public String getText() {
            return this.text;
        }

        public Part setText(String text) {
            this.text = text;
            return this;
        }

        public String getLocalizedTextId() {
            return this.localizedTextId;
        }

        public Part setLocalizedTextId(String id) {
            this.localizedTextId = id;
            return this;
        }

        public String[] getLocalizedTextParams() {
            return this.localizedTextParams;
        }

        public Part setLocalizedTextParams(String[] params) {
            this.localizedTextParams = params;
            return this;
        }

        public MessageClick getClick() {
            return this.click;
        }

        public Part setClick(MessageClick click) {
            this.click = click;
            return this;
        }

        public MessageHover getHover() {
            return this.hover;
        }

        public Part setHover(MessageHover hover) {
            this.hover = hover;
            return this;
        }
    }
}
