package org.bukkit.chat;

import org.apache.commons.lang.Validate;

import java.util.regex.Pattern;

public final class Click {
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
        ;
    }

    private Type type;
    private String text;

    Click(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    public Type getType() {
        return this.type;
    }

    public String getText() {
        return this.text;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Click click = (Click) o;

        if (!text.equals(click.text)) {
            return false;
        }
        if (type != click.type) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }
}
