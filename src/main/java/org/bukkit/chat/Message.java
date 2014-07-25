package org.bukkit.chat;

import org.apache.commons.lang.Validate;
import org.bukkit.Achievement;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

public class Message implements Iterable<Message.Part> {

    public static Message empty() {
        return new Message();
    }

    public static Message of(String string) {
        Message result = new Message();
        result.append(string);
        return result;
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

    public Message append(String[] hoverLines, String text) {
        append(Part.of(hoverLines, text));
        return this;
    }

    public Message appendLocalized(String[] hoverLines, String id, String... parameters) {
        append(Part.ofLocalized(hoverLines, id, parameters));
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

    public Message append(Click clickAction, String[] hoverLines, String text) {
        append(Part.of(clickAction, hoverLines, text));
        return this;
    }

    public Message appendLocalized(Click clickAction, String[] hoverLines, String id, String... parameters) {
        append(Part.ofLocalized(clickAction, hoverLines, id, parameters));
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

    public static class Part {

        public static Part of(String text) {
            Validate.notNull(text, "text can't be null");
            return new Part().setText(text);
        }

        public static Part ofLocalized(String id, String... parameters) {
            Validate.notNull(id, "id can't be null");
            return new Part().setLocalizedText(id, parameters);
        }

        public static Part of(String[] hoverLines, String text) {
            Validate.notEmpty(hoverLines, "hoverLines can't be empty");
            Validate.notNull(text, "text can't be null");
            return new Part().setHover(hoverLines).setText(text);
        }

        public static Part ofLocalized(String[] hoverLines, String id, String... parameters) {
            Validate.notNull(id, "id can't be null");
            return new Part().setHover(hoverLines).setLocalizedText(id, parameters);
        }

        public static Part of(ItemStack item) {
            Validate.notNull(item, "item can't be null");
            return new Part().setHover(item);
        }

        public static Part of(ItemStack item, String text) {
            Validate.notNull(text, "text can't be null");
            return new Part().setHover(item).setText(text);
        }

        public static Part ofLocalized(ItemStack item, String id, String... parameters) {
            Validate.notNull(id, "id can't be null");
            return new Part().setHover(item).setLocalizedText(id, parameters);
        }

        public static Part of(Achievement achievement) {
            Validate.notNull(achievement, "achievement can't be null");
            return new Part().setHover(achievement);
        }

        public static Part of(Achievement achievement, String text) {
            Validate.notNull(text, "text can't be null");
            return new Part().setHover(achievement).setText(text);
        }

        public static Part ofLocalized(Achievement achievement, String id, String... parameters) {
            Validate.notNull(id, "id can't be null");
            return new Part().setHover(achievement).setLocalizedText(id, parameters);
        }

        public static Part of(Click clickAction, String text) {
            Validate.notNull(text, "text can't be null");
            return new Part().setClickAction(clickAction).setText(text);
        }

        public static Part ofLocalized(Click clickAction, String id, String... parameters) {
            Validate.notNull(id, "id can't be null");
            return new Part().setClickAction(clickAction).setLocalizedText(id, parameters);
        }

        public static Part of(Click clickAction, String[] hoverLines, String text) {
            Validate.notEmpty(hoverLines, "hoverLines can't be empty");
            Validate.notNull(text, "text can't be null");
            return new Part().setClickAction(clickAction).setHover(hoverLines).setText(text);
        }

        public static Part ofLocalized(Click clickAction, String[] hoverLines, String id, String... parameters) {
            Validate.notNull(id, "id can't be null");
            return new Part().setClickAction(clickAction).setHover(hoverLines).setLocalizedText(id, parameters);
        }

        public static Part of(Click clickAction, ItemStack item) {
            Validate.notNull(item, "item can't be null");
            return new Part().setClickAction(clickAction).setHover(item);
        }

        public static Part of(Click clickAction, ItemStack item, String text) {
            Validate.notNull(text, "text can't be null");
            return new Part().setClickAction(clickAction).setHover(item).setText(text);
        }

        public static Part ofLocalized(Click clickAction, ItemStack item, String id, String... parameters) {
            Validate.notNull(id, "id can't be null");
            return new Part().setClickAction(clickAction).setHover(item).setLocalizedText(id, parameters);
        }

        public static Part of(Click clickAction, Achievement achievement) {
            Validate.notNull(achievement, "achievement can't be null");
            return new Part().setClickAction(clickAction).setHover(achievement);
        }

        public static Part of(Click clickAction, Achievement achievement, String text) {
            Validate.notNull(text, "text can't be null");
            return new Part().setClickAction(clickAction).setHover(achievement).setText(text);
        }

        public static Part ofLocalized(Click clickAction, Achievement achievement, String id, String... parameters) {
            Validate.notNull(id, "id can't be null");
            return new Part().setClickAction(clickAction).setHover(achievement).setLocalizedText(id, parameters);
        }

        private String text;

        private boolean localizedText;
        private String[] localizedTextParameters;

        private String[] hoverLines;

        private Object hoverObject;
        private Click clickAction;

        private Part() {}

        public String getText() {
            return this.text;
        }

        public Part setText(String text) {
            Validate.notNull(text, "text can't be null");
            this.text = text;
            this.localizedText = false;
            return this;
        }

        public boolean isLocalizedText() {
            return this.localizedText;
        }

        public Part setLocalizedText(String id, String... parameters) {
            Validate.notNull(id, "id can't be null");
            this.text = id;
            this.localizedText = true;
            this.setLocalizedTextParameters(parameters);
            return this;
        }

        public String[] getLocalizedTextParameters() {
            return this.localizedTextParameters;
        }

        public Part setLocalizedTextParameters(String... parameters) {
            this.localizedTextParameters = parameters.length == 0 ? null : parameters;
            return this;
        }

        public String[] getHoverLines() {
            return this.hoverLines;
        }

        // TODO Split this into 2 methods?
        public Object getHoverObject() {
            return this.hoverObject;
        }

        public Part setHover(String... hoverLines) {
            // TODO Split input on \n? 
            //    | This would allow providing a single (or even multiple!) String(s) with \n's as hover text
            if (hoverLines == null) {
                this.hoverLines = null;
            } else {
                Validate.notEmpty(hoverLines, "hoverLines can't be empty");
                this.hoverLines = hoverLines;
                this.hoverObject = null;
            }
            return this;
        }

        public Part setHover(Achievement achievement) {
            this.hoverObject = achievement;
            if (achievement != null) {
                this.hoverLines = null;
            }
            return this;
        }

        public Part setHover(ItemStack item) {
            this.hoverObject = item;
            if (item != null) {
                this.hoverLines = null;
            }
            return this;
        }

        public Click getClickAction() {
            return this.clickAction;
        }

        public Part setClickAction(Click clickAction) {
            this.clickAction = clickAction;
            return this;
        }
    }

    public static class Click {
        private static final Pattern HTTP_REGEX = Pattern.compile("^https?://.*", Pattern.CASE_INSENSITIVE);

        public static Click ofOpenUrl(String url) {
            Validate.isTrue(HTTP_REGEX.matcher(url).matches(), "Provided url is invalid: " + url);
            return forType(Type.OPEN_URL, url);
        }

        public static Click ofSendText(String text) {
            return forType(Type.SEND_TEXT, text);
        }

        public static Click ofSetText(String text) {
            return forType(Type.SET_TEXT, text);
        }

        private static Click forType(Type type, String action) {
            Validate.notEmpty(action);
            return new Click(type, action);
        }

        public enum Type {
            OPEN_URL,
            SEND_TEXT,
            SET_TEXT,
        }

        private final Type type;
        private final String text;

        private Click(Type type, String text) {
            this.type = type;
            this.text = text;
        }

        public Type getType() {
            return this.type;
        }

        public String getText() {
            return this.text;
        }
    }
}
